package com.itheima.mapper;

import com.itheima.pojo.DeptLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: DeptLogMapper
 * Package: com.itheima.mapper
 * Description:
 *
 * @Author Piggie
 * @Create 30/01/2024 7:31 pm
 * @Version 1.0
 */
@Mapper
public interface DeptLogMapper {
    void insert(DeptLog deptLog);
}
