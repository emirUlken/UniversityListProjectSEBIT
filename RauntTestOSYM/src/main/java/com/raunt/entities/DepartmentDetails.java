package com.raunt.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class DepartmentDetails {
	@Id
	@SequenceGenerator(name = "depDetailsSeqGen", initialValue = 1)
	@GeneratedValue(generator = "depDetailsSeqGen", strategy = GenerationType.TABLE)
	private int id;

	private int ogrenimSuresi;

	// @JsonBackReference(value = "dep-puanTuru")
	// JsonBackReference and JsonManagedReference is no longer required.
	// Parts of entities that create infinite loop of JSON serialization
	// on GET Requests have been assigned
	// to "null" value.
	@ManyToOne(fetch = FetchType.EAGER)
	private PointType puanTuru;

	// @JsonBackReference(value = "dep-depDetails")
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dep_id")
	private Department dep;

	private int kontenjan;
	private int okulBirinciKontenjan;

	// @JsonBackReference(value = "kosul-depDetails")
	@ManyToMany
	@JoinTable(name = "kosul_depdetails", joinColumns = @JoinColumn(name = "depdetails_id"), inverseJoinColumns = @JoinColumn(name = "kosul_id"))
	private List<KosulAciklama> kosulAciklama = new ArrayList<>();

	private int basariSirasi;
	private float taban;
	private boolean fullStatus;

	public DepartmentDetails() {
		super();
	}

	public DepartmentDetails(int id, int ogrenimSuresi, PointType puanTuru, Department dep, int kontenjan,
			int okulBirinciKontenjan, List<KosulAciklama> kosulAciklama, int basariSirasi, float taban,
			boolean fullStatus) {
		super();
		this.id = id;
		this.ogrenimSuresi = ogrenimSuresi;
		this.puanTuru = puanTuru;
		this.dep = dep;
		this.kontenjan = kontenjan;
		this.okulBirinciKontenjan = okulBirinciKontenjan;
		this.kosulAciklama = kosulAciklama;
		this.basariSirasi = basariSirasi;
		this.taban = taban;
		this.fullStatus = fullStatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOgrenimSuresi() {
		return ogrenimSuresi;
	}

	public void setOgrenimSuresi(int ogrenimSuresi) {
		this.ogrenimSuresi = ogrenimSuresi;
	}

	public PointType getPuanTuru() {
		return puanTuru;
	}

	public void setPuanTuru(PointType puanTuru) {
		this.puanTuru = puanTuru;
	}

	public Department getDep() {
		return dep;
	}

	public void setDep(Department dep) {
		this.dep = dep;
	}

	public int getKontenjan() {
		return kontenjan;
	}

	public void setKontenjan(int kontenjan) {
		this.kontenjan = kontenjan;
	}

	public int getOkulBirinciKontenjan() {
		return okulBirinciKontenjan;
	}

	public void setOkulBirinciKontenjan(int okulBirinciKontenjan) {
		this.okulBirinciKontenjan = okulBirinciKontenjan;
	}

	public List<KosulAciklama> getKosulAciklama() {
		return kosulAciklama;
	}

	public void setKosulAciklama(List<KosulAciklama> kosulAciklama) {
		this.kosulAciklama = kosulAciklama;
	}

	public int getBasariSirasi() {
		return basariSirasi;
	}

	public void setBasariSirasi(int basariSirasi) {
		this.basariSirasi = basariSirasi;
	}

	public float getTaban() {
		return taban;
	}

	public void setTaban(float taban) {
		this.taban = taban;
	}

	public boolean isFullStatus() {
		return fullStatus;
	}

	public void setFullStatus(boolean fullStatus) {
		this.fullStatus = fullStatus;
	}

}
