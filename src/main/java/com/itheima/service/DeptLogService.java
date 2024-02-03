package com.itheima.service;

import com.itheima.pojo.DeptLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

/**
 * ClassName: DeptLogService
 * Package: com.itheima.service
 * Description:
 *
 * @Author Piggie
 * @Create 30/01/2024 7:29 pm
 * @Version 1.0
 */
public interface DeptLogService {
    void insert(DeptLog deptLog);
}
