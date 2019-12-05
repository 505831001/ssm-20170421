package com.liuweiwei.service;

import com.liuweiwei.bean.Employee;
import com.liuweiwei.bean.EmployeeExample;
import com.liuweiwei.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    public List<EmployeeExample> getAll() {
        // TODO Auto-generated method stub
        List<EmployeeExample> list = employeeMapper.selectAll();
        return list;
    }

    public EmployeeExample getOne(EmployeeExample employeeExample) {
        // TODO Auto-generated method stub
        EmployeeExample selectOne = employeeMapper.selectOne(employeeExample);
        return selectOne;
    }

    public Integer update(Employee employee) {
        // TODO Auto-generated method stub
        int update = employeeMapper.update(employee);
        return update;
    }

    public Integer saveOne(Employee employee) {
        // TODO Auto-generated method stub
        int insert = employeeMapper.saveOne(employee);
        return insert;
    }

    public Employee checkUser(String empName) {
        // TODO Auto-generated method stub
        Employee result = employeeMapper.selectByName(empName);
        return result;
    }

    public Employee getById(Integer id) {
        // TODO Auto-generated method stub
        Employee employee = employeeMapper.getById(id);
        return employee;
    }

    public Integer updateOne(Employee employee) {
        // TODO Auto-generated method stub
        int updateOne = employeeMapper.updateOne(employee);
        return updateOne;
    }

    public Integer delete(Integer id) {
        // TODO Auto-generated method stub
        int delete = employeeMapper.delete(id);
        return delete;
    }

    public Integer deleteBatch(List<Integer> idList) {
        // TODO Auto-generated method stub
        int deleteBatch = employeeMapper.deleteBatch(idList);
        return deleteBatch;
    }

}
