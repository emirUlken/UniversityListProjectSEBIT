package com.raunt.depdetails;

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

import com.raunt.entities.DepartmentDetails;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class DepDetailsController {

	@Autowired
	private DepDetailsService depdetailsService;

	// HTTP GET Request on depdetails list
	// @CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.GET, value = "/depdetails")
	public List<DepartmentDetails> getAllDepartmentDetails() {
		return depdetailsService.getAllDepartmentDetails();

	}

	// Get single depdetails
	@RequestMapping(method = RequestMethod.GET, value = "/depdetails/{id}")
	public DepartmentDetails getDepartmentDetails(@PathVariable int id) {
		return depdetailsService.getDepartmentDetails(id);
	}

	// POST for single object
	@RequestMapping(method = RequestMethod.POST, value = "/depdetails")
	public String addDepartmentDetails(@RequestBody DepartmentDetails depdetails) {
		return depdetailsService.addDepartmentDetails(depdetails);
	}

	// POST for json array
	@RequestMapping(method = RequestMethod.POST, value = "/depdetails/bulk")
	public String addDepartmentDetailsBulk(@RequestBody List<DepartmentDetails> depdetailsList) {
		return depdetailsService.addDepartmentDetailsBulk(depdetailsList);
	}

	// UPDATE Request
	@RequestMapping(method = RequestMethod.PUT, value = "/depdetails/{id}")
	public String updateDepartmentDetails(@RequestBody DepartmentDetails depdetails) {
		return depdetailsService.updateDepartmentDetails(depdetails);
	}

	// DELETE Request
	@RequestMapping(method = RequestMethod.DELETE, value = "/depdetails/{id}")
	public String deleteDepartmentDetails(@PathVariable int id) {
		return depdetailsService.deleteDepartmentDetails(id);
	}

	// PATCH Request for single properties
	@RequestMapping(method = RequestMethod.PATCH, value = "/depdetails/{id}/patchfields")
	public String patchDepartmentDetails(@PathVariable int id, @RequestBody Map<Object, Object> fields) {
		DepartmentDetails depdetails = depdetailsService.getDepartmentDetails(id);

		fields.forEach((k, v) -> {
			Field field = ReflectionUtils.findField(DepartmentDetails.class, (String) k);
			field.setAccessible(true);

			ReflectionUtils.setField(field, depdetails, v);

		});

		return depdetailsService.updateDepartmentDetails(depdetails);
	}
}
