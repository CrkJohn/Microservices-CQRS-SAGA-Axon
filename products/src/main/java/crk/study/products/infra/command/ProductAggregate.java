package crk.study.products.infra.command;

import crk.study.core.commands.CancelProductReservationCommand;
import crk.study.core.commands.ReserveProductCommand;
import crk.study.core.events.ProductReservationCanceledEvent;
import crk.study.core.events.ProductReservedEvent;
import crk.study.products.domain.commands.CreateProductCommand;
import crk.study.products.domain.events.ProductCreated;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aggregate
@NoArgsConstructor
public class ProductAggregate {

    @AggregateIdentifier
    private String productId;
    private String title;
    private double price;
    private int quantity;

    private final static Logger logger = LoggerFactory.getLogger(ProductAggregate.class);


    @CommandHandler
    public ProductAggregate(CreateProductCommand command) {

        ProductCreated createdProduct = ProductCreated.builder()
                .title(command.getTitle())
                .quantity(command.getQuantity())
                .price(command.getPrice())
                .productId(command.getProductId())
                .build();
        AggregateLifecycle.apply(createdProduct);
    }

    @CommandHandler
    public void handle(ReserveProductCommand reserveProductCommand) {

        if (reserveProductCommand.getQuantity() > quantity) {
            throw new IllegalArgumentException("Insufficient number of item in stock");
        }

        ProductReservedEvent productReservedEvent = ProductReservedEvent.builder()
                .quantity(reserveProductCommand.getQuantity())
                .productId(reserveProductCommand.getProductId())
                .userId(reserveProductCommand.getUserId())
                .orderId(reserveProductCommand.getOrderId())
                .build();

        AggregateLifecycle.apply(productReservedEvent);
    }

    @CommandHandler
    public void handle(CancelProductReservationCommand reserveProductCommand) {

        ProductReservationCanceledEvent productReservationCanceledEvent = ProductReservationCanceledEvent.builder()
                .quantity(reserveProductCommand.getQuantity())
                .productId(reserveProductCommand.getProductId())
                .userId(reserveProductCommand.getUserId())
                .orderId(reserveProductCommand.getOrderId())
                .build();

        AggregateLifecycle.apply(productReservationCanceledEvent);
    }


    @EventSourcingHandler
    public void productCreatedHandler(ProductCreated productCreated) {
        this.productId = productCreated.getProductId();
        this.title = productCreated.getTitle();
        this.price = productCreated.getPrice();
        this.quantity = productCreated.getQuantity();
    }

    @EventSourcingHandler
    public void productReservedEventHandler(ProductReservedEvent productReservedEvent) {
        this.productId = productReservedEvent.getProductId();
        this.quantity -= productReservedEvent.getQuantity();
    }

    @EventSourcingHandler
    public void productReservationCanceledEventHandler(ProductReservationCanceledEvent e) {
        this.quantity += e.getQuantity();
    }

}
