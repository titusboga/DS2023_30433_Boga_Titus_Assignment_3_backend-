package com.project.device;

import com.project.device.model.dto.DeviceDTO;
import com.project.device.model.Device;
import com.project.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final DeviceMapper deviceMapper;

    public Device findById(Long id) {
        return deviceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Device not found: " + id));
    }

    public List<DeviceDTO> findAll(){
        return deviceRepository.findAll().stream().map(deviceMapper::toDto).collect(Collectors.toList());

    }

   /* public List<DeviceDTO> findAllOnline() {

        ArrayList<DeviceDTO> music= new ArrayList<>();
        try {
            System.out.println("Songs online");
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://spotify23.p.rapidapi.com/search/?q=%3CREQUIRED%3E&type=multi&offset=0&limit=10&numberOfTopResults=5"))
                    .header("X-RapidAPI-Host", "spotify23.p.rapidapi.com")
                    .header("X-RapidAPI-Key", "6aed63ec0cmsh6fbb66974da5728p182777jsn4fce90f2afe4")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            JSONObject responseJsonObj = new JSONObject(response.body().toString());
            JSONArray songs=responseJsonObj.getJSONObject("tracks").getJSONArray("items");
            for(int i=0;i<10;++i)
            {
                String name=songs.getJSONObject(i).getJSONObject("data").getString("name");
                String artist= songs.getJSONObject(i).getJSONObject("data").getJSONObject("artists").getJSONArray("items").getJSONObject(0).getJSONObject("profile").getString("name");
                DeviceDTO deviceDTO = new DeviceDTO(0L,name,artist);
                music.add(deviceDTO);
            }
        } catch (Exception e)
        {
            System.out.println(e);
        }
        return music;
    }*/

    public byte [] getSongMp3(Long id)
    {
        System.out.println(id);
        try {
            File f = new File("resources/music.mp3");
            return Files.readAllBytes(f.toPath());
        } catch (Exception e)
        {
            System.out.println(e);
        }
        return null;
    }

    public DeviceDTO create(DeviceDTO song) {
        return deviceMapper.toDto(deviceRepository.save(
                deviceMapper.fromDto(song)
        ));
    }

    public DeviceDTO edit(Long id, DeviceDTO song) {
        Device actDevice = findById(id);
        actDevice.setAddress(song.getAddress());
        actDevice.setDescription(song.getDescription());
        actDevice.setUser(new User(song.getUserId(),"","","", null));
        return deviceMapper.toDto(
                deviceRepository.save(actDevice)
        );
    }

    public void delete(Long id) {
        deviceRepository.deleteById(id);
    }
}
