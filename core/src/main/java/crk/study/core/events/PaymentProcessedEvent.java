package crk.study.core.events;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PaymentProcessedEvent {

    private final String orderId;

    private final String paymentId;
}
