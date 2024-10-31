package com.deezzex.fiat.mapper;

import com.deezzex.fiat.entity.Account;
import com.deezzex.shared.dto.GetAccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AccountMapper {
    GetAccountDto toGetAccountDto(Account account);
}
