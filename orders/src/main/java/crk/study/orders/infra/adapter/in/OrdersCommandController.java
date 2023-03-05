package crk.study.orders.infra.adapter.in;


import crk.study.orders.domain.adapter.in.createorder.dto.CreateOrderRequest;
import crk.study.orders.domain.adapter.in.createorder.dto.CreateOrderResponse;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static crk.study.orders.infra.commands.mapper.OrderCommandMapper.buildCreateOrderCommandFrom;
import static crk.study.orders.domain.adapter.in.createorder.dto.mapper.OrderMapper.buildCreateOrderResponseFrom;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "v1")
public class OrdersCommandController {


    private final CommandGateway commandGateway;

    @PostMapping
    public CreateOrderResponse createOrder(@RequestBody  CreateOrderRequest orderRequest) {


        String uuid = UUID.randomUUID().toString();
        String id = commandGateway.sendAndWait(buildCreateOrderCommandFrom(orderRequest, uuid));
        return buildCreateOrderResponseFrom(orderRequest, uuid);
    }

}
