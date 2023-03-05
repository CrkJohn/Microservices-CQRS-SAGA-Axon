package crk.study.products.infra.query.config;

import crk.study.products.domain.adapters.out.ProductRepository;
import crk.study.products.domain.adapters.in.createproduct.mapper.GetProductInformation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetProductsConfig {


    @Bean
    public GetProductInformation getProductUseCase(ProductRepository productRepository) {

        return new GetProductInformation(productRepository);
    }
}
