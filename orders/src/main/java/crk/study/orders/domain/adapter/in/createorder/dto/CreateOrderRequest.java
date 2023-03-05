package crk.study.orders.domain.adapter.in.createorder.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CreateOrderRequest {

    private String productId;
    private int quantity;
    private String addressId;
}
