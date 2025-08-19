package com.zclu.interviewtips.mapper;


import com.zclu.interviewtips.entity.OsJobs;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface OsJobsMapper {
    @Select("SELECT * FROM osjobs WHERE id = #{id}")
    OsJobs getOsJobsById(Integer id);

    @Select("SELECT * FROM osjobs")
    List<OsJobs> getAllOsJobs();

    @Select("SELECT * FROM osjobs WHERE tag = #{tag}")
    List<OsJobs> getOsJobsByTag(String tag);

    @Select("SELECT * from osjobs ORDER BY RAND() LIMIT 1")
    OsJobs getRandomOsJobs();
}
