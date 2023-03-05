package crk.study.products.infra.query.config;

import crk.study.products.infra.adapter.out.repository.ProductRepositoryJpa;
import crk.study.products.infra.query.ProductEventsHandlers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductEventsHandlersConfig {

    @Bean
    public ProductEventsHandlers productEventsHandlers(ProductRepositoryJpa productRepository) {

        return new ProductEventsHandlers(productRepository);
    }
}
