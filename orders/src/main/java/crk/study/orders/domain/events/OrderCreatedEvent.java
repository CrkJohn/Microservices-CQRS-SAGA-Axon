package crk.study.orders.domain.events;

import crk.study.orders.domain.model.OrderStatus;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderCreatedEvent {

    private String orderId;
    private String productId;
    private String userId;
    private int quantity;
    private String addressId;
    private OrderStatus orderStatus;
}
