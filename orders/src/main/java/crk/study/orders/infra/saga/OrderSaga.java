package crk.study.orders.infra.saga;

import crk.study.core.commands.CancelProductReservationCommand;
import crk.study.core.commands.ProcessPaymentCommand;
import crk.study.core.commands.ReserveProductCommand;
import crk.study.core.events.PaymentProcessedEvent;
import crk.study.core.events.ProductReservationCanceledEvent;
import crk.study.core.events.ProductReservedEvent;
import crk.study.core.model.User;
import crk.study.core.query.FetchUserPaymentDetailsQuery;
import crk.study.orders.domain.events.OrderApprovedEvent;
import crk.study.orders.domain.events.OrderCreatedEvent;
import crk.study.orders.domain.events.OrderRejectedEvent;
import crk.study.orders.infra.commands.approveorder.ApproveOrderCommand;
import crk.study.orders.infra.commands.rejectedorder.RejectOrderCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Saga
public class OrderSaga {

    @Autowired
    private transient CommandGateway commandGateway;
    @Autowired
    private transient QueryGateway queryGateway;
    private final static Logger logger = LoggerFactory.getLogger(OrderSaga.class);

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void handler(OrderCreatedEvent orderCreatedEvent) {

        logger.info("Start Saga.");
        ReserveProductCommand reserveProductCommand = ReserveProductCommand.builder()
                .productId(orderCreatedEvent.getProductId())
                .orderId(orderCreatedEvent.getOrderId())
                .quantity(orderCreatedEvent.getQuantity())
                .userId(orderCreatedEvent.getUserId())
                .build();
        logger.info("Sending command ReserveProductCommand with orderId [{}]!",
                orderCreatedEvent.getOrderId());
        commandGateway.send(reserveProductCommand, (commandMessage, commandResultMessage) -> {
            if (commandResultMessage.isExceptional()) {
                logger.error("Error! [{}]", commandResultMessage.exceptionResult().getMessage());
            }
        });
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handler(ProductReservedEvent productReservedEvent) {

        FetchUserPaymentDetailsQuery fetchUserPaymentDetailsQuery =
                new FetchUserPaymentDetailsQuery(productReservedEvent.getUserId());
        logger.info("Sending query to Payments Micro in order to fetching data with orderId [{}]!",
                productReservedEvent.getOrderId());
        User us = null;
        try {
            us = queryGateway.query(fetchUserPaymentDetailsQuery, ResponseTypes.instanceOf(User.class)).join();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            cancelProductReservation(productReservedEvent, ex.getMessage());
            return;
        }
        if (us == null) {

            cancelProductReservation(productReservedEvent, "There was not possible fetch user payments details");
            return;
        }
        logger.info("It was possible fetch payment details of client [{}] ", us.getFirstName());
        createAndSendProcessPaymentCommand(productReservedEvent, us);
    }

    private void createAndSendProcessPaymentCommand(ProductReservedEvent productReservedEvent, User us) {
        ProcessPaymentCommand processPaymentCommand = ProcessPaymentCommand.builder()
                .paymentId(UUID.randomUUID().toString())
                .orderId(productReservedEvent.getOrderId())
                .paymentDetails(us.getPaymentDetails())
                .build();
        logger.info("Sending command ProcessPaymentCommand with orderId [{}]!",
                productReservedEvent.getOrderId());
        String result = null;
        try {
            result = commandGateway.sendAndWait(processPaymentCommand, 10, TimeUnit.SECONDS);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            cancelProductReservation(productReservedEvent, ex.getMessage());
            return;
        }
        if (result == null) {
            logger.info("The ProcessPaymentCommand resulted is NULL, Initiating a compensating transaction");
            cancelProductReservation(productReservedEvent, "The ProcessPaymentCommand resulted is NULL, Initiating a compensating transaction");
            return;
        }
    }

    private void cancelProductReservation(ProductReservedEvent productReservedEvent, String reason) {

        CancelProductReservationCommand command =
                CancelProductReservationCommand.builder()
                        .orderId(productReservedEvent.getOrderId())
                        .productId(productReservedEvent.getProductId())
                        .userId(productReservedEvent.getUserId())
                        .quantity(productReservedEvent.getQuantity())
                        .reason(reason)
                        .build();
        commandGateway.send(command);

    }


    @SagaEventHandler(associationProperty = "orderId")
    public void handler(ProductReservationCanceledEvent productReservationCanceledEvent) {

        RejectOrderCommand orderCommand = new RejectOrderCommand(
                productReservationCanceledEvent.getOrderId(),
                productReservationCanceledEvent.getReason());

        logger.info("Sending command RejectOrderCommand with orderId [{}]!",
                orderCommand.getOrderId());
        commandGateway.send(orderCommand);
    }


    @SagaEventHandler(associationProperty = "orderId")
    public void handler(PaymentProcessedEvent paymentProcessedEvent) {

        ApproveOrderCommand orderCommand = ApproveOrderCommand.builder()
                .orderId(paymentProcessedEvent.getOrderId())
                .build();
        logger.info("Sending command ApproveOrderCommand with orderId [{}]!",
                orderCommand.getOrderId());
        commandGateway.send(orderCommand);
    }
    
    @EndSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void handler(OrderRejectedEvent orderApprovedEvent) {

        logger.info("OrderRejectedEvent Saga finished.");
        SagaLifecycle.end();
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void handler(OrderApprovedEvent orderApprovedEvent) {

        logger.info("OrderApprovedEvent Saga finished.");
        SagaLifecycle.end();
    }


}
