package crk.study.orders.infra.commands.approveorder;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@Builder
@ToString
public class ApproveOrderCommand {

    @TargetAggregateIdentifier
    private String orderId;
}
