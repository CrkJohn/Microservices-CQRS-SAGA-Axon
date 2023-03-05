package crk.study.products.domain.data;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;

@Data
@Entity(name = "products")
@ToString
public class ProductEntity {

    @Id
    @Column(unique = true, name = "id")
    private String productId;

    @Column(unique = true, name = "title")
    private String title;
    private double price;
    private int quantity;
}
