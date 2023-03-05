package crk.study.products;

import crk.study.products.infra.adapter.in.exception.ProductServiceEventErrorHandler;
import crk.study.products.infra.command.interceptors.ProductCommandInterceptor;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.config.EventProcessingConfiguration;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

@EnableDiscoveryClient
@SpringBootApplication
public class ProductsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductsApplication.class, args);
    }

    @Autowired
    public void registerProductCommandInterceptor(ApplicationContext applicationContext, CommandBus commandBus) {

        commandBus.registerDispatchInterceptor(applicationContext.getBean(ProductCommandInterceptor.class));
    }

    @Autowired
    public void configure(EventProcessingConfigurer configuration){

        // I can use this event process and configure object to register the listener
        //invocation air handler for a specific processing group.
        configuration.registerListenerInvocationErrorHandler("product-group", configuration1 ->
                new ProductServiceEventErrorHandler());


    }
}
