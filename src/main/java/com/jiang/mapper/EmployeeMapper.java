package com.jiang.mapper;

import com.jiang.pojo.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

//指定这是一个操作数据库的Mapper
@Mapper
public interface EmployeeMapper {

    @Options(useGeneratedKeys = true,keyProperty = "id")
    //@Insert("insert  into `employee`(`name`,`gender`) values(#{name},#{gender})")
    @Insert("INSERT into `employee` (`name`,`gender`) values (#{name},#{gender})")
    void save(Employee employee);
}
