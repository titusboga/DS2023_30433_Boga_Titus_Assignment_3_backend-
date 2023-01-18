package com.project.device.model.dto;

import com.project.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDTO {
    private Long id;
    private String description;
    private String address;
    private Long userId;
}
