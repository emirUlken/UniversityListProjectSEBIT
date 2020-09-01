package com.raunt.kosulaciklama;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raunt.entities.DepartmentDetails;
import com.raunt.entities.KosulAciklama;

//Spring business service
@Service
public class KosulAciklamaService {
	// Parts that create infinite JSON recursion are assigned to null.

	@Autowired
	private KosulAciklamaRepository kosulAciklamaRepository;

	public List<KosulAciklama> getAllKosulAciklama() {
		List<KosulAciklama> kosulAciklamaList = new ArrayList<>();
		kosulAciklamaRepository.findAll().forEach(kosulAciklamaList::add);

		for (KosulAciklama kosul : kosulAciklamaList) {
			for (DepartmentDetails depDetail : kosul.getDepDetailsKosul()) {
				depDetail.getPuanTuru().setDepDetailsListPoint(null);
				depDetail.setDep(null);
				depDetail.setKosulAciklama(null);
			}
		}

		return kosulAciklamaList;

	}

	public KosulAciklama getKosulAciklama(int id) {
		KosulAciklama tempKosul = kosulAciklamaRepository.findById(id).orElse(null);

		for (DepartmentDetails depDetail : tempKosul.getDepDetailsKosul()) {
			depDetail.getPuanTuru().setDepDetailsListPoint(null);
			depDetail.setDep(null);
			depDetail.setKosulAciklama(null);
		}

		return tempKosul;
	}

	public String addKosulAciklama(KosulAciklama kosulaciklama) {
		if (kosulaciklama.getNumber() == 0 || kosulaciklama.getDescription().isEmpty()) {
			return "EMPTY";
		}

		else if (kosulAciklamaRepository.findByNumber(kosulaciklama.getNumber()) == null) {
			kosulAciklamaRepository.save(kosulaciklama);
			return "OK";

		} else {
			return "FAIL";
		}
	}

	public String addKosulAciklamaBulk(List<KosulAciklama> kosulaciklamaList) {
		long startPDFLoad = System.nanoTime();

		for (KosulAciklama kos : kosulaciklamaList) {
			kosulAciklamaRepository.save(kos);
		}

		long finishPDFLoad = System.nanoTime();

		long elapsedTime = finishPDFLoad - startPDFLoad;
		double elapsedTimeInSecond = (double) elapsedTime / 1_000_000_000;

		System.out.println("Time elapsed: " + elapsedTimeInSecond + " seconds");
		return "OK";

	}

	public String updateKosulAciklama(KosulAciklama kosulaciklama) {
		if (getKosulAciklama(kosulaciklama.getId()) != null) {
			kosulAciklamaRepository.save(kosulaciklama);
			return "OK";

		} else {
			return "FAIL";
		}
	}

	public String deleteKosulAciklama(int id) {
		KosulAciklama kosulaciklama = getKosulAciklama(id);
		if (getKosulAciklama(id) != null) {
			kosulAciklamaRepository.delete(kosulaciklama);
			return "OK";

		} else {
			return "FAIL";
		}
	}

}
