package com.raunt.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class KosulAciklama {
	@Id
	@SequenceGenerator(name = "kosulSeqGen", initialValue = 1)
	@GeneratedValue(generator = "kosulSeqGen", strategy = GenerationType.TABLE)
	private int id;

	private int number;

	@Column(columnDefinition = "CLOB")
	@Lob
	private String description;

	// @JsonBackReference(value = "kosul-depDetails")
	// JsonBackReference and JsonManagedReference is no longer required.
	// Parts of entities that create infinite loop of JSON serialization
	// on GET Requests have been assigned
	// to "null" value.
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "kosul_depdetails", joinColumns = @JoinColumn(name = "kosul_id"), inverseJoinColumns = @JoinColumn(name = "depdetails_id"))
	private List<DepartmentDetails> depDetailsKosul = new ArrayList<>();

	public KosulAciklama() {
		super();
	}

	public KosulAciklama(int id, int number, String description, List<DepartmentDetails> depDetailsKosul) {
		super();
		this.id = id;
		this.number = number;
		this.description = description;
		this.depDetailsKosul = depDetailsKosul;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<DepartmentDetails> getDepDetailsKosul() {
		return depDetailsKosul;
	}

	public void setDepDetailsKosul(List<DepartmentDetails> depDetailsKosul) {
		this.depDetailsKosul = depDetailsKosul;
	}

}
