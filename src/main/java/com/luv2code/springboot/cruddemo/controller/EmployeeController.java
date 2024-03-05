package com.luv2code.springboot.cruddemo.controller;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping({"/list"})
    public String listOfEmployees(Model model){
        //get employees from db
        List<Employee> list=service.findAll();

        //add to model
        model.addAttribute("employees",list);
        return "list-employees";
    }

    @GetMapping("/showAddEmployeeForm")
    public String getEmployee(Model model){
        Employee employee=new Employee();

        //add to model
        model.addAttribute("employee",employee);
        return "add-employee";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        //saving the employee by calling the service
        service.save(employee);
        return "redirect:/employees/list";
    }

    @GetMapping("/showFormUpdate/{id}")
    public String showFormForUpdate(@PathVariable int id,Model model){
        System.out.println("id - "+id);
        Employee employee=service.findById(id);
        model.addAttribute("employee",employee);
        return "add-employee";
    }
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable int id ){
        System.out.println("------"+ id);
        //saving the employee by calling the service
        service.deleteById(id);
        return "redirect:/employees/list";
    }


}
