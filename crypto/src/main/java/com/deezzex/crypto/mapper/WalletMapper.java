package com.deezzex.crypto.mapper;

import com.deezzex.crypto.dto.wallet.GetWalletDto;
import com.deezzex.crypto.entity.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface WalletMapper {

    @Mapping(target = "token", source = "wallet.token.name")
    @Mapping(target = "blockchain", source = "wallet.blockchain.name")
    GetWalletDto toGetWalletDto(Wallet wallet);
}
