package in.sutura.entities;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Etudiant")
public class Etudiant extends Personne {
	
	private static final long serialVersionUID = 1L;
	
	//on a enlevé l'id parce que Etudiant hérite l'id de Personne
	//car on a utilisé le mapping TABLE_PER_CLASS
	@Column(name="motDePasse")
	private String motDePasse;
	@Column(name="ville")
    private String ville;
	@Column(name="etablissement")
    private String etablissement;
	@Column(name="filiere")
    private String filiere;
	@Column(name="sexe")
    private String sexe;
	@Column(name="paysOrigine")
    private String paysOrigine;
	@Column(name="nbAnneeMaroc")
    private int nbAnneeMaroc;
	@Column(name="dateInscription")
    private String dateInscription;
	@Column(name="telephone")
    private int Telephone;
	@Column(name="updateTableauDeBord")
    private Date updateTableauDeBord;
	
	
	@OneToMany(mappedBy="etudiant",fetch = FetchType.LAZY)
	private Collection <Cotisation> cotisations;
	
	@OneToMany(mappedBy="etudiant",fetch = FetchType.LAZY)
	private Collection <Pret> prets;
	
	@OneToMany(mappedBy="etudiant",fetch = FetchType.LAZY)
	private Collection <Remboursement> remboursements;

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getEtablissement() {
		return etablissement;
	}

	public void setEtablissement(String etablissement) {
		this.etablissement = etablissement;
	}

	public String getFiliere() {
		return filiere;
	}

	public void setFiliere(String filiere) {
		this.filiere = filiere;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getPaysOrigine() {
		return paysOrigine;
	}

	public void setPaysOrigine(String paysOrigine) {
		this.paysOrigine = paysOrigine;
	}

	public int getNbAnneeMaroc() {
		return nbAnneeMaroc;
	}

	public void setNbAnneeMaroc(int nbAnneeMaroc) {
		this.nbAnneeMaroc = nbAnneeMaroc;
	}

	public String getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(String dateInscription) {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		this.dateInscription = df.format(dateInscription);
	}

	public int getTelephone() {
		return Telephone;
	}

	public void setTelephone(int telephone) {
		Telephone = telephone;
	}

	public Date getUpdateTableauDeBord() {
		return updateTableauDeBord;
	}

	public void setUpdateTableauDeBord(Date updateTableauDeBord) {
		this.updateTableauDeBord = updateTableauDeBord;
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

	public Etudiant() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
