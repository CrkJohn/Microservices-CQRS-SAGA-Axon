package crk.study.products.infra.adapter.in.commandsAdapters;


import crk.study.products.domain.commands.CreateProductCommand;
import crk.study.products.domain.adapters.in.createproduct.dto.CreateProduct;
import crk.study.products.domain.adapters.in.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/v1")
@RequiredArgsConstructor
public class CommandProductAdapter {
    private final CommandGateway commandGateway;

    @PostMapping
    public ProductResponse saveProduct(@RequestBody CreateProduct createProduct) {

        final String id = UUID.randomUUID().toString();
        CreateProductCommand command = CreateProductCommand
                .builder()
                .title(createProduct.getTitle())
                .quantity(createProduct.getQuantity())
                .price(createProduct.getPrice())
                .productId(id)
                .build();
        commandGateway.sendAndWait(command);
        return ProductResponse.builder()
                .title(createProduct.getTitle())
                .quantity(createProduct.getQuantity())
                .price(createProduct.getPrice())
                .id(id)
                .build();
    }

}
