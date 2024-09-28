package org.ichat.backend.services.social.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.exception.SocialException;
import org.ichat.backend.model.tables.social.Connection;
import org.ichat.backend.model.tables.social.Profile;
import org.ichat.backend.repository.social.ConnectionRepository;
import org.ichat.backend.repository.social.ProfileRepository;
import org.ichat.backend.services.social.IProfileService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfileService implements IProfileService {
    private final ProfileRepository profileRepo;
    private final ConnectionRepository connectionRepo;

    @Override
    public Connection connect(Profile source, Long other_profile_id) {
        Profile other = profileRepo.findById(other_profile_id)
                .orElseThrow(() -> new SocialException("La connexion a échoué car le profil n'existe pas."));

        Connection connection = new Connection();
        connection.setSender(source);
        connection.setReceiver(other);
        return connectionRepo.save(connection);
    }

    @Override
    public Page<Profile> getConnectedProfiles(Profile profile, Pageable pageable) {
        return profileRepo.findAllConnectedProfiles(profile.getProfileId(), pageable);
    }

    @Override
    public Page<Connection> getConnections(Pageable pageable) {
        return connectionRepo.findAll(pageable);
    }
}
