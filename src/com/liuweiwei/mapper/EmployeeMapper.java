package com.liuweiwei.mapper;

import com.liuweiwei.bean.Employee;
import com.liuweiwei.bean.EmployeeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    // 查询所有员工
    List<EmployeeExample> selectAll();
    // 查询单个员工
    EmployeeExample selectOne(EmployeeExample employeeExample);
    // 更新单个员工
    int update(Employee employee);
    // 验证用户是否已存在
    Employee selectByName(@Param("empName") String empName);
    // 保存单个员工
    int saveOne(Employee employee);
    // 修改单个员工
    int updateOne(Employee record);
    // 根据ID获取员工
    Employee getById(@Param("empId") Integer empId);
    // 删除单个员工
    int delete(@Param("empId") Integer empId);
    // 批量删除员工
    int deleteBatch(@Param("list") List<Integer> list);
}