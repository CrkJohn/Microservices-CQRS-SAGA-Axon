package crk.study.orders.domain.events.mapper;

import crk.study.orders.infra.commands.createorder.CreateOrderCommand;
import crk.study.orders.domain.events.OrderCreatedEvent;

public class OrderEventsMapper {

    public static OrderCreatedEvent buildOrderCreatedEventFrom(CreateOrderCommand orderCommand) {

        return OrderCreatedEvent.builder()
                .addressId(orderCommand.getAddressId())
                .productId(orderCommand.getProductId())
                .quantity(orderCommand.getQuantity())
                .orderStatus(orderCommand.getOrderStatus())
                .orderId(orderCommand.getOrderId())
                .userId(orderCommand.getUserId())
                .build();

    }
}
