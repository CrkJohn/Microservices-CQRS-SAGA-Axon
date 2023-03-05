package crk.study.core.commands;


import crk.study.core.model.PaymentDetails;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;


@Data
@Builder
@ToString
public class ProcessPaymentCommand {

    @TargetAggregateIdentifier
    private final String orderId;
    private final String paymentId;
    private final PaymentDetails paymentDetails;
}
