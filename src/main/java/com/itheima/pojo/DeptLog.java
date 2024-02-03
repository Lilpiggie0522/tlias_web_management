package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * ClassName: DeptLog
 * Package: com.itheima.pojo
 * Description:
 *
 * @Author Piggie
 * @Create 30/01/2024 7:25 pm
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeptLog {
    private Integer id;
    private LocalDateTime createTime;
    private String description;
}
