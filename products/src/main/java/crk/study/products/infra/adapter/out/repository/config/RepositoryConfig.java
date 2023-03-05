package crk.study.products.infra.adapter.out.repository.config;


import crk.study.products.infra.adapter.out.repository.ProductH2Repository;
import crk.study.products.infra.adapter.out.repository.ProductInMemory;
import crk.study.products.domain.adapters.out.ProductRepository;
import crk.study.products.infra.adapter.out.repository.ProductRepositoryJpa;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class RepositoryConfig {


    @Bean("inMemoryH2")
    public ProductRepository getProductRepositoryH2(ProductRepositoryJpa productRepositoryJpa) {

        return new ProductH2Repository(productRepositoryJpa);
    }
}
