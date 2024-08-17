package org.ichat.backend.model.patchers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobseekerPatchDTO {
    private String first_name;
    private String last_name;
    private String address;
    private String phone;
    private String cv_url;
    private String image_url;
}