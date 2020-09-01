package com.raunt.university;

import org.springframework.data.repository.CrudRepository;

import com.raunt.entities.University;

public interface UniversityRepository extends CrudRepository<University, Integer> {
	public University findByName(String name);

	public University findByCode(int code);
}
