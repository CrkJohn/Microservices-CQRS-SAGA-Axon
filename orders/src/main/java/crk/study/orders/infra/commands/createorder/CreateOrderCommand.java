package crk.study.orders.infra.commands.createorder;

import crk.study.orders.domain.model.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Getter
@ToString
public class CreateOrderCommand {


    @TargetAggregateIdentifier
    public final String orderId;
    private final String userId;
    private final String productId;
    private final int quantity;
    private final String addressId;
    private final OrderStatus orderStatus;
}
