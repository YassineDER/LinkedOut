package org.ichat.backend.repository.social;

import org.ichat.backend.model.tables.social.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    @Query("SELECT DISTINCT p FROM Profile p WHERE p.profileId IN " +
            "(SELECT c.sender.profileId FROM Connection c WHERE c.receiver.profileId = :profileId " +
            "UNION " +
            "SELECT c.receiver.profileId FROM Connection c WHERE c.sender.profileId = :profileId)")
    Page<Profile> findAllConnectedProfiles(@Param("profileId") Long profileId, Pageable pageable);


}
