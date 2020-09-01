package com.raunt.university;

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
import com.raunt.entities.University;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UniversityController {

	@Autowired
	private UniversityService universityService;

	// HTTP GET Request on university list
	// @CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.GET, value = "/university")
	public List<University> getAllUniversity() {
		return universityService.getAllUniversity();

	}

	// Get single university
	@RequestMapping(method = RequestMethod.GET, value = "/university/{id}")
	public University getUniversity(@PathVariable int id) {
		return universityService.getUniversity(id);
	}

	// DELETE Request for deleting from depList
	// This is how to delete a department from parent node
	@RequestMapping(method = RequestMethod.DELETE, value = "/university/{universityId}/delete/{depId}")
	public String deleteDepFromList(@PathVariable int universityId, @PathVariable int depId) {
		return universityService.deleteDepFromList(universityId, depId);
	}

	// POST Request for adding to depList
	@RequestMapping(method = RequestMethod.POST, value = "/university/{universityId}/add")
	public String addStudentFromList(@PathVariable int universityId, @RequestBody Department department) {
		return universityService.addDepToList(universityId, department);
	}

	// POST for single object
	@RequestMapping(method = RequestMethod.POST, value = "/university")
	public String addUniversity(@RequestBody University university) {
		return universityService.addUniversity(university);
	}

	// POST for json array
	@RequestMapping(method = RequestMethod.POST, value = "/university/bulk")
	public String addUniversityBulk(@RequestBody List<University> universityList) {
		return universityService.addUniversityBulk(universityList);
	}

	// UPDATE Request
	@RequestMapping(method = RequestMethod.PUT, value = "/university/{id}")
	public String updateUniversity(@RequestBody University university) {
		return universityService.updateUniversity(university);
	}

	// DELETE Request
	@RequestMapping(method = RequestMethod.DELETE, value = "/university/{id}")
	public String deleteUniversity(@PathVariable int id) {
		return universityService.deleteUniversity(id);
	}

	// REFRESH Request (GET)
	@RequestMapping(method = RequestMethod.GET, value = "/university/refresh")
	public String refreshDepartment() {
		return universityService.refreshDepartment();
	}

	// PATCH Request for single properties
	@RequestMapping(method = RequestMethod.PATCH, value = "/university/{id}/patchfields")
	public String patchUniversity(@PathVariable int id, @RequestBody Map<Object, Object> fields) {
		University university = universityService.getUniversity(id);

		fields.forEach((k, v) -> {
			Field field = ReflectionUtils.findField(University.class, (String) k);
			field.setAccessible(true);

			ReflectionUtils.setField(field, university, v);

		});

		return universityService.updateUniversity(university);
	}
}
