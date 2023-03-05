package crk.study.products.infra.command.eventhandler;


import crk.study.products.domain.data.ProductLockupEntity;
import crk.study.products.domain.events.ProductCreated;
import crk.study.products.infra.adapter.out.repository.ProductLockUpRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup(value = "product-group")
public class ProductLockUpcEventsHandler {

    private final ProductLockUpRepository productLockUpRepository;

    @Autowired
    public ProductLockUpcEventsHandler(ProductLockUpRepository productLockUpRepository) {
        this.productLockUpRepository = productLockUpRepository;
    }

    @EventHandler
    public void handler(ProductCreated productCreated) {

        productLockUpRepository.save(new ProductLockupEntity(
                productCreated.getProductId(),
                productCreated.getTitle()));
    }

}
