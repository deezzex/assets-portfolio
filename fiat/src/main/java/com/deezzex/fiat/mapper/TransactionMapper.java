package com.deezzex.fiat.mapper;

import com.deezzex.fiat.dto.transaction.GetTransactionDto;
import com.deezzex.fiat.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface TransactionMapper {

    @Mapping(target = "accountId", source = "transaction.account.id")
    @Mapping(target = "debitCreditIndicator", source = "transaction.debitCreditIndicator.value")
    GetTransactionDto toGetTransactionDto(Transaction transaction);
}
