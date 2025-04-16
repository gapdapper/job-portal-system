package com.jobportal.jobportal.controller;


import com.jobportal.jobportal.entitiy.JobPost;
import com.jobportal.jobportal.entitiy.JobPostDto;
import com.jobportal.jobportal.service.JobPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class JobPostController {

    final JobPostService jobPostService;
    @GetMapping("jobs")
    public ResponseEntity<Page<JobPost>> getJobPostLists(
            @RequestParam(value = "keyword", required = false)String keyword,
            @RequestParam(value = "_limit", required = false, defaultValue = "4")Integer perPage,
            @RequestParam(value = "_page", required = false, defaultValue = "1")Integer page,
            @RequestParam(value = "sortDirection", required = false, defaultValue = "asc") String sortDirection,
            @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy) {


        Sort sort = sortDirection.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page - 1, perPage, sort);

        Page<JobPost> output = jobPostService.getJobPosts(keyword, pageable);

        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("X-Total-Count", String.valueOf(output.getTotalElements()));
        return new ResponseEntity<>(output, responseHeader, HttpStatus.OK);
    }


    @GetMapping("jobs/{id}")
    public ResponseEntity<JobPost> getJobPost(@PathVariable("id") Long id) {
        JobPost job = jobPostService.findById(id);

        if (job != null) {
            return ResponseEntity.ok(job);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Job post with the given ID was not found.");
        }
    }

    @GetMapping("jobs/industry/{industry}")
    public ResponseEntity<Page<JobPost>> getJobPostByIndustry(
            @PathVariable("industry") String industry,
            @RequestParam(value = "_limit", required = false, defaultValue = "4")Integer perPage,
            @RequestParam(value = "_page", required = false, defaultValue = "1")Integer page,
            @RequestParam(value = "sortDirection", required = false, defaultValue = "asc") String sortDirection,
            @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy) {

        Sort sort = sortDirection.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page - 1, perPage, sort);

        Page<JobPost> output = jobPostService.getJobPostsByIndustry(industry, pageable);
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("X-Total-Count", String.valueOf(output.getTotalElements()));
        return new ResponseEntity<>(output, responseHeader, HttpStatus.OK);
    }


    @PostMapping("jobs")
    public ResponseEntity<JobPost> addJobPost(@RequestBody JobPost jobPost){
        jobPost.setPostedDate(new Date());
        JobPost output = jobPostService.save(jobPost);
        return new ResponseEntity<>(output, HttpStatus.CREATED);
    }

    @PutMapping("jobs/{id}")
    public ResponseEntity<JobPost> updateJobPost(@PathVariable Long id,@RequestBody JobPostDto jobPostDto){
        JobPost updatedJobPost = jobPostService.updateJobPost(id, jobPostDto);
        return ResponseEntity.ok(updatedJobPost);
    }

    @DeleteMapping("jobs/{id}")
    public ResponseEntity<?> deleteJobPost(@PathVariable Long id){
        jobPostService.deleteById(id);
        return ResponseEntity.ok("Job post id: " + id + " is now deleted");
    }


}
