package com.raunt.kosulaciklama;

import org.springframework.data.repository.CrudRepository;

import com.raunt.entities.KosulAciklama;

public interface KosulAciklamaRepository extends CrudRepository<KosulAciklama, Integer> {
	public KosulAciklama findByNumber(int number);
}
