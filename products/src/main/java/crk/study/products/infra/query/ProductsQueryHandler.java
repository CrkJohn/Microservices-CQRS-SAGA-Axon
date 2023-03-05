package crk.study.products.infra.query;


import crk.study.products.domain.adapters.in.ProductResponse;
import crk.study.products.infra.adapter.out.repository.ProductRepositoryJpa;
import crk.study.products.domain.queries.GetProductByTitleQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductsQueryHandler {

    private final ProductRepositoryJpa productRepositoryJpa;

    @QueryHandler
    public List<ProductResponse> findByTitle(GetProductByTitleQuery query) {

        return productRepositoryJpa.findAll()
                .stream()
                .map(productEntity -> ProductResponse.builder()
                        .title(productEntity.getTitle())
                        .price(productEntity.getPrice())
                        .id(productEntity.getProductId())
                        .build())
                .collect(Collectors.toList());
    }
}
