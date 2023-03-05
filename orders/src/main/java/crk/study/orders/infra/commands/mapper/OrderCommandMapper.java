package crk.study.orders.infra.commands.mapper;

import crk.study.orders.infra.commands.createorder.CreateOrderCommand;
import crk.study.orders.domain.adapter.in.createorder.dto.CreateOrderRequest;

public class OrderCommandMapper {

    public static final String USER_ID = "27b95829-4f3f-4ddf-8983-151ba010e35b";

    public static CreateOrderCommand buildCreateOrderCommandFrom(CreateOrderRequest orderRequest, String uuid) {

        return CreateOrderCommand.builder()
                .addressId(orderRequest.getAddressId())
                .quantity(orderRequest.getQuantity())
                .productId(orderRequest.getProductId())
                .orderId(uuid)
                .userId(USER_ID)
                .build();
    }
}
