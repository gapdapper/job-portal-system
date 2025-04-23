package com.jobportal.jobportal.entitiy;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDto {

    private Long id;
    private String status;
    private Date appliedDate;

}
