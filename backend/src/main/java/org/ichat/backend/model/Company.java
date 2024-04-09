package org.ichat.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("C")
public class Company extends User {
    @Column(nullable = false)
    @NotEmpty(message = "Company name is required")
    String company_name;

    String description;

    @Column(nullable = false)
    @NotEmpty(message = "Capital is required")
    int capital;

    @Column(nullable = false)
    @NotEmpty(message = "Headquarters is required")
    String headquarters;

    @Column(nullable = false)
    @NotEmpty(message = "Founded date is required")
    LocalDate foundedDate;

    @Column(nullable = false)
    @NotEmpty(message = "Sector is required")
    String sector;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Job> availableJobs;

}
