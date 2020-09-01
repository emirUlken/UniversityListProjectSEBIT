package com.raunt.depdetails;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raunt.entities.DepartmentDetails;
import com.raunt.entities.KosulAciklama;

//Spring business service
@Service
public class DepDetailsService {
	// Parts that create infinite JSON recursion are assigned to null.

	@Autowired
	private DepDetailsRepository depDetailsRepository;

	public List<DepartmentDetails> getAllDepartmentDetails() {
		List<DepartmentDetails> depDetailsList = new ArrayList<>();
		depDetailsRepository.findAll().forEach(depDetailsList::add);
		for(DepartmentDetails depDetail: depDetailsList) {
			depDetail.getPuanTuru().setDepDetailsListPoint(null);
			depDetail.setDep(null);
			for(KosulAciklama kosul: depDetail.getKosulAciklama()) {
				kosul.setDepDetailsKosul(null);
			}
			
			//depDetail.getDep().getUni().setDepList(null);
			//depDetail.getDep().setDepDetails(null);
		}
		return depDetailsList;

	}

	public DepartmentDetails getDepartmentDetails(int id) {
		DepartmentDetails tempDetails = depDetailsRepository.findById(id).orElse(null);
		
		tempDetails.getPuanTuru().setDepDetailsListPoint(null);
		tempDetails.setDep(null);
		for(KosulAciklama kosul: tempDetails.getKosulAciklama()) {
			kosul.setDepDetailsKosul(null);
		}
		
		return tempDetails;
	}

	public String addDepartmentDetails(DepartmentDetails depDetails) {
		/*
		if (depDetailsRepository.findByCode(depDetails.getCode()) == null) {
			depDetailsRepository.save(depDetails);
			return "OK";

		} else {
			return "FAIL";
		}
		*/
		return "OK";
	}

	public String addDepartmentDetailsBulk(List<DepartmentDetails> depDetailsList) {
		long startPDFLoad = System.nanoTime();
		int count = 0;
		
		for (DepartmentDetails dep : depDetailsList) {
			// Numbers'dan ne geldiğinin önemi yok, olunca tek tek karşılık gelenleri ekle.
			/*
			DepartmentDetails tempDep = dep;
			List<KosulAciklama> tempList = new ArrayList<>();
			tempList.add(new KosulAciklama(1, 1, "test description 1"));
			tempList.add(new KosulAciklama(2, 2, "test description 2"));
			tempList.add(new KosulAciklama(3, 3, "test description 3"));
			tempDep.getDepDetails().setKosulAciklama(tempList);
			*/
			// Bu kısmı düzelt, aldığın gerçek dep i ekle.
			count += 1;
			System.out.println(count);
			depDetailsRepository.save(dep);
		}
		
		long finishPDFLoad = System.nanoTime();

		long elapsedTime = finishPDFLoad - startPDFLoad;
		double elapsedTimeInSecond = (double) elapsedTime / 1_000_000_000;

		System.out.println("Time elapsed: " + elapsedTimeInSecond + " seconds");
		return "OK";

	}

	public String updateDepartmentDetails(DepartmentDetails depDetails) {
		if (depDetailsRepository.findById(depDetails.getId()) != null) {
			depDetailsRepository.save(depDetails);
			return "OK";

		} else {
			return "FAIL";
		}
	}

	public String deleteDepartmentDetails(int id) {
		DepartmentDetails depDetails = depDetailsRepository.findById(id).orElse(null);
		if (depDetails != null) {
			depDetailsRepository.delete(depDetails);
			return "OK";

		} else {
			return "FAIL";
		}
	}

}
