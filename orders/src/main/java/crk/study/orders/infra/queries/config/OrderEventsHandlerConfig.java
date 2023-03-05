package crk.study.orders.infra.queries.config;

import crk.study.orders.domain.adapter.out.repository.OrdersRepository;
import crk.study.orders.infra.adapter.out.repository.OrderRepositoryH2;
import crk.study.orders.infra.adapter.out.repository.OrderRepositoryImpl;
import crk.study.orders.infra.queries.OrderEventsHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderEventsHandlerConfig {


    @Bean
    public OrderEventsHandler getOrderEventsHandler(OrdersRepository ordersRepository) {

        return new OrderEventsHandler(ordersRepository);
    }

    @Bean
    public OrdersRepository getOrdersRepository(OrderRepositoryH2 orderRepositoryH2) {

        return new OrderRepositoryImpl(orderRepositoryH2);
    }
}
