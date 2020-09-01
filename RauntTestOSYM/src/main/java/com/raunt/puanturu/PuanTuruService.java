package com.raunt.puanturu;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raunt.entities.DepartmentDetails;
import com.raunt.entities.KosulAciklama;
import com.raunt.entities.PointType;

//Spring business service
@Service
public class PuanTuruService {
	// Parts that create infinite JSON recursion are assigned to null.

	@Autowired
	private PuanTuruRepository pointTypeRepository;

	public List<PointType> getAllPointType() {
		List<PointType> pointTypeList = new ArrayList<>();
		pointTypeRepository.findAll().forEach(pointTypeList::add);
		for (PointType point : pointTypeList) {
			for (DepartmentDetails depDetail : point.getDepDetailsListPoint()) {
				depDetail.setPuanTuru(null);
				depDetail.setDep(null);

				// depDetail.getDep().getUni().setDepList(null);
				// depDetail.getDep().setDepDetails(null);

				for (KosulAciklama kosul : depDetail.getKosulAciklama()) {
					kosul.setDepDetailsKosul(null);
				}

			}
		}
		return pointTypeList;

	}

	public PointType getPointType(int id) {
		PointType tempPoint = pointTypeRepository.findById(id).orElse(null);
		for (DepartmentDetails depDetail : tempPoint.getDepDetailsListPoint()) {
			depDetail.setPuanTuru(null);
			depDetail.setDep(null);

			// depDetail.getDep().getUni().setDepList(null);
			// depDetail.getDep().setDepDetails(null);

			for (KosulAciklama kosul : depDetail.getKosulAciklama()) {
				kosul.setDepDetailsKosul(null);
			}
		}

		return tempPoint;
	}

	public String addPointType(PointType pointType) {
		if (pointType.getType().isEmpty()) {
			return "EMPTY";
		}

		else if (pointType.getType() != null && pointTypeRepository.findByType(pointType.getType()) == null) {
			pointTypeRepository.save(pointType);
			return "OK";

		} else {
			return "FAIL";
		}
	}

	public String addPointTypeBulk(List<PointType> pointTypeList) {
		long startPDFLoad = System.nanoTime();

		for (PointType point : pointTypeList) {
			pointTypeRepository.save(point);
		}

		long finishPDFLoad = System.nanoTime();

		long elapsedTime = finishPDFLoad - startPDFLoad;
		double elapsedTimeInSecond = (double) elapsedTime / 1_000_000_000;

		System.out.println("Time elapsed: " + elapsedTimeInSecond + " seconds");
		return "OK";

	}

	public String updatePointType(PointType pointType) {
		if (getPointType(pointType.getId()) != null) {
			pointTypeRepository.save(pointType);
			return "OK";

		} else {
			return "FAIL";
		}
	}

	public String deletePointType(int id) {
		PointType pointType = getPointType(id);
		if (pointType != null) {
			pointTypeRepository.delete(pointType);
			return "OK";

		} else {
			return "FAIL";
		}
	}

}
