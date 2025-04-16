package com.jobportal.jobportal.entitiy;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Application {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            @EqualsAndHashCode.Exclude
    Long id;
    @ManyToOne
    @JoinColumn(name = "job_post_id")
    @JsonBackReference("jobpost-application")
    private JobPost jobPost;
    @ManyToOne
    @JoinColumn(name = "applicant_id", referencedColumnName = "id")
    Applicant applicant;
    String status;
    Date appliedDate;
}
