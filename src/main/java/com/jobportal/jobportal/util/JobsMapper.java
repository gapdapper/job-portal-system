package com.jobportal.jobportal.util;

import com.jobportal.jobportal.entitiy.Application;
import com.jobportal.jobportal.entitiy.ApplicationDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface JobsMapper {

    JobsMapper INSTANCE = Mappers.getMapper(JobsMapper.class);

    ApplicationDto applicationToApplicationDto(Application application);

}
