package crk.study.products.infra.query;


import crk.study.core.events.ProductReservationCanceledEvent;
import crk.study.core.events.ProductReservedEvent;
import crk.study.products.domain.data.ProductEntity;
import crk.study.products.domain.events.ProductCreated;
import crk.study.products.infra.adapter.out.repository.ProductRepositoryJpa;
import crk.study.products.infra.command.interceptors.ProductCommandInterceptor;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

@RequiredArgsConstructor
@ProcessingGroup(value = "product-group")
public class ProductEventsHandlers {

    private final ProductRepositoryJpa productRepository;
    Logger logger = LoggerFactory.getLogger(ProductEventsHandlers.class);


    @ExceptionHandler(resultType = IllegalStateException.class)
    private void exceptionHandler(Exception ex) throws Exception {

        logger.error("There was an error on ProductEventsHandlers, error was [{}]", ex.getMessage());
        throw ex;
    }

    @EventHandler
    public void productCreatedHandler(ProductCreated productCreated) {

        ProductEntity product = new ProductEntity();
        BeanUtils.copyProperties(productCreated, product);
        productRepository.save(product);
    }

    @EventHandler
    public void productReservedEventHandler(ProductReservedEvent productReservedEvent) {

        Optional<ProductEntity> productEntity = productRepository.findById(productReservedEvent.getProductId());
        if (productEntity.isPresent()) {
            logger.debug("ProductReservedEvent: [{}]", productEntity.get().toString());
            ProductEntity product = productEntity.get();
            product.setQuantity(product.getQuantity() - productReservedEvent.getQuantity());
            productRepository.save(product);
            logger.debug("ProductReservedEvent: [{}]", product.toString());

        }
    }

    @EventHandler
    public void productReservationCanceledEventHandler(ProductReservationCanceledEvent productReservationCanceledEvent) {
        Optional<ProductEntity> productEntity = productRepository.findById(productReservationCanceledEvent.getProductId());
        if (productEntity.isPresent()) {
            logger.debug("ProductReservationCanceledEvent: [{}]", productEntity.get().toString());
            ProductEntity product = productEntity.get();
            product.setQuantity(product.getQuantity() + productReservationCanceledEvent.getQuantity());
            productRepository.save(product);
            logger.debug("ProductReservationCanceledEvent: [{}]", product.toString());

        }
    }

}
