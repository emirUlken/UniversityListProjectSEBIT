package com.raunt.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Department {
	@Id
	@SequenceGenerator(name = "departmentSeqGen", initialValue = 1)
	@GeneratedValue(generator = "departmentSeqGen")
	private int id;

	private String name;

	// @JsonBackReference(value="uni-dep")
	// JsonBackReference and JsonManagedReference is no longer required.
	// Parts of entities that create infinite loop of JSON serialization
	// on GET Requests have been assigned
	// to "null" value.
	@ManyToOne(fetch = FetchType.EAGER)
	private University uni;

	private long code;

	// @JsonManagedReference(value = "dep-depDetails")
	@OneToOne(mappedBy = "dep", cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
	private DepartmentDetails depDetails;

	public Department() {
		super();
	}

	public Department(int id, String name, University uni, long code, DepartmentDetails depDetails) {
		super();
		this.id = id;
		this.name = name;
		this.uni = uni;
		this.code = code;
		this.depDetails = depDetails;
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

	public University getUni() {
		return uni;
	}

	public void setUni(University uni) {
		this.uni = uni;
	}

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public DepartmentDetails getDepDetails() {
		return depDetails;
	}

	public void setDepDetails(DepartmentDetails depDetails) {
		if (depDetails == null) {
			if (this.depDetails != null) {
				this.depDetails.setDep(null);
			}
		} else {
			depDetails.setDep(this);
		}
		this.depDetails = depDetails;
	}

}
