package crk.study.orders.infra.adapter.out.repository;

import crk.study.orders.domain.adapter.out.repository.OrdersRepository;
import crk.study.orders.infra.adapter.out.repository.data.OrderEntity;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrdersRepository {


    private final OrderRepositoryH2 orderRepositoryH2;

    @Override
    public void save(OrderEntity order) {

        orderRepositoryH2.save(order);
    }

    @Override
    public Optional<OrderEntity> findByOrderId(String orderId) {
        return orderRepositoryH2.findById(orderId);
    }


}
