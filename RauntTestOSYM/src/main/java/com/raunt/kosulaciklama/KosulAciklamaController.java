package com.raunt.kosulaciklama;

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

import com.raunt.entities.KosulAciklama;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class KosulAciklamaController {

	@Autowired
	private KosulAciklamaService kosulaciklamaService;

	// HTTP GET Request on kosulaciklama list
	// @CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.GET, value = "/kosulaciklama")
	public List<KosulAciklama> getAllKosulAciklama() {
		return kosulaciklamaService.getAllKosulAciklama();

	}

	// Get single kosulaciklama
	@RequestMapping(method = RequestMethod.GET, value = "/kosulaciklama/{id}")
	public KosulAciklama getKosulAciklama(@PathVariable int id) {
		return kosulaciklamaService.getKosulAciklama(id);
	}

	// POST for single object
	@RequestMapping(method = RequestMethod.POST, value = "/kosulaciklama")
	public String addKosulAciklama(@RequestBody KosulAciklama kosulaciklama) {
		return kosulaciklamaService.addKosulAciklama(kosulaciklama);
	}

	// POST for json array
	@RequestMapping(method = RequestMethod.POST, value = "/kosulaciklama/bulk")
	public String addKosulAciklamaBulk(@RequestBody List<KosulAciklama> kosulaciklamaList) {
		return kosulaciklamaService.addKosulAciklamaBulk(kosulaciklamaList);
	}

	// UPDATE Request
	@RequestMapping(method = RequestMethod.PUT, value = "/kosulaciklama/{id}")
	public String updateKosulAciklama(@RequestBody KosulAciklama kosulaciklama) {
		return kosulaciklamaService.updateKosulAciklama(kosulaciklama);
	}

	// DELETE Request
	@RequestMapping(method = RequestMethod.DELETE, value = "/kosulaciklama/{id}")
	public String deleteKosulAciklama(@PathVariable int id) {
		return kosulaciklamaService.deleteKosulAciklama(id);
	}

	// PATCH Request for single properties
	@RequestMapping(method = RequestMethod.PATCH, value = "/kosulaciklama/{id}/patchfields")
	public String patchKosulAciklama(@PathVariable int id, @RequestBody Map<Object, Object> fields) {
		KosulAciklama kosulaciklama = kosulaciklamaService.getKosulAciklama(id);

		fields.forEach((k, v) -> {
			Field field = ReflectionUtils.findField(KosulAciklama.class, (String) k);
			field.setAccessible(true);

			ReflectionUtils.setField(field, kosulaciklama, v);

		});

		return kosulaciklamaService.updateKosulAciklama(kosulaciklama);
	}
}
