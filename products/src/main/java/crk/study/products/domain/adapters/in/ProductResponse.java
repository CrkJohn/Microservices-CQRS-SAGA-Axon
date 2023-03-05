package crk.study.products.domain.adapters.in;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
public class ProductResponse {

    private String id;
    private String title;
    private double price;
    private int quantity;
}
