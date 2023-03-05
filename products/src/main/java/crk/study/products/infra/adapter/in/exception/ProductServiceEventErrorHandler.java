package crk.study.products.infra.adapter.in.exception;

import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventhandling.EventMessageHandler;
import org.axonframework.eventhandling.ListenerInvocationErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;

public class ProductServiceEventErrorHandler implements ListenerInvocationErrorHandler {

    private final Logger logger = LoggerFactory.getLogger(ProductServiceEventErrorHandler.class);

    @Override
    public void onError(@Nonnull Exception exception, @Nonnull EventMessage<?> event, @Nonnull EventMessageHandler eventHandler) throws Exception {

        logger.error("ProductServiceEventErrorHandler error handling!!");
        throw exception;
    }
}
