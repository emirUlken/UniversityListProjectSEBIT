package com.raunt.department;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.raunt.entities.Department;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	// HTTP GET Request on department list
	// @CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.GET, value = "/department")
	public List<Department> getAllDepartment() {
		return departmentService.getAllDepartment();

	}

	// Get single department
	@RequestMapping(method = RequestMethod.GET, value = "/department/{id}")
	public Department getDepartment(@PathVariable int id) {
		return departmentService.getDepartment(id);
	}

	// POST for single object
	@RequestMapping(method = RequestMethod.POST, value = "/department")
	public String addDepartment(@RequestBody Department department) {
		return departmentService.addDepartment(department);
	}

	// POST for json array
	@RequestMapping(method = RequestMethod.POST, value = "/department/bulk")
	public String addDepartmentBulk(@RequestBody List<Department> departmentList) {
		return departmentService.addDepartmentBulk(departmentList);
	}

	// UPDATE Request
	@RequestMapping(method = RequestMethod.PUT, value = "/department/{id}")
	public String updateDepartment(@RequestBody Department department) {
		return departmentService.updateDepartment(department);
	}
	
	// REFRESH Request (GET)
	// DELETE and REFRESH on a department is made from parent entity "University".
	/*
	@RequestMapping(method = RequestMethod.GET, value = "/department/refresh")
	public String refreshDepartment() {
		return departmentService.refreshDepartment();
	}
	*/

	/*
	// DELETE Request
	@RequestMapping(method = RequestMethod.DELETE, value = "/department/{id}")
	public String deleteDepartment(@PathVariable int id) {
		return departmentService.deleteDepartment(id);
	}
	*/

	// PATCH Request for single properties
	@RequestMapping(method = RequestMethod.PATCH, value = "/department/{id}/patchfields")
	public String patchDepartment(@PathVariable int id, @RequestBody Map<Object, Object> fields) {
		Department department = departmentService.getDepartment(id);

		fields.forEach((k, v) -> {
			Field field = ReflectionUtils.findField(Department.class, (String) k);
			field.setAccessible(true);

			ReflectionUtils.setField(field, department, v);

		});

		return departmentService.updateDepartment(department);
	}
}
