package crk.study.products.domain.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity(name = "ProductLockup")
@NoArgsConstructor
public class ProductLockupEntity {

    @Id
    @Column(unique = true, name = "id")
    private String productId;

    @Column(unique = true, name = "title")
    private String title;

    public ProductLockupEntity(String productId, String title) {

        this.productId = productId;
        this.title = title;
    }
}
