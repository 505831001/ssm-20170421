package com.liuweiwei.mapper;

import com.liuweiwei.bean.Department;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DepartmentMapper {
    long countByExample(DepartmentExample example);
    int deleteByExample(DepartmentExample example);
    int deleteByPrimaryKey(Integer deptId);
    int insert(Department record);
    int insertSelective(Department record);
    // 查询所有部门
    List<Department> selectAll();
    Department selectByPrimaryKey(Integer deptId);
    int updateByExampleSelective(@Param("record") Department record, @Param("example") DepartmentExample example);
    int updateByExample(@Param("record") Department record, @Param("example") DepartmentExample example);
    int updateByPrimaryKeySelective(Department record);
    int updateByPrimaryKey(Department record);
}