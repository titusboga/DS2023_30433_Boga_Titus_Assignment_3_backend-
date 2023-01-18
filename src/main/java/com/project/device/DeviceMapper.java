package com.project.device;

import com.project.device.model.dto.DeviceDTO;
import com.project.device.model.Device;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DeviceMapper {
    @Mappings({
          //  @Mapping(target = "album", source = "album.title"),
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "address", source = "address"),
            @Mapping(target = "userId", source = "user.id")

    })
    DeviceDTO toDto(Device device);

    @Mappings({
           //@Mapping(target = "album.title", source = "album"),
           @Mapping(target = "description", source = "description"),
           @Mapping(target = "address", source = "address"),
           @Mapping(target = "user.id", source = "userId")

    })
    Device fromDto(DeviceDTO deviceDTO);

}
