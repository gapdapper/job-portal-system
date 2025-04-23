package com.jobportal.jobportal.entitiy;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Application {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            @EqualsAndHashCode.Exclude
    private Long id;
    @ManyToOne
    @JoinColumn(name = "job_post_id")
    @JsonBackReference("jobpost-application")
    private JobPost jobPost;
    @ManyToOne
    @JoinColumn(name = "applicant_id", referencedColumnName = "id")
    private Applicant applicant;
    private String status;
    private Date appliedDate;
}
