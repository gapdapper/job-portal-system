package com.jobportal.jobportal.entitiy;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class JobPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    String description;
    String location;
    String salaryRange;
    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonBackReference("company-jobpost")
    Company company; // Many-to-One with CompanyProfile
    Date postedDate;
}
