package io.github.lucasoliveira28.mapper;

import io.github.lucasoliveira28.dto.AuthorizationDTO;
import io.github.lucasoliveira28.dto.authorization.Authorization;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorizationMapper {

    Authorization toAuthorization(AuthorizationDTO authorizationDTO);

}
