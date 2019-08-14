package com.baoge.springbootweb.controller;

import com.baoge.springbootweb.dao.DepartmentDao;
import com.baoge.springbootweb.dao.EmployeeDao;
import com.baoge.springbootweb.entities.Department;
import com.baoge.springbootweb.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    // 查询所有员工返回列表页面
    @GetMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getAll();

        // 放在请求域中共享
        model.addAttribute("emps", employees);
        // thymeleaf默认就会拼串
        return "emp/list";
    }

    // 来到员工添加页面
    @GetMapping("/emp")
    public String toAddPage(Model model){
        // 来到添加页面，查出所有的部门，在页面显示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        return "emp/add";
    }

    // 员工添加
    @PostMapping("/emp")
    // SpringMvc自动将请求参数和入参对象的属性进行绑定；要求了请求参数的名字和javaBean入参的对象属性名是一样的
    public String addEmp(Employee employee){

        // 来到员工列表页面
        System.out.println("保存的员工信息:" + employee);
        // 保存员工
        employeeDao.save(employee);
        // redirect表示重定向
        // forward表示转发
        return "redirect:/emps";
    }

    // 来到修改页面，查出当前员工，在页面回显
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model){
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp", employee);

        // 页面要显示所有的部门列表
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);

        // 回到修改页面(add是修改添加二合一页面)
        return "emp/add";
    }

    // 员工修改
    @PutMapping("/emp")
    public String updateEmployee(Employee employee){
        System.out.println("修改的员工数据:" + employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    // 员工删除
    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
