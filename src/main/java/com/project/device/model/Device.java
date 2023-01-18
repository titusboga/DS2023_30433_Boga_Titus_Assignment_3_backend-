package com.project.device.model;

import com.project.user.model.User;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String address;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


/*    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "device_playlist",
                joinColumns = @JoinColumn(name = "device_id"),
                inverseJoinColumns = @JoinColumn(name = "playlist_id"))
    @Builder.Default
    private Set<Playlist> playlists = new HashSet<>();
*/


}
