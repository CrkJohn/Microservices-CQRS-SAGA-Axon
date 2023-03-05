package crk.study.orders.domain.adapter.out.repository;

import crk.study.orders.infra.adapter.out.repository.data.OrderEntity;

import java.util.Optional;

public interface OrdersRepository  {

    void save(OrderEntity order);

    Optional<OrderEntity> findByOrderId(String orderId);
}
