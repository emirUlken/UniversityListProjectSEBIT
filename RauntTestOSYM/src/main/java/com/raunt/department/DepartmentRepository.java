package com.raunt.department;

import org.springframework.data.repository.CrudRepository;

import com.raunt.entities.Department;

public interface DepartmentRepository extends CrudRepository<Department, Integer> {
	public Department findByName(String name);

	public Department findByCode(long code);
}
