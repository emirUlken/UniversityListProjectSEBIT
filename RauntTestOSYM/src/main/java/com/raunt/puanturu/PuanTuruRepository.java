package com.raunt.puanturu;

import org.springframework.data.repository.CrudRepository;

import com.raunt.entities.PointType;

public interface PuanTuruRepository extends CrudRepository<PointType, Integer> {
	public PointType findByType(String type);
}
