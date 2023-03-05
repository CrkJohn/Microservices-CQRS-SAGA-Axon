package crk.study.paymentsservice.command;


import crk.study.core.commands.ProcessPaymentCommand;
import crk.study.core.events.PaymentProcessedEvent;
import crk.study.paymentsservice.handlers.PaymentEventsHandler;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aggregate
@NoArgsConstructor
public class PaymentAggregate {

    private String orderId;

    @AggregateIdentifier
    private String paymentId;

    private final static Logger logger = LoggerFactory.getLogger(PaymentEventsHandler.class);


    @CommandHandler
    public PaymentAggregate(ProcessPaymentCommand processPaymentCommand) {


        logger.info("Handling ProcessPaymentCommand [{}]", processPaymentCommand.toString());

        if (processPaymentCommand.getPaymentDetails().getCvv() == null) {

            throw new IllegalArgumentException();
        }

        PaymentProcessedEvent paymentProcessedEvent = PaymentProcessedEvent.builder()
                .orderId(processPaymentCommand.getOrderId())
                .paymentId(processPaymentCommand.getPaymentId())
                .build();
        AggregateLifecycle.apply(paymentProcessedEvent);
    }

    @EventSourcingHandler
    public void handler(PaymentProcessedEvent command) {

        this.orderId = command.getOrderId();
        this.paymentId = command.getPaymentId();
        logger.info("Payment with orderId [{}] and paymentId [{}] has been processed",
                command.getPaymentId(), command.getOrderId());
    }
}
