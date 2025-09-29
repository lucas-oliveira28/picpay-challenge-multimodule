package io.github.lucasoliveira28;

import io.github.lucasoliveira28.port.integration.AuthorizationIntegration;
import io.github.lucasoliveira28.port.integration.NotificationIntegration;
import io.github.lucasoliveira28.repository.TransactionRepository;
import io.github.lucasoliveira28.repository.UserRepository;
import io.github.lucasoliveira28.usecase.TransactionFlow;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "io.github.lucasoliveira28")
public class AppConfiguration {

    @Bean
    TransactionFlow transactionFlow(
            AuthorizationIntegration authorizationIntegration,
            NotificationIntegration notificationIntegration,
            TransactionRepository transactionRepository,
            UserRepository userRepository) {
        return new TransactionFlow(
                transactionRepository,
                userRepository,
                authorizationIntegration,
                notificationIntegration);
    }

}
