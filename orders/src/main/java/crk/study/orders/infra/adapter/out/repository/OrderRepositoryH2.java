package crk.study.orders.infra.adapter.out.repository;

import crk.study.orders.infra.adapter.out.repository.data.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepositoryH2 extends JpaRepository<OrderEntity, String> {
}
