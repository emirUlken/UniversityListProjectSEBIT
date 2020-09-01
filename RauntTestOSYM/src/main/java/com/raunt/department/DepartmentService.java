package com.raunt.department;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raunt.entities.Department;
import com.raunt.entities.KosulAciklama;

//Spring business service
@Service
public class DepartmentService {
	// Parts that create infinite JSON recursion are assigned to null.

	@Autowired
	private DepartmentRepository departmentRepository;

	public List<Department> getAllDepartment() {
		List<Department> departmentList = new ArrayList<>();
		departmentRepository.findAll().forEach(departmentList::add);

		for (Department dep : departmentList) {
			dep.getUni().setDepList(null);
			dep.getDepDetails().getPuanTuru().setDepDetailsListPoint(null);
			dep.getDepDetails().setDep(null);
			for (KosulAciklama kosul : dep.getDepDetails().getKosulAciklama()) {
				kosul.setDepDetailsKosul(null);
			}

		}
		return departmentList;

	}

	public Department getDepartment(int id) {
		Department tempDep = departmentRepository.findById(id).orElse(null);

		tempDep.getUni().setDepList(null);
		tempDep.getDepDetails().getPuanTuru().setDepDetailsListPoint(null);
		tempDep.getDepDetails().setDep(null);
		for (KosulAciklama kosul : tempDep.getDepDetails().getKosulAciklama()) {
			kosul.setDepDetailsKosul(null);
		}

		return tempDep;
	}

	public String addDepartment(Department department) {
		if (department.getName().isEmpty() || department.getUni() == null || department.getCode() == 0
				|| department.getDepDetails() == null) {
			return "EMPTY";
		}

		else if (departmentRepository.findByCode(department.getCode()) == null) {
			departmentRepository.save(department);
			return "OK";

		} else {
			return "FAIL";
		}
	}

	public String addDepartmentBulk(List<Department> departmentList) {
		long startPDFLoad = System.nanoTime();
		int count = 0;

		for (Department dep : departmentList) {
			count += 1;
			System.out.println(count);
			departmentRepository.save(dep);
		}

		long finishPDFLoad = System.nanoTime();

		long elapsedTime = finishPDFLoad - startPDFLoad;
		double elapsedTimeInSecond = (double) elapsedTime / 1_000_000_000;

		System.out.println("Time elapsed: " + elapsedTimeInSecond + " seconds");
		return "OK";

	}

	public String updateDepartment(Department department) {
		if (departmentRepository.findById(department.getId()) != null) {
			departmentRepository.save(department);
			return "OK";

		} else {
			return "FAIL";
		}
	}

	/*
	 * 
	 * public String refreshDepartment() { List<Department> departmentList = new
	 * ArrayList<>(); departmentRepository.findAll().forEach(departmentList::add);
	 * 
	 * for (Department dep : departmentList) { if (dep.getDepDetails() == null) {
	 * System.out.println("boş"); departmentRepository.delete(dep); } else {
	 * System.out.println("dolu"); } } return String.valueOf(departmentList.size());
	 * }
	 * 
	 */

	/*
	 * 
	 * public String deleteDepartment(int id) { Department department =
	 * departmentRepository.findById(id).orElse(null); if (department != null) {
	 * departmentRepository.delete(department); return "OK";
	 * 
	 * } else { return "FAIL"; } }
	 * 
	 */

}
