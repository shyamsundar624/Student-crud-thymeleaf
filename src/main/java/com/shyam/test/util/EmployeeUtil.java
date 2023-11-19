package com.shyam.test.util;

import java.util.Arrays;
import java.util.List;

import org.springframework.ui.Model;

public interface EmployeeUtil {

	public static void getCourseList(Model model) {
		List<String> list = Arrays.asList("JAVA","SPRING","ANGULAR","OTHERS");
		model.addAttribute("courseList", list);
	}
	
}
