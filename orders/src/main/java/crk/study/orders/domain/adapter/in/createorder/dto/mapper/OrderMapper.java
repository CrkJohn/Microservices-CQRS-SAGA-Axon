package crk.study.orders.domain.adapter.in.createorder.dto.mapper;

import crk.study.orders.domain.adapter.in.createorder.dto.CreateOrderRequest;
import crk.study.orders.domain.adapter.in.createorder.dto.CreateOrderResponse;

public class OrderMapper {


    public static CreateOrderResponse buildCreateOrderResponseFrom(CreateOrderRequest orderRequest, String uuid) {

        return CreateOrderResponse.builder()
                .addressId(orderRequest.getAddressId())
                .quantity(orderRequest.getQuantity())
                .productId(orderRequest.getProductId())
                .orderId(uuid)
                .build();
    }
}
