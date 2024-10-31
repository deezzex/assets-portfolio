package com.deezzex.user.mapper;

import com.deezzex.user.dto.GetUserDto;
import com.deezzex.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {
    GetUserDto toGetUserDto(User user);
}
