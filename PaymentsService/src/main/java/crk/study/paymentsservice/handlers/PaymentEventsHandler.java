package crk.study.paymentsservice.handlers;


import crk.study.core.events.PaymentProcessedEvent;
import crk.study.paymentsservice.repository.PaymentRepository;
import crk.study.paymentsservice.repository.entity.PaymentEntity;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentEventsHandler {

    private PaymentRepository paymentRepository;

    private final static Logger logger = LoggerFactory.getLogger(PaymentEventsHandler.class);

    @Autowired
    public PaymentEventsHandler(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }


    @EventHandler
    public void paymentProcessedHandler(PaymentProcessedEvent paymentProcessedEvent) {

        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setPaymentId(paymentProcessedEvent.getPaymentId());
        paymentEntity.setOrderId(paymentProcessedEvent.getOrderId());
        paymentRepository.save(paymentEntity);
        logger.info("Payment with OrderId [{}] and PaymentId [{}] has been persisted ", paymentEntity.getOrderId(),
                paymentEntity.getPaymentId());
    }
}
