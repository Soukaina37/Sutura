package in.sutura.entities;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Pret {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Date dateDemande;
	private String raison;//liste déroulante (santé, alimentations, loyer, transport, autres)
	private Boolean statutTraitement;
	private String etat;//nouveau,prêt,bloqué,permuté, élu, terminé
	private double montant;
	private Date date;//de la création de l'objet
	private int periode;//correspond à l'année de la création de l'objet
	private double priorite; //=0 si l'état est nouveau et sera modifiée après le premier calcul de la priorité
	private boolean etatRemboursement;//par défaut false, et quand on crée un objet remboursement la valeur devient true
	private Date dateTraitement;
	private int idJustificatif;
	private String justificatifPretAccorde;
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
	
	@OneToOne( cascade = CascadeType.ALL ) 
    @JoinColumn( name="Justificatif" )
    private Justificatif justificatif;

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public void setDateTraitement(Date dateTraitement) {
		this.dateTraitement = dateTraitement;
	}

	public int getIdJustificatif() {
		return idJustificatif;
	}

	public void setIdJustificatif(int idJustificatif) {
		this.idJustificatif = idJustificatif;
	}

	public String getJustificatifPretAccorde() {
		return justificatifPretAccorde;
	}

	public void setJustificatifPretAccorde(String justificatifPretAccorde) {
		this.justificatifPretAccorde = justificatifPretAccorde;
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

	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
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

	public Justificatif getJustificatif() {
		return justificatif;
	}

	public void setJustificatif(Justificatif justificatif) {
		this.justificatif = justificatif;
	}
	
	
	
}
