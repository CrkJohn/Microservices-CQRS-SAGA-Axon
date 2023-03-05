package crk.study.core.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;


@Data
@Builder
public class CancelProductReservationCommand {
    @TargetAggregateIdentifier
    private final String productId;
    public final String orderId;
    private final String userId;
    private final int quantity;
    private final String reason;

}
