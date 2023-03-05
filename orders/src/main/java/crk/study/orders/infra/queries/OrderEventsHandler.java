package crk.study.orders.infra.queries;


import crk.study.orders.domain.adapter.out.repository.OrdersRepository;
import crk.study.orders.domain.events.OrderApprovedEvent;
import crk.study.orders.domain.events.OrderCreatedEvent;
import crk.study.orders.domain.events.OrderRejectedEvent;
import crk.study.orders.infra.adapter.out.repository.data.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

@RequiredArgsConstructor
public class OrderEventsHandler {

    private final OrdersRepository ordersRepository;
    private final static Logger logger = LoggerFactory.getLogger(OrderEventsHandler.class);

    @EventHandler
    public void orderCreatedHandler(OrderCreatedEvent orderCreatedEvent) {

        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(orderCreatedEvent, orderEntity);
        ordersRepository.save(orderEntity);
        logger.info("Order with id [{}] has been created and persisted successful", orderEntity.getOrderId());
    }

    @EventHandler
    public void orderApprovedHandler(OrderApprovedEvent orderApprovedEvent) {

        Optional<OrderEntity> order = ordersRepository.findByOrderId(orderApprovedEvent.getOrderId());
        if (order.isPresent()){
            OrderEntity orderEntity = new OrderEntity();
            BeanUtils.copyProperties(orderApprovedEvent, orderEntity);
            ordersRepository.save(orderEntity);
            logger.info("Order with id [{}] has been finished and persisted successful", orderEntity.getOrderId());
        }
    }

    @EventHandler
    public void orderRejectedHandler(OrderRejectedEvent orderRejectedEvent) {

        Optional<OrderEntity> order = ordersRepository.findByOrderId(orderRejectedEvent.getOrderId());
        if (order.isPresent()){
            OrderEntity orderEntity = new OrderEntity();
            BeanUtils.copyProperties(orderRejectedEvent, orderEntity);
            ordersRepository.save(orderEntity);
            logger.info("Order with id [{}] has been rejected and persisted successful", orderEntity.getOrderId());
        }
    }
}
