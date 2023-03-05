package crk.study.orders.infra.commands;

import crk.study.orders.domain.events.OrderApprovedEvent;
import crk.study.orders.infra.commands.approveorder.ApproveOrderCommand;
import crk.study.orders.infra.commands.createorder.CreateOrderCommand;
import crk.study.orders.domain.events.OrderCreatedEvent;
import crk.study.orders.domain.model.OrderStatus;
import crk.study.orders.infra.commands.rejectedorder.RejectOrderCommand;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static crk.study.orders.domain.events.mapper.OrderEventsMapper.buildOrderCreatedEventFrom;


@Aggregate
//@CustomLog
@NoArgsConstructor
public class OrderAggregate {

    @AggregateIdentifier
    private String orderId;
    private String productId;
    private String userId;
    private int quantity;
    private String addressId;
    private OrderStatus orderStatus;

    private final static Logger logger = LoggerFactory.getLogger(OrderAggregate.class);


    @CommandHandler
    public OrderAggregate(CreateOrderCommand command) {


        logger.info("Handling CreateOrderCommand [{}]", command.toString());
        OrderCreatedEvent orderCreatedEvent = buildOrderCreatedEventFrom(command);
        AggregateLifecycle.apply(orderCreatedEvent);
    }

    @CommandHandler
    public void approvedOrderHandler(ApproveOrderCommand command) {


        logger.info("Handling ApproveOrderCommand [{}]", command.toString());
        OrderApprovedEvent orderApprovedEvent = new OrderApprovedEvent(command.getOrderId());
        AggregateLifecycle.apply(orderApprovedEvent);
    }

    @CommandHandler
    public void rejectOrderHandler(RejectOrderCommand command) {

        logger.info("Handling RejectOrderCommand [{}]", command.toString());
        OrderApprovedEvent orderApprovedEvent = new OrderApprovedEvent(command.getOrderId());
        AggregateLifecycle.apply(orderApprovedEvent);
    }

    @EventSourcingHandler
    public void handler(OrderCreatedEvent command) {
        this.orderId = command.getOrderId();
        this.productId = command.getProductId();
        this.userId = command.getUserId();
        this.quantity = command.getQuantity();
        this.addressId = command.getAddressId();
        this.orderStatus = command.getOrderStatus();
        logger.info("Order with id [{}] has been Created!", command.getOrderId());

    }

    @EventSourcingHandler
    public void handler(OrderApprovedEvent command) {
        this.orderStatus = command.getOrderStatus();
        logger.info("Order with id [{}] has been Rejected!", command.getOrderId());

    }
    @EventSourcingHandler
    public void orderApprovedHandler(OrderApprovedEvent command) {
        this.orderStatus = command.getOrderStatus();
        logger.info("Order with id [{}] has been Approved!", command.getOrderId());
    }


}
