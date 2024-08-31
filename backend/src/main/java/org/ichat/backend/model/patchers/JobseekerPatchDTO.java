package org.ichat.backend.model.patchers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JobseekerPatchDTO is a class that represents the data transfer object for the jobseeker patch request.
 * It contains the fields that can be updated in a jobseeker:
 * <ul>
 *     <li>first_name</li>
 *     <li>last_name</li>
 *     <li>address</li>
 *     <li>phone</li>
 *     </ul>
 *     @see org.ichat.backend.model.tables.Jobseeker
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobseekerPatchDTO {
    private String first_name;
    private String last_name;
    private String address;
    private String phone;
}
