package org.ichat.backend.model.patchers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CompanyPatchDTO is a class that represents the data transfer object for the company patch request.
 * It contains the fields that can be updated in a company:
 * <ul>
 *     <li>name</li>
 *     <li>description</li>
 *     <li>sector</li>
 *     <li>headquarters</li>
 *     <li>website</li>
 *     </ul>
 * @see org.ichat.backend.model.tables.Company
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyPatchDTO {
    private String name;
    private String description;
    private String sector;
    private String headquarters;
    private String website;
}
