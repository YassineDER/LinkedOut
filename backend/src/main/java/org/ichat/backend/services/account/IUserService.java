package org.ichat.backend.services.account;

import org.ichat.backend.model.tables.User;
import org.ichat.backend.repository.account.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {
    /**
     * Delete user by id
     * @param id id of user
     */
    void deleteBy(Long id);

    /**
     * Find user by id
     * @param id id of user
     * @return user
     */
    User findBy(Long id);

    /**
     * Find user by email
     * @param email email of user
     * @return user
     */
    User findBy(String email);

    /**
     * Get user repository (used by {@link org.ichat.backend.config.security.MvcConfiguration})
     * @return user repository
     */
    UserRepository getUserRepo();

    /**
     * Update user
     * @param userId id of user
     * @param newUser new user
     * @return updated user
     */
    User update(Long userId, User newUser);

    /**
     * Find suggested users based on their roles. <br>
     * The result is limited to 6 users and randomly selected.
     *
     * @param excluded user to exclude
     * @param pageable
     * @return set of suggested users
     */
    Page<User> findSuggested(User excluded, Pageable pageable);

    /**
     * Compact user's data, by removing all the data that is not necessary. <br>
     * Removed data:
     * <ul>
     *     <li>password</li>
     *     <li>if user is using mfa</li>
     *     <li>mfa secret</li>
     *     <li>MFA secret</li>
     *     <li>created at</li>
     *     <li>profile</li>
     *     <li>email verifications</li>
     *     <li>password resets</li>
     *     <li>if user is enabled</li>
     * </ul>
     * @param user the user to compact
     */
    void compact(User user);

    boolean existsByImage(String imagePath);
}
