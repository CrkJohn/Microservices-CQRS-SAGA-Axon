package crk.study.products.infra.adapter.out.repository;


import crk.study.products.domain.adapters.out.ProductRepository;
import crk.study.products.domain.model.Product;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class ProductInMemory implements ProductRepository {

    private final List<Product> productList;


    @Override
    public Optional<Product> findById(String id) {
        return productList.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();
    }

    @Override
    public Product save(Product product) {

        String uuid = UUID.randomUUID().toString();
        product.setId(uuid);
        productList.add(product);
        return product;
    }
}
