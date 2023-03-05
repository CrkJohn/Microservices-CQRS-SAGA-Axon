package crk.study.orders.domain.adapter.in.createorder.dto;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateOrderResponse {

    private String productId;
    private Integer quantity;
    private String addressId;
    private String orderId;
}

