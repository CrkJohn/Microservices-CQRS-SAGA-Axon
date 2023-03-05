package crk.study.products.infra.command.interceptors;

import crk.study.products.domain.commands.CreateProductCommand;
import crk.study.products.domain.data.ProductLockupEntity;
import crk.study.products.infra.adapter.out.repository.ProductLockUpRepository;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

@Component
public class ProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

    Logger logger = LoggerFactory.getLogger(ProductCommandInterceptor.class);

    private final ProductLockUpRepository productLockUpRepository;

    @Autowired
    public ProductCommandInterceptor(ProductLockUpRepository productLockUpRepository) {
        this.productLockUpRepository = productLockUpRepository;
    }

    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(
            @Nonnull List<? extends CommandMessage<?>> messages) {


        return (index, command) -> {
            if (CreateProductCommand.class.equals(command.getPayloadType())) {
                logger.info("We're in MessageDispatchInterceptor Uwu");

//                CreateProductCommand createProductCommand = (CreateProductCommand) command.getPayload();
//
//                Optional<ProductLockupEntity> productEntity =
//                        productLockUpRepository.findByProductIdOrTitle(createProductCommand.getProductId(), createProductCommand.getTitle());
//                if (productEntity.isPresent()) {
//                    throw new IllegalStateException("Product entity ready exist!");
//                }
            }
            return command;
        };
    }
}
