package org.ichat.backend.controller;

import lombok.RequiredArgsConstructor;
import org.ichat.backend.model.tables.User;
import org.ichat.backend.model.tables.social.Connection;
import org.ichat.backend.model.tables.social.Profile;
import org.ichat.backend.model.util.social.ConnectResponse;
import org.ichat.backend.services.social.IProfileService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/social")
public class SocialController {
    private final IProfileService profileService;

    @PostMapping("/connect")
    public ResponseEntity<ConnectResponse> connect(User me, @RequestParam("profile_id") Long other_profile_id) {
        var connection = profileService.connect(me.getProfile(), other_profile_id);
        return ResponseEntity.ok(new ConnectResponse(connection, true));
    }

    @GetMapping("/profiles/connected")
    public Page<Profile> connectedProfiles(User me, Pageable pageable) {
        var myprofile = me.getProfile();
        return profileService.getConnectedProfiles(myprofile, pageable);
    }

    @GetMapping("/profiles/connected/check")
    public ResponseEntity<Boolean> isConnected(User me, @RequestParam("profile_id") Long other_profile_id) {
        var isConnected = profileService.isConnected(me.getProfile(), other_profile_id);
        return ResponseEntity.ok(isConnected);
    }

    @GetMapping("/connections")
    public Page<Connection> connections(Pageable pageable) {
        return profileService.getConnections(pageable);
    }
}
