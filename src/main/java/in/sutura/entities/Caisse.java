package in.sutura.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Caisse {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Boolean isFavorable;
	private double seuil;
	private double montantCaisse;
	private double montantActuel;
	private int periode = 1;
	
	@OneToMany(mappedBy="caisse",fetch = FetchType.LAZY)
	private Collection <Don> dons;
	
	@OneToMany(mappedBy="caisse",fetch = FetchType.LAZY)
	private Collection <Depense> depenses;
	
	@OneToMany(mappedBy="caisse",fetch = FetchType.LAZY)
	private Collection <Cotisation> cotisations;
	
	@OneToMany(mappedBy="caisse",fetch = FetchType.LAZY)
	private Collection <Remboursement> remboursements;
	
	@OneToMany(mappedBy="caisse",fetch = FetchType.LAZY)
	private Collection <Pret> prets;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getIsFavorable() {
		return isFavorable;
	}

	public void setIsFavorable(Boolean isFavorable) {
		this.isFavorable = isFavorable;
	}

	public double getSeuil() {
		return seuil;
	}

	public void setSeuil(double seuil) {
		this.seuil = seuil;
	}

	public double getMontantCaisse() {
		return montantCaisse;
	}

	public void setMontantCaisse(double montantCaisse) {
		this.montantCaisse = montantCaisse;
	}

	public double getMontantActuel() {
		return montantActuel;
	}

	public void setMontantActuel(double montantActuel) {
		this.montantActuel = montantActuel;
	}
	
	public int getPeriode() {
		return periode;
	}

	public void setPeriode(int periode) {
		this.periode = periode;
	}

	public Collection<Don> getDons() {
		return dons;
	}

	public void setDons(Collection<Don> dons) {
		this.dons = dons;
	}

	public Collection<Depense> getDepenses() {
		return depenses;
	}

	public void setDepenses(Collection<Depense> depenses) {
		this.depenses = depenses;
	}

	public Collection<Cotisation> getCotisations() {
		return cotisations;
	}

	public void setCotisations(Collection<Cotisation> cotisations) {
		this.cotisations = cotisations;
	}

	public Collection<Remboursement> getRemboursements() {
		return remboursements;
	}

	public void setRemboursements(Collection<Remboursement> remboursements) {
		this.remboursements = remboursements;
	}

	public Collection<Pret> getPrets() {
		return prets;
	}

	public void setPrets(Collection<Pret> prets) {
		this.prets = prets;
	}

	public Caisse() {
		super();
	}

	@Override
	public String toString() {
		return "Caisse [id=" + id + ", isFavorable=" + isFavorable + ", seuil=" + seuil + ", montantCaisse="
				+ montantCaisse + ", montantActuel=" + montantActuel + ", periode=" + periode + "]";
	}
	
	

	

	

	

	

	
	
	
	
	
	


	

}
