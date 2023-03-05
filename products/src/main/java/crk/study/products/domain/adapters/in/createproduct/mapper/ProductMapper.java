package crk.study.products.domain.adapters.in.createproduct.mapper;


import crk.study.products.domain.adapters.in.createproduct.dto.CreateProduct;
import crk.study.products.domain.adapters.in.ProductResponse;
import crk.study.products.domain.model.Product;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductMapper {

    public static ProductResponse buildProductResponseFrom(Product product) {

        return ProductResponse.builder()
                .id(product.getId())
                .title(product.getTitle())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
    }


    public static Product buildProductFrom(CreateProduct createProduct) {

        return Product.builder()
                .price(createProduct.getPrice())
                .quantity(createProduct.getQuantity())
                .title(createProduct.getTitle())
                .build();
    }
}
