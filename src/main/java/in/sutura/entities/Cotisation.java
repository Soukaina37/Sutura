package in.sutura.entities;

 
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Cotisation {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private double montant;
	private Date date = new java.sql.Date(System.currentTimeMillis());//Date de création de l'objet
	private int periode;//par défaut c'est l'année
	private Date expiration;//automatique qui correspond à la même de date de l'année suivant son inscription.
	//boolean expired;//false par défaut et devient true si la date d'expiration est dépassée (voir si c'est utile)
	private boolean approved;//false par défaut et true si la cotisation est approuvée (ajoutée)
	
	@ManyToOne
	@JoinColumn(name="CODE_ETU")
	private Etudiant etudiant;
	
	@ManyToOne
	@JoinColumn(name="CODE_ADMIN")
	private Administrateur administrateur;
	
	@ManyToOne
   	@JoinColumn(name="CODE_CAISSE")
   	private Caisse caisse;
	
	
	
	
	/*
	 * @OneToOne( cascade = CascadeType.ALL )
	 * 
	 * @JoinColumn( name="Justificatif" ) private Justificatif justificatif;
	 */
	 
	private boolean justificatif;
	
	/*
	 * Puisqu'on est pas encore en phase de déploiement, nous allons simplifier
	 * en changeant le type de l'attribut de justificatif en Booléen
	 * true si on suppose que l'objet a un justificatif et false sinon
	 */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
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

	/*
	 * public Justificatif getJustificatif() { return justificatif; }
	 * 
	 * public void setJustificatif(Justificatif justificatif) { this.justificatif =
	 * justificatif; }
	 */

}
