package crk.study.products.domain.events;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProductCreated {

    private final String productId;
    private final String title;
    private final double price;
    private final int quantity;

    @Override
    public String toString() {
        return "ProductCreated{" +
                "productId='" + productId + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
