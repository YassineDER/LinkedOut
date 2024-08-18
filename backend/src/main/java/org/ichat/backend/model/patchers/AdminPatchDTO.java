package org.ichat.backend.model.patchers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AdminPatchDTO is a data transfer object that is used to update the admin's information.
 * This patch contains the following fields:
 * <ul>
 *     <li>first_name</li>
 *     <li>last_name</li>
 *     <li>phone</li>
 *     <li>title</li>
 * </ul>
 * <p>
 * The fields are optional and can be updated individually.
 * @see org.ichat.backend.model.tables.Admin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminPatchDTO {
    private String first_name;
    private String last_name;
    private String phone;
    private String title;
}
