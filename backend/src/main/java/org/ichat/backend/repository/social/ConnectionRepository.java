package org.ichat.backend.repository.social;

import org.ichat.backend.model.tables.social.Connection;
import org.ichat.backend.model.tables.social.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectionRepository extends JpaRepository<Connection, Long> {

//    @Query("SELECT CASE WHEN c.sender = :profile THEN c.receiver ELSE c.sender END " +
//            "FROM Connection c WHERE c.sender.profileId = :profile OR c.receiver.profileId = :profile")
//    Page<Profile> findConnectedProfiles(@Param("profile") Long profile_id, Pageable pageable);


}
