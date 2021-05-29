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
public class Remboursement {
	@Id  @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	//int id_pret; pour le trouver on fait une recherche sur les deux tables car un étudiant ne peut pas avoir deux prêts en même temps
	private double montant;
	private Date date;
	private int periode;//correspond à l'année
	private String type;//remboursement en entier ou partiel
	private boolean statut;//true si c'est traité et false par défaut
    private int nbDeTranches;
    
    @ManyToOne
	@JoinColumn(name="CODE_ETU")
	private Etudiant etudiant;
    
    @ManyToOne
   	@JoinColumn(name="CODE_ADMIN")
   	private Administrateur adminsitrateur;
    
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isStatut() {
		return statut;
	}

	public void setStatut(boolean statut) {
		this.statut = statut;
	}

	public int getNbDeTranches() {
		return nbDeTranches;
	}

	public void setNbDeTranches(int nbDeTranches) {
		this.nbDeTranches = nbDeTranches;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	public Administrateur getAdminsitrateur() {
		return adminsitrateur;
	}

	public void setAdminsitrateur(Administrateur adminsitrateur) {
		this.adminsitrateur = adminsitrateur;
	}

	public Caisse getCaisses() {
		return caisse;
	}

	public void setCaisses(Caisse caisses) {
		this.caisse = caisses;
	}

	public Justificatif getJustificatif() {
		return justificatif;
	}

	public void setJustificatif(Justificatif justificatif) {
		this.justificatif = justificatif;
	}
    
    
	
}
