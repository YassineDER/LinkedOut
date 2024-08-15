package org.ichat.backend.model.tables.social;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("COMPANY_STAFF")
public class CompanyStaffProfile extends Profile {
    

}
