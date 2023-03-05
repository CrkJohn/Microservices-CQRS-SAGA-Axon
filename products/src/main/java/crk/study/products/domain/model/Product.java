package crk.study.products.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Product {

    private String id;

    private String title;
    private double price;
    private int quantity;
}
