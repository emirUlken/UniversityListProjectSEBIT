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
public class PointType {
	@Id
	@SequenceGenerator(name = "pointSeqGen", initialValue = 1)
	@GeneratedValue(generator = "pointSeqGen")
	private int id;

	private String type;

	// @JsonManagedReference(value="dep-puanTuru")
	// JsonBackReference and JsonManagedReference is no longer required.
	// Parts of entities that create infinite loop of JSON serialization
	// on GET Requests have been assigned
	// to "null" value.
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "puanTuru", cascade = CascadeType.ALL)
	@ElementCollection(targetClass = DepartmentDetails.class)
	private List<DepartmentDetails> depDetailsListPoint;

	public PointType() {
		super();
	}

	public PointType(int id, String type, List<DepartmentDetails> depDetailsListPoint) {
		super();
		this.id = id;
		this.type = type;
		this.depDetailsListPoint = depDetailsListPoint;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<DepartmentDetails> getDepDetailsListPoint() {
		return depDetailsListPoint;
	}

	public void setDepDetailsListPoint(List<DepartmentDetails> depDetailsListPoint) {
		this.depDetailsListPoint = depDetailsListPoint;
	}

}
