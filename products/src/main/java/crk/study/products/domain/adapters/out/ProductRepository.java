package crk.study.products.domain.adapters.out;

import crk.study.products.domain.model.Product;

import java.util.Optional;

public interface ProductRepository {

    Optional<Product> findById(String id);

    Product save(Product product);
}
