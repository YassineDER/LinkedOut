package org.ichat.backend.model.util.patchers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminPatch {
    private String first_name;
    private String last_name;
    private String phone;
    private String title;
}
