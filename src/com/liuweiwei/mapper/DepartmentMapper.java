package com.liuweiwei.mapper;

import com.liuweiwei.bean.Department;

import java.util.List;

public interface DepartmentMapper {
    // 查询所有部门
    List<Department> selectAll();
}