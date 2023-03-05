package crk.study.products.infra.adapter.out.repository;

import crk.study.products.domain.adapters.out.ProductRepository;
import crk.study.products.domain.model.Product;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class ProductH2Repository implements ProductRepository {

    private final ProductRepositoryJpa productRepositoryJpa;
    @Override
    public Optional<Product> findById(String id) {
        return Optional.empty();
    }

    @Override
    public Product save(Product product) {
        return null;
    }
}
