package com.raunt.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class University {
	@Id
	@SequenceGenerator(name = "uniSeqGen", initialValue = 1)
	@GeneratedValue(generator = "uniSeqGen")
	private int id;

	private String name;
	private int code;

	// @JsonManagedReference(value="uni-dep")
	// JsonBackReference and JsonManagedReference is no longer required.
	// Parts of entities that create infinite loop of JSON serialization
	// on GET Requests have been assigned
	// to "null" value.
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "uni", cascade = CascadeType.ALL, orphanRemoval = true)
	@ElementCollection(targetClass = Department.class)
	private List<Department> depList;

	public University() {
	}

	public University(int id, String name, int code, List<Department> depList) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.depList = depList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public List<Department> getDepList() {
		return depList;
	}

	public void setDepList(List<Department> depList) {
		this.depList = depList;
	}

}
