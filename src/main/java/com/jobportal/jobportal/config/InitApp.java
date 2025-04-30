package com.jobportal.jobportal.config;


import com.jobportal.jobportal.entitiy.Applicant;
import com.jobportal.jobportal.entitiy.Application;
import com.jobportal.jobportal.entitiy.Company;
import com.jobportal.jobportal.entitiy.JobPost;
import com.jobportal.jobportal.repository.ApplicantRepository;
import com.jobportal.jobportal.repository.ApplicationRepository;
import com.jobportal.jobportal.repository.CompanyRepository;
import com.jobportal.jobportal.repository.JobPostRepository;
import com.jobportal.jobportal.user.Role;
import com.jobportal.jobportal.user.User;
import com.jobportal.jobportal.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    final JobPostRepository jobPostRepository;
    final CompanyRepository companyRepository;
    final UserRepository userRepository;
    final ApplicationRepository applicationRepository;
    final ApplicantRepository applicantRepository;



    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {

        addUser();


        Company company1 = companyRepository.save(Company.builder()
                .companyName("Apex Innovations")
                .industry("Technology")
                .website("www.apexinnovations.com")
                .build());

        company1.setUser(user3);
        companyRepository.save(company1);


        JobPost jobPost1 = jobPostRepository.save(JobPost.builder()
                .title("Software Engineer")
                .description("Work on scalable backend systems.")
                .location("New York, NY")
                .salaryRange("$80,000 - $120,000")
                .postedDate(new Date())
                .company(company1)
                .build());

        jobPostRepository.save(JobPost.builder()
                .title("Frontend Developer")
                .description("Build responsive UIs using React.")
                .location("San Francisco, CA")
                .salaryRange("$90,000 - $130,000")
                .postedDate(new Date())
                .company(company1)
                .build());

        jobPostRepository.save(JobPost.builder()
                .title("DevOps Engineer")
                .description("Manage CI/CD pipelines and cloud infra.")
                .location("Remote")
                .salaryRange("$100,000 - $140,000")
                .postedDate(new Date())
                .company(company1)
                .build());

        jobPostRepository.save(JobPost.builder()
                .title("Data Scientist")
                .description("Analyze large datasets to drive decisions.")
                .location("Chicago, IL")
                .salaryRange("$95,000 - $135,000")
                .postedDate(new Date())
                .company(company1)
                .build());

        jobPostRepository.save(JobPost.builder()
                .title("Mobile App Developer")
                .description("Develop Android and iOS apps.")
                .location("Austin, TX")
                .salaryRange("$85,000 - $125,000")
                .postedDate(new Date())
                .company(company1)
                .build());

        jobPostRepository.save(JobPost.builder()
                .title("UI/UX Designer")
                .description("Design intuitive user experiences.")
                .location("Seattle, WA")
                .salaryRange("$75,000 - $110,000")
                .postedDate(new Date())
                .company(company1)
                .build());


        Applicant applicant1 = applicantRepository.save(Applicant.builder()
                .name("Michael Mann")
                .skills("Backend Developer")
                .experienceYears("2 Years")
                .build());

        applicant1.setUser(user2);
        applicantRepository.save(applicant1);


        applicationRepository.save(Application.builder()
                .jobPost(jobPost1)
                .applicant(applicant1)
                .appliedDate(new Date())
                .status("APPLIED")
                .build());
    }

    User user1, user2, user3;
    private void addUser() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        user1 = User.builder()
                .username("admin")
                .password(encoder.encode("admin"))
                .firstname("admin")
                .lastname("admin")
                .email("admin@admin.com")
                .enabled(true)
                .build();

        user2 = User.builder()
                .username("user")
                .password(encoder.encode("user"))
                .firstname("user")
                .lastname("user")
                .email("user@user.com")
                .enabled(true)
                .build();

        user3 = User.builder()
                .username("company")
                .password(encoder.encode("company"))
                .firstname("company")
                .lastname("company")
                .email("company@company.com")
                .enabled(true)
                .build();

        user1.getRoles().add(Role.ROLE_ADMIN);
        user2.getRoles().add(Role.ROLE_APPLICANT);
        user3.getRoles().add(Role.ROLE_COMPANY);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

    }

}