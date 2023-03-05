package crk.study.products.domain.adapters.in.createproduct.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateProduct {
    private String title;
    private double price;
    private int quantity;
}
