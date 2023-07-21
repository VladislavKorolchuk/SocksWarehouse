package ru.warehouse.socks.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.warehouse.socks.dto.SocksDto;
import ru.warehouse.socks.entity.Socks;

@Mapper(componentModel = "spring")
public interface SocksMapper {

    @Mapping(target = "id", ignore = true)
    Socks toEntity(SocksDto socksDto);

}
