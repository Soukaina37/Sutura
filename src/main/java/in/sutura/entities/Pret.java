package in.sutura.entities;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Pret {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Date dateDemande;//de la création de l'objet
	private String raison;//liste déroulante (santé, alimentations, loyer, transport, autres)
	private Boolean statutTraitement;
	private String etat;//nouveau,prêt,bloqué,permuté, élu, terminé
	private double montant;
	private int periode;//correspond à l'année de la création de l'objet
	private double priorite; //=0 si l'état est nouveau et sera modifiée après le premier calcul de la priorité
	private boolean etatRemboursement;//par défaut false, et quand on crée un objet remboursement la valeur devient true
	private Date dateTraitement;
	private Long RIB;
	private Date echeance;//date laquelle l'étudiant prévoit de rembourser
	private Date dateModification;//initialisé dès la création de l'objet
	private String commentaire;
	
	@ManyToOne
	@JoinColumn(name="CODE_ETU")
	private Etudiant etudiant;
	
	@ManyToOne
	@JoinColumn(name="CODE_ADMIN")
	private Administrateur administrateur;
	
	@ManyToOne
	@JoinColumn(name="CODE_CAISSE")
	private Caisse caisse;
	
	@OneToMany(mappedBy="pret",fetch = FetchType.LAZY)
	private Collection <Remboursement> remboursements;
	
	/*
	 * @OneToOne( cascade = CascadeType.ALL )
	 * 
	 * @JoinColumn( name="Justificatif" ) private Justificatif justificatif;
	 */
	
	private boolean justificatif;
	
	/*
	 * pour l'attribut justificatifPretAccorde, on ne peut pas faire le mapping dans
	 * la même entité (Justificatif) que l'attribut justificatif
	 * pour garder l'attribut justificatifPretAccorde, la solution est de changer la 
	 * cardinalité de l'entité Pret vers l'entité Justificatif
	 * ce qui veut dire que Pret peut avoir jusqu'à deux attributs de types justificatif
	 * l'autre idée est d'avoir une autre entité JustificatifPretAccorde qui hérite de 
	 * Justificatif
	 * et ainsi avoir le discriminationValue
	 * A FAIRE PLUS TARD
	 * POUR L'INSTANT ON TRAVAILLE SANS justificatifPretAccorde
	 * */
	
	 /*
	  * Pour la première version, on ne s'intéressera pas aux fichiers de justificatif
	  * Pour la version suivante, nous aurons quelque chose comme ceci:
	  * @OneToOne( cascade = CascadeType.ALL ) 
	  * @JoinColumn( name="Justificatif" )
	  * private Justificatif collection<Justificatif> justificatifs;
	  * Dans pretform, on aura la possibilité d'avoir un justificatif pour le demandeur
	  * dans pretshow, on aura la possibilité d'ajour justificatifPretAccorde pour un administrateur
	  * 
	  */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateDemande() {
		return dateDemande;
	}

	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
	}

	public String getRaison() {
		return raison;
	}

	public void setRaison(String raison) {
		this.raison = raison;
	}

	public Boolean getStatutTraitement() {
		return statutTraitement;
	}

	public void setStatutTraitement(Boolean statutTraitement) {
		this.statutTraitement = statutTraitement;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public int getPeriode() {
		return periode;
	}

	public void setPeriode(int periode) {
		this.periode = periode;
	}

	public double getPriorite() {
		return priorite;
	}

	public void setPriorite(double priorite) {
		this.priorite = priorite;
	}

	public boolean isEtatRemboursement() {
		return etatRemboursement;
	}

	public void setEtatRemboursement(boolean etatRemboursement) {
		this.etatRemboursement = etatRemboursement;
	}

	public Date getDateTraitement() {
		return dateTraitement;
	}

	public void setDateTraitement(String dateTraitement) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		this.dateTraitement= df.parse(dateTraitement);
	}

	public Long getRIB() {
		return RIB;
	}

	public void setRIB(Long rIB) {
		RIB = rIB;
	}

	public Date getEcheance() {
		return echeance;
	}

	public void setEcheance(Date echeance) {
		this.echeance = echeance;
	}

	public Date getDateModification() {
		return dateModification;
	}

	public void setDateModification(String dateModification) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		this.dateModification= df.parse(dateModification);
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	public Collection<Remboursement> getRemboursements() {
		return remboursements;
	}

	public void setRemboursements(Collection<Remboursement> remboursements) {
		this.remboursements = remboursements;
	}

	public void setDateTraitement(Date dateTraitement) {
		this.dateTraitement = dateTraitement;
	}

	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	public Administrateur getAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(Administrateur administrateur) {
		this.administrateur = administrateur;
	}

	public Caisse getCaisse() {
		return caisse;
	}

	public void setCaisse(Caisse caisse) {
		this.caisse = caisse;
	}

	public boolean isJustificatif() {
		return justificatif;
	}

	public void setJustificatif(boolean justificatif) {
		this.justificatif = justificatif;
	}

	@Override
	public String toString() {
		return "Pret [id=" + id + ", raison=" + raison + ", montant=" + montant + ", periode=" + periode + ", etudiant="
				+ etudiant + ", administrateur=" + administrateur + "]";
	}

	
	
	

}
