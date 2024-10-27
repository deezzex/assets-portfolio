package com.deezzex.fiat.mapper;

import com.deezzex.fiat.dto.report.GetReportDto;
import com.deezzex.fiat.entity.Report;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ReportMapper {

    @Mapping(target = "accountId", source = "report.account.id")
    GetReportDto toGetReportDto(Report report);
}
