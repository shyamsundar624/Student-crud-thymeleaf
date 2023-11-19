package com.shyam.test.controller;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shyam.test.entity.Student;
import com.shyam.test.exception.StudentNotFoundException;
import com.shyam.test.service.IStudentService;
import com.shyam.test.util.EmployeeUtil;

@Controller

@RequestMapping("/student")
public class StudentController {

	@Autowired
	private IStudentService service;

	@GetMapping("/register")
	public String registerPage(Model model) {
		EmployeeUtil.getCourseList(model);
		return "StudentRegister";
	}

	@PostMapping("/save")
	public String saveStudentInfo(@ModelAttribute Student student, Model model) {
		Integer id = service.saveStudent(student);
		String msg = "STUDENT '" + id + "' CREATED";
		model.addAttribute("msg", msg);
		EmployeeUtil.getCourseList(model);
		return "StudentRegister";
	}

	@GetMapping("/all")
	public String showAllInfo(Model model, @RequestParam(value = "msg", required = false) String msg) {
		model.addAttribute("list", service.getAllStudent());
		model.addAttribute("msg", msg);
		return "Studentdata";
	}

	@GetMapping("/delete")
	public String deleteStudent(@RequestParam("id") Integer id, RedirectAttributes attributes) {
		String msg = null;
		try {
			service.deleteStudent(id);
			msg = "STUDENT '" + id + "' DELETED";
		} catch (StudentNotFoundException e) {
			e.printStackTrace();
			msg = e.getMessage();
		}
		attributes.addAttribute(msg);
		return "redirect:all";
	}

	@GetMapping("/edit")
	public String editStudent(@RequestParam("id") Integer id, Model model, RedirectAttributes attributes) {
		String page = null;
		try {
			Student student = service.getOneStudent(id);
			model.addAttribute("student", student);
			EmployeeUtil.getCourseList(model);
			page = "StudentEdit";
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addAttribute("msg", e.getMessage());
			page = "redirect:all";
		}
		return page;
	}
	
	@PostMapping("/update")
	public String updateStudent(@ModelAttribute Student student,RedirectAttributes attributes) {
		service.updateStudent(student);
		attributes.addAttribute("msg", "STUDENT '"+student.getId()+"' UPDATED");
		return "redirect:all";
	}
}
