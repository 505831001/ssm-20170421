package com.liuweiwei.controller;

import com.liuweiwei.bean.Department;
import com.liuweiwei.bean.Message;
import com.liuweiwei.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DepartmentController {
	@Autowired
	private DepartmentService departmentService;

	@RequestMapping("/getDepartment")
	@ResponseBody
	public Message getDepartment() {
		List<Department> list = departmentService.getAll();
		return Message.success().add("depts", list);
	}
}
