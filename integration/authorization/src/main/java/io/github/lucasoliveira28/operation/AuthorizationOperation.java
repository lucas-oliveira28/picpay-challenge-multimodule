package io.github.lucasoliveira28.operation;

import feign.FeignException;
import io.github.lucasoliveira28.api.AuthorizationAPI;
import io.github.lucasoliveira28.dto.authorization.Authorization;
import io.github.lucasoliveira28.exceptions.TransactionNotAuthorizedException;
import io.github.lucasoliveira28.mapper.AuthorizationMapper;
import io.github.lucasoliveira28.port.integration.AuthorizationIntegration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthorizationOperation implements AuthorizationIntegration {

    private final AuthorizationAPI api;
    private final AuthorizationMapper mapper;

    @Override
    public Authorization getAuthorization() {
        try {
            var authorization = api.authorize();
            return mapper.toAuthorization(authorization);
        } catch (FeignException.Forbidden e) {
            throw new TransactionNotAuthorizedException("Transação não autorizada");
        }
    }
}
