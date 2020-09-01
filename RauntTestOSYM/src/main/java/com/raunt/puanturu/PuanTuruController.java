package com.raunt.puanturu;

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

import com.raunt.entities.PointType;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PuanTuruController {

	@Autowired
	private PuanTuruService pointTypeService;

	// HTTP GET Request on pointtype list
	// @CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.GET, value = "/pointtype")
	public List<PointType> getAllPointType() {
		return pointTypeService.getAllPointType();

	}

	// Get single pointtype
	@RequestMapping(method = RequestMethod.GET, value = "/pointtype/{id}")
	public PointType getPointType(@PathVariable int id) {
		return pointTypeService.getPointType(id);
	}

	// POST for single object
	@RequestMapping(method = RequestMethod.POST, value = "/pointtype")
	public String addPointType(@RequestBody PointType pointtype) {
		return pointTypeService.addPointType(pointtype);
	}

	// POST for json array
	@RequestMapping(method = RequestMethod.POST, value = "/pointtype/bulk")
	public String addPointTypeBulk(@RequestBody List<PointType> pointtypeList) {
		return pointTypeService.addPointTypeBulk(pointtypeList);
	}

	// UPDATE Request
	@RequestMapping(method = RequestMethod.PUT, value = "/pointtype/{id}")
	public String updatePointType(@RequestBody PointType pointtype) {
		return pointTypeService.updatePointType(pointtype);
	}

	// DELETE Request
	@RequestMapping(method = RequestMethod.DELETE, value = "/pointtype/{id}")
	public String deletePointType(@PathVariable int id) {
		return pointTypeService.deletePointType(id);
	}

	// PATCH Request for single properties
	@RequestMapping(method = RequestMethod.PATCH, value = "/pointtype/{id}/patchfields")
	public String patchPointType(@PathVariable int id, @RequestBody Map<Object, Object> fields) {
		PointType pointtype = pointTypeService.getPointType(id);

		fields.forEach((k, v) -> {
			Field field = ReflectionUtils.findField(PointType.class, (String) k);
			field.setAccessible(true);

			ReflectionUtils.setField(field, pointtype, v);

		});

		return pointTypeService.updatePointType(pointtype);
	}
}
