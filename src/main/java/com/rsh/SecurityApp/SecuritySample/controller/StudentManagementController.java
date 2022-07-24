package com.rsh.SecurityApp.SecuritySample.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.el.parser.AstIdentifier;
import org.checkerframework.checker.units.qual.s;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rsh.SecurityApp.SecuritySample.model.Student;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {
	
	private static final List<Student> STUDENTS = Arrays.asList(
			new Student(1, "Petrov Ivan"),
			new Student(2, "Gor Jhon"),
			new Student(3, "Indiana Jhons")
	);
	
	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	//@PreAuthorize("hasAnyRole('ADMIN, USER')")
	//@PreAuthorize("hasAuthority('student:read')")
	//@PreAuthorize("hasAnyAuthority('ROLE_ADMIN, ROLE_USER')")
	//@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
	public List<Student> getAllStudents(){
		System.out.println("");
		System.out.println("getAllStudents()");
		return STUDENTS;
	}

	@PostMapping
	@PreAuthorize("hasAuthority('student:write')")
	public void registerNewStudent(@RequestBody Student student) {
		System.out.println("");
		System.out.println("registerNewStudent => "+ student);
	}
	
	@DeleteMapping(path = "{studentId}")
	@PreAuthorize("hasAuthority('student:write')")
	public void deleteStudent(@PathVariable("studentId") Integer studentId) {
		System.out.println("");
		System.out.println("delete stdent => " + studentId);
	}
	
	@PutMapping(path = "{studentId}")
	@PreAuthorize("hasAuthority('student:write')")
	public void updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody Student student) {
		System.out.println("");
		System.out.println(String.format("update = > %s %s", studentId, student));
	}
	
}
