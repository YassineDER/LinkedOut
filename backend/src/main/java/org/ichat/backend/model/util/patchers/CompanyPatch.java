package org.ichat.backend.model.util.patchers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyPatch {
    private String name;
    private String description;
    private String sector;
    private String headquarters;
    private String image_url;
}
