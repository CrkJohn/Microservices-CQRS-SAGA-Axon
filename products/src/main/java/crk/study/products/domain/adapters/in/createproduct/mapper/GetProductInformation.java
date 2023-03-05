package crk.study.products.domain.adapters.in.createproduct.mapper;

import crk.study.products.domain.adapters.in.gerproduct.dto.GetProductById;
import crk.study.products.domain.adapters.out.ProductRepository;
import crk.study.products.domain.adapters.UseCase;
import crk.study.products.domain.adapters.in.ProductResponse;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class GetProductInformation implements UseCase<GetProductById, ProductResponse> {

    private final ProductRepository repository;

    @Override
    public ProductResponse execute(GetProductById command) {
        return repository.findById(command.getId())
                .map(ProductMapper::buildProductResponseFrom)
                .orElse(ProductResponse.builder().build());
    }
}
