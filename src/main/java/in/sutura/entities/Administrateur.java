package in.sutura.entities;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Administrateur")
public class Administrateur extends Etudiant {
	
	private static final long serialVersionUID = 1L;
	
	//on a enlevé l'id parce que Administrateur hérite l'id de Personne qui hérite à son tour l'id de Personne
	//car on a utilisé le mapping TABLE_PER_CLASS
	@Column(name="poste")
	private String poste;
	@Column(name="photo")
	private String photo;
	@Column(name="description")
	private String description;
	
	@OneToMany(mappedBy="administrateur",fetch = FetchType.LAZY)
	private Collection <Cotisation> cotisations;
	
	@OneToMany(mappedBy="administrateur",fetch = FetchType.LAZY)
	private Collection <Pret> prets;
	
	@OneToMany(mappedBy="administrateur",fetch = FetchType.LAZY)
	private Collection <Remboursement> remboursements;
	
	@OneToMany(mappedBy="administrateur",fetch = FetchType.LAZY)
	private Collection <Don> dons;
	
	@OneToMany(mappedBy="administrateur",fetch = FetchType.LAZY)
	private Collection <Depense> depenses;

	public String getPoste() {
		return poste;
	}

	public void setPoste(String poste) {
		this.poste = poste;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Collection<Cotisation> getCotisations() {
		return cotisations;
	}

	public void setCotisations(Collection<Cotisation> cotisations) {
		this.cotisations = cotisations;
	}

	public Collection<Pret> getPrets() {
		return prets;
	}

	public void setPrets(Collection<Pret> prets) {
		this.prets = prets;
	}

	public Collection<Remboursement> getRemboursements() {
		return remboursements;
	}

	public void setRemboursements(Collection<Remboursement> remboursements) {
		this.remboursements = remboursements;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

	
}
