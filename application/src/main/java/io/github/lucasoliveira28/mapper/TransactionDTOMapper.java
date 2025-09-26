package io.github.lucasoliveira28.mapper;

import io.github.lucasoliveira28.dto.transaction.TransactionDTO;
import io.github.lucasoliveira28.dto.transaction.TransactionResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionDTOMapper {

    TransactionResponseDTO toTransactionResponseDTO(TransactionDTO transactionDTO);

}
