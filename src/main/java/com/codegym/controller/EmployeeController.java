package com.codegym.controller;


import com.codegym.model.Department;
import com.codegym.model.Employee;
import com.codegym.service.DepartmentService;
import com.codegym.service.EmployeeService;
import com.codegym.service.impl.DepartmentServiceImpl;
import com.codegym.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @ModelAttribute("departments")
    public Iterable<Department> departments(){
        return departmentService.findAll();
    }

    @GetMapping("/employees")
    public ModelAndView listEmployees(@RequestParam("s") Optional<String> s, Pageable pageable){
        Page<Employee> employees;
        if(s.isPresent()){
            employees = employeeService.findAllByEmployeeIdOrName(s.get(),s.get(), pageable);
        } else {
            employees = employeeService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/employee/list");
        modelAndView.addObject("employees", employees);
        return modelAndView;
    }

    @GetMapping("/create-employee")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/employee/create");
        modelAndView.addObject("employee", new Employee());
        return modelAndView;
    }

    @PostMapping("/create-employee")
    public ModelAndView saveCustomer(@Validated @ModelAttribute("employee") Employee employee, BindingResult bindingResult){
       if (bindingResult.hasFieldErrors()){

           ModelAndView modelAndView = new ModelAndView("/employee/create");
           return modelAndView;
       }
        employeeService.save(employee);
        ModelAndView modelAndView = new ModelAndView("/employee/create");
        modelAndView.addObject("message", "New customer created successfully");
        return modelAndView;
    }

    @GetMapping("/edit-employee/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Employee employee = employeeService.findById(id);
        if (employee != null) {
            ModelAndView modelAndView = new ModelAndView("/employee/edit");
            modelAndView.addObject("employee", employee);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-employee")
    public ModelAndView updateProduct(@ModelAttribute("employee") Employee employee) {

        Employee employee1 = employeeService.findById(employee.getId());
        employee1.setName(employee.getName());
        employee1.setDateOfBirth(employee.getDateOfBirth());
        employee1.setGender(employee.getGender());
        employee1.setNumberPhone(employee.getNumberPhone());
        employee1.setPersonId(employee.getPersonId());
        employee1.setEmail(employee.getEmail());
        employee1.setAddress(employee.getAddress());

        if (employee1 != null) {
            employeeService.save(employee1);
            ModelAndView modelAndView = new ModelAndView("/employee/edit");
            modelAndView.addObject("employee", employee1);
            modelAndView.addObject("message", "\n" +
                    "Fix the employee successfully !!!");
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @GetMapping("/delete-employee/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Employee employee = employeeService.findById(id);
        if (employee != null) {
            ModelAndView modelAndView = new ModelAndView("/employee/delete");
            modelAndView.addObject("employee", employee);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-employee")
    public String deleteCustomer(@ModelAttribute("employee") Employee employee) {
        employeeService.remove(employee.getId());
        return "redirect:employees";
    }
    @PostMapping("/validateUser")
    public ModelAndView checkValidation(@Validated @ModelAttribute("employee") Employee employee, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("index");
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView("result");
        return modelAndView;
    }
}
