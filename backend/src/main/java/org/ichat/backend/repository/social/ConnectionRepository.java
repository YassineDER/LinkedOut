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

//    @Query("SELECT CASE WHEN c.sender.profileId = :profileId THEN c.receiver ELSE c.sender END " +
//       "FROM Connection c WHERE c.sender.profileId = :profileId OR c.receiver.profileId = :profileId")
//    Page<Profile> findConnectedProfiles(@Param("profileId") Long profileId, Pageable pageable);

//    Page<Profile> findDistinctBySenderOrReceiver(Profile sender, Profile receiver, Pageable pageable);

}
