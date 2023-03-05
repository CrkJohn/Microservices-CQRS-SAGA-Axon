package crk.study.products.infra.adapter.out.repository;

import crk.study.products.domain.data.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface  ProductRepositoryJpa extends JpaRepository<ProductEntity, String> {

    List<ProductEntity> findByTitle(String title);
}
