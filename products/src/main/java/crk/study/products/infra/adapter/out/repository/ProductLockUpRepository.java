package crk.study.products.infra.adapter.out.repository;

import crk.study.products.domain.data.ProductLockupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ProductLockUpRepository extends JpaRepository<ProductLockupEntity, String> {

    Optional<ProductLockupEntity> findByProductIdOrTitle(String productId, String title);
}
