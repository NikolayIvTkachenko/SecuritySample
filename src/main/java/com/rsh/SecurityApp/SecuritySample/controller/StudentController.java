package com.rsh.SecurityApp.SecuritySample.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rsh.SecurityApp.SecuritySample.model.Student;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {

	private static final List<Student> STUDENTS = Arrays.asList(
			new Student(1, "Petrov Ivan"),
			new Student(2, "Gor Jhon"),
			new Student(3, "Indiana Jhons")
	);
			
	
	@GetMapping(path = "/{studentId}")
	public Student getStudent(@PathVariable("studentId") Integer studentId) {
		return STUDENTS.stream()
				.filter(student -> studentId.equals(studentId))
				.findFirst()
				.orElseThrow( () -> new IllegalStateException("Student " + studentId + " does not exists"));
	}
	
}
