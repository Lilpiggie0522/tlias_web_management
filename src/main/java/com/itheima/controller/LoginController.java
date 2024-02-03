package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: LoginController
 * Package: com.itheima.controller
 * Description:
 *
 * @Author Piggie
 * @Create 29/01/2024 2:03 pm
 * @Version 1.0
 */
@Slf4j
@RestController
public class LoginController {
    @Autowired
    private EmpService empService;
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        Emp result = empService.login(emp);
        if (result != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", result.getId());
            claims.put("name", result.getName());
            claims.put("username", result.getUsername());
            String jwt = JwtUtils.generateJwt(claims);
            log.info("jwt is: " + jwt);
            return Result.success(jwt);
        }
        log.info("login failed");
        return Result.error("login failed");
    }
}
