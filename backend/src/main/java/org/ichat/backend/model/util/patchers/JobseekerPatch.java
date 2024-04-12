package org.ichat.backend.model.util.patchers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobseekerPatch {
    private String first_name;
    private String last_name;
    private String address;
    private String phone;
    private String cv_url;
    private String image_url;
}
