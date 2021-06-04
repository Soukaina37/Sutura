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
	
	/*
	 * si on redéfinit un constructeur dans cette classe Administrateur,
	 * on aura des problèmes d'affichage au niveau de l'affichage de:
	 * <p class="form-control-static" th:text="${pret.administrateur}">
	 * dans pretshow.html
	 */
}
