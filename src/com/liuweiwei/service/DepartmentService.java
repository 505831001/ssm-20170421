package com.liuweiwei.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liuweiwei.bean.Department;
import com.liuweiwei.mapper.DepartmentMapper;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentMapper departmentMapper;

	public List<Department> getAll() {
		// TODO Auto-generated method stub
		List<Department> list = departmentMapper.selectAll();
		return list;
	}

}
