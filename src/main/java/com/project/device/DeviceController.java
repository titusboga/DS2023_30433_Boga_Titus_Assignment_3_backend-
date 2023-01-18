package com.project.device;

import com.project.device.model.dto.DeviceDTO;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.project.UrlMapping.*;

@RestController
@RequestMapping(DEVICES)
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @GetMapping
    public List<DeviceDTO> allDevices() {
        return deviceService.findAll();
    }

    @PostMapping
    public DeviceDTO create(@RequestBody DeviceDTO device) {
        return deviceService.create(device);
    }

    @PatchMapping(DEVICE_EDIT)
    public DeviceDTO edit(@PathVariable Long id, @RequestBody DeviceDTO device) {
        return deviceService.edit(id, device);
    }

    @DeleteMapping(DEVICE_DEL)
    public void delete(@PathVariable Long id) {
        deviceService.delete(id);
    }

}
