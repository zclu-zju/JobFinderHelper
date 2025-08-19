package com.zclu.interviewtips.mapper;


import com.zclu.interviewtips.entity.Jobs;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface JobsMapper {
    @Select("select * from jobs ORDER BY date desc limit #{offset}, #{pageSize}")
    List<Jobs> getJobsByPage(int offset, int pageSize);
}
