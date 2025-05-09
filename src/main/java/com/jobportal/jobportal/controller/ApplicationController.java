package com.jobportal.jobportal.controller;


import com.jobportal.jobportal.dao.ApplicantDao;
import com.jobportal.jobportal.entitiy.*;
import com.jobportal.jobportal.service.ApplicantService;
import com.jobportal.jobportal.service.ApplicationService;
import com.jobportal.jobportal.service.CompanyService;
import com.jobportal.jobportal.service.JobPostService;
import com.jobportal.jobportal.user.User;
import com.jobportal.jobportal.user.UserService;
import com.jobportal.jobportal.util.JobsMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class ApplicationController {

    final ApplicationService applicationService;
    final ApplicantService applicantService;
    final UserService userService;
    final CompanyService companyService;
    final JobPostService jobPostService;


    @PreAuthorize("hasRole('ROLE_COMPANY')")
    @GetMapping("jobs/{id}/applications")
    public ResponseEntity<Page<Application>> getApplicationsByJobId(
            @RequestParam(value = "_limit", required = false, defaultValue = "4")Integer perPage,
            @RequestParam(value = "_page", required = false, defaultValue = "1")Integer page,
            @PathVariable("id")Long id
    ){

        Pageable pageable = PageRequest.of(page - 1, perPage);
        Page<Application> output = applicationService.findByJobPostId(id, pageable);

        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("X-Total-Count", String.valueOf(output.getTotalElements()));
        return new ResponseEntity<>(output, responseHeader, HttpStatus.OK);


    }


    @PreAuthorize("hasRole('ROLE_COMPANY')")
    @PutMapping("jobs/{job-id}/applications/{app-id}/shortlist")
    public ResponseEntity<?> shortlistApplication(
            @PathVariable("job-id")Long jobId,
            @PathVariable("app-id")Long appId,
            Principal principal
    ){

        try {
            Application shortlistedApplication = applicationService.shortlistApplication(jobId, appId, principal.getName());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(shortlistedApplication);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ROLE_COMPANY')")
    @PutMapping("jobs/{job-id}/applications/{app-id}/reject")
    public ResponseEntity<?> rejectApplication(
            @PathVariable("job-id")Long jobId,
            @PathVariable("app-id")Long appId,
            Principal principal
    ){

        try {
            Application rejectedApplication = applicationService.rejectApplication(jobId, appId, principal.getName());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(rejectedApplication);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }


    @PreAuthorize("hasRole('ROLE_APPLICANT')")
    @PostMapping("jobs/{id}/submit")
    public ResponseEntity<?> submitApplication(
            @PathVariable("id")Long id,
            Principal principal
    ){

        Application persistedApplication = applicationService.submitApplication(id, principal.getName());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(persistedApplication);
    }


    @PreAuthorize("hasRole('ROLE_APPLICANT')")
    @GetMapping("jobs/applications/me")
    public ResponseEntity<?> getApplicationByApplicant(
            Principal principal
    ){

        User currentUser = userService.findByUsername(principal.getName());
        Applicant applicant = applicantService.findByUserId(currentUser.getId());
        List<Application> output = applicationService.findByApplicantId(applicant.getId());

        if (output.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No application found with Applicant Id: " + applicant.getId());
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(output);
    }
}
