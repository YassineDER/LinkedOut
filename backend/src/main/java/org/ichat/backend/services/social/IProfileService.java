package org.ichat.backend.services.social;

import org.ichat.backend.model.tables.social.Connection;
import org.ichat.backend.model.tables.social.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProfileService {
    Connection connect(Profile source, Long other_profile_id);

    Page<Profile> getConnectedProfiles(Profile profile, Pageable pageable);

    Page<Connection> getConnections(Pageable pageable);
}
