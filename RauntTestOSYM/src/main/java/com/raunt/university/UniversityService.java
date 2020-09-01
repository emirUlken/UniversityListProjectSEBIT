package com.raunt.university;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raunt.entities.Department;
import com.raunt.entities.University;

//Spring business service
@Service
public class UniversityService {

	@Autowired
	private UniversityRepository universityRepository;

	public List<University> getAllUniversity() {
		List<University> universityList = new ArrayList<>();
		universityRepository.findAll().forEach(universityList::add);
		for (University uni : universityList) {
			for (Department dep : uni.getDepList()) {
				dep.setUni(null);
				dep.setDepDetails(null);
			}
		}
		return universityList;

	}

	public University getUniversity(int id) {
		University tempUni = universityRepository.findById(id).orElse(null);

		for (Department dep : tempUni.getDepList()) {
			dep.setUni(null);
			dep.setDepDetails(null);
		}

		return tempUni;
	}

	public String addUniversity(University university) {
		System.out.println("NAME: " + university.getName());
		System.out.println("CODE: " + university.getCode());
		if (university.getName().isEmpty() || university.getCode() == 0) {
			return "EMPTY";

		} else if (universityRepository.findByCode(university.getCode()) == null) {
			universityRepository.save(university);
			return "OK";

		} else {
		}
		return "FAIL";
	}

	public String addUniversityBulk(List<University> universityList) {
		long startPDFLoad = System.nanoTime();

		for (University uni : universityList) {
			universityRepository.save(uni);
		}

		long finishPDFLoad = System.nanoTime();

		long elapsedTime = finishPDFLoad - startPDFLoad;
		double elapsedTimeInSecond = (double) elapsedTime / 1_000_000_000;

		System.out.println("Time elapsed: " + elapsedTimeInSecond + " seconds");
		return "OK";

	}

	public String updateUniversity(University university) {
		if (universityRepository.findById(university.getId()) != null) {
			universityRepository.save(university);
			return "OK";

		} else {
			return "FAIL";
		}
	}

	public String deleteUniversity(int id) {
		University university = universityRepository.findById(id).orElse(null);
		if (university != null) {
			universityRepository.delete(university);
			return "OK";

		} else {
			return "FAIL";
		}
	}

	public String deleteDepFromList(int universityId, int departmentId) {
		University tempUni = getUniversity(universityId);
		University newUni = new University();
		List<Department> tempList = tempUni.getDepList();

		tempList.removeIf(s -> s.getId() == departmentId);
		newUni.setCode(tempUni.getCode());
		newUni.setId(tempUni.getId());
		newUni.setName(tempUni.getName());
		newUni.setDepList(tempList);

		universityRepository.save(newUni);

		return "OK";
	}

	public String addDepToList(int universityId, Department department) {
		University tempUni = getUniversity(universityId);
		University newUni = new University();
		List<Department> tempList = tempUni.getDepList();

		if (tempList.contains(department)) {
			return "FAIL";
		} else {
			tempList.add(department);

			newUni.setCode(tempUni.getCode());
			newUni.setId(tempUni.getId());
			newUni.setName(tempUni.getName());
			newUni.setDepList(tempList);

			universityRepository.save(newUni);

			return "OK";

		}

	}

	public String refreshDepartment() {
		List<University> universityList = new ArrayList<>();
		universityRepository.findAll().forEach(universityList::add);
		for (University uni : universityList) {
			for (Department dep : uni.getDepList()) {
				if (dep.getDepDetails() == null) {
					System.out.println("boşş");
					deleteDepFromList(uni.getId(), dep.getId());
				}
			}
		}
		return "OK";
	}

}
