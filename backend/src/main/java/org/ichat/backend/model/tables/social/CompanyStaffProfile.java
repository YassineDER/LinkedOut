package org.ichat.backend.model.tables.social;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * CompanyStaffProfile class is a subclass of Profile class. It is used to represent the profile of a company or an admin.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("COMPANY_STAFF")
public class CompanyStaffProfile extends Profile {
    

}
