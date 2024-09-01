package org.ichat.backend.services.social.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.model.tables.social.Profile;
import org.ichat.backend.services.social.IProfileService;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfileService implements IProfileService {
}
