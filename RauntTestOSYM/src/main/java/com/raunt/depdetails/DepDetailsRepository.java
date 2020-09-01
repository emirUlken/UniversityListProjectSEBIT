package com.raunt.depdetails;

import org.springframework.data.repository.CrudRepository;

import com.raunt.entities.DepartmentDetails;

public interface DepDetailsRepository extends CrudRepository<DepartmentDetails, Integer> {
}
