package com.jobportal.jobportal.entitiy;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class JobPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String location;
    private String salaryRange;
    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonBackReference("company-jobpost")
    private Company company;
    private Date postedDate;
}
