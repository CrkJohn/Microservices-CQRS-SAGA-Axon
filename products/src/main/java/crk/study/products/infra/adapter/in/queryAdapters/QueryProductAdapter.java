package crk.study.products.infra.adapter.in.queryAdapters;


import crk.study.products.domain.adapters.in.ProductResponse;
import crk.study.products.domain.adapters.in.createproduct.mapper.GetProductInformation;
import crk.study.products.domain.queries.GetProductByTitleQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/v1")
@RequiredArgsConstructor
public class QueryProductAdapter {

    private final GetProductInformation productUseCase;

    private final QueryGateway queryGateway;

    @GetMapping(value = "/title/{title}")
    public List<ProductResponse> getProductByTitle(@PathVariable String title) {

        GetProductByTitleQuery query = GetProductByTitleQuery.builder().title(title).build();
        return queryGateway.query(query, ResponseTypes.multipleInstancesOf(ProductResponse.class))
                .join();
    }

}
