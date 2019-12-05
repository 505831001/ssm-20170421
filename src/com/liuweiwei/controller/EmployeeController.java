package com.liuweiwei.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liuweiwei.bean.*;
import com.liuweiwei.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    /**
     * 1. 查询员工列表
     * @param pn
     * @return
     */
    @RequestMapping("/getEmployee")
    @ResponseBody
    public Message getEmployee(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        PageHelper.startPage(pn, 5);
        List<EmployeeExample> emps = employeeService.getAll();
        PageInfo page = new PageInfo(emps, 5);
        return Message.success().add("pageInfo", page);
    }

    /**
     * 2. 检查用户名是否可用
     * @param empName
     * @return
     */
    @RequestMapping(value = "/uniqueAuth")
    @ResponseBody
    public Message uniqueAuth(@RequestParam("empName") String empName) {
        // 1.判断用户名是否是符合正则表达式
        String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
        if (!empName.matches(regx)) {
            return Message.fail().add("va_msg", "提示:用户名是6-16位数字和字母组合,或者2-5位中文");
        }
        // 2.判断数据库是否已存在此用户
        Employee employee = employeeService.checkUser(empName);
        if (employee == null) {
            return Message.success();
        } else {
            return Message.fail().add("va_msg", "此用户名已存在");
        }
    }

    /**
     * 4. 保存员工信息(支持JSR303校验,导入Hibernate-Validator)
     * @param employee
     * @param result
     * @return
     */
    @RequestMapping(value = "/saveOne", method = RequestMethod.POST)
    @ResponseBody
    public Message saveOne(@Valid Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, Object> map = new HashMap<>();
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError fieldError : errors) {
                System.out.println("错误的字段名：" + fieldError.getField());
                System.out.println("错误信息：" + fieldError.getDefaultMessage());
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return Message.fail().add("errorFields", map);
        }
        int insert = employeeService.saveOne(employee);
        if (insert > 0) {
            return Message.success();
        }
        return Message.fail();
    }

    /**
     * 5. 获取更新员工信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Message getById(@PathVariable("id") Integer id) {
        Employee employee = employeeService.getById(id);
        return Message.success().add("employee", employee);
    }

    /**
     * 6. 更新员工信息
     * @param employee
     * @param request
     * @return
     */
    @RequestMapping(value = "/update/{empId}", method = RequestMethod.PUT)
    @ResponseBody
    public Message updateOne(Employee employee, HttpServletRequest request) {
        System.out.println("请求体中性别的值是：" + request.getParameter("gender"));
        System.out.println("将要更新的员工对象：" + employee);
        Integer updateOne = employeeService.updateOne(employee);
        if (updateOne > 0) {
            return Message.success();
        }
        return Message.fail();
    }

    /**
     * 7. 删除员工信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Message delete(@PathVariable("id") String id) {
        Integer delete = employeeService.delete(new Integer(id));
        if (delete > 0) {
            return Message.success();
        }
        return Message.fail();
    }

    /**
     * 8. 批量删除员工信息
     * @param idString
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteBatch/{idString}", method = RequestMethod.DELETE)
    public Message deleteBatch(@PathVariable("idString") String idString) {
        System.out.println("接收到前端的ID字符串: " + idString);

        List<Integer> idList = new ArrayList<>();
        if (idString.contains("-")) {
            String[] split = idString.split("-");
            for (int i = 0; i < split.length; i++) {
                System.out.println("分割后的ID字符串：" + split[i]);
            }
            for (String id : split) {
                idList.add(Integer.parseInt(id));
                System.out.println("改变类型后的串串：" + idList.toString());
            }
            Integer deleteBatch = employeeService.deleteBatch(idList);
            if (deleteBatch > 0) {
                System.out.println("Affected row: " + deleteBatch);
                return Message.success();
            }
        }
        return Message.fail();
    }




    /**
     * 0. 模板测试 - TODO 后期完善模板参考
     * @param employee
     * @param model
     * @return
     * @author liuweiwei
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public MessageExample<Integer> update(Employee employee, Model model) {
        Integer update = employeeService.update(employee);
        model.addAttribute("update", update);
        return MessageExample.data(update);
    }
    @RequestMapping(value = "/getOne")
    @ResponseBody
    public MessageExample<EmployeeExample> getOne(EmployeeExample employeeExample, Model model) {
        EmployeeExample employee = employeeService.getOne(employeeExample);
        model.addAttribute("employee", employee);
        return MessageExample.data(employee);
    }
    @RequestMapping(value = "/getList")
    @ResponseBody
    public MessageExample<List<EmployeeExample>> getList(Model model) {
        List<EmployeeExample> list = employeeService.getAll();
        model.addAttribute("list", list);
        return MessageExample.data(list);
    }
    @RequestMapping(value = "/getPage")
    @ResponseBody
    public MessageExample<PageExample<EmployeeExample>> getPage(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber, Model model) {
        List<EmployeeExample> list = employeeService.getAll();
        PageExample<EmployeeExample> page = new PageExample<>();
        page.setPageNumber(pageNumber);
        page.setPageSize(10);
        page.setRows(list);
        model.addAttribute("page", page);
        return MessageExample.data(page);
    }
}
