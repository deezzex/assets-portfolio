package com.deezzex.crypto.mapper;

import com.deezzex.crypto.dto.transaction.GetTransactionDto;
import com.deezzex.crypto.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface TransactionMapper {

    @Mapping(target = "fromWalletId", source = "transaction.fromWallet.id")
    @Mapping(target = "toWalletId", source = "transaction.toWallet.id")
    GetTransactionDto toGetTransactionDto(Transaction transaction);
}
