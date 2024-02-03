package com.itheima.mapper;

import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
//    //  获取总数据数量
//    @Select("select count(*) from emp")
//    Long count();
//
//    //  分页查询获取列表数据
//    @Select("select * from emp limit #{start}, #{pageSize}")
//    List<Emp> page(Integer start, Integer pageSize);

    //  用pageHelper自动查询封装结果
    //  @Select("select * from emp where name like concat('%', #{name}, '%') and gender=#{gender} and entrydate between #{begin} and #{end} order by update_time desc")
    public List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);

    void delete(Integer[] ids);


    void save(Emp emp);

    Emp get(Integer id);

    void update(Emp emp);

    Emp getUserByUsernameAndPassword(Emp emp);

    void deleteEmpByDeptId(Integer deptId);
}
