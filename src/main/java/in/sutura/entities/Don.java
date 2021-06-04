package in.sutura.entities;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Don {
	@Id	  @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private double montant;
	private Date date;
	
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
	 * @OneToOne( cascade = CascadeType.ALL )
	 * 
	 * @JoinColumn( name="Donateur" ) private Donateur donateur ;
	 */
	/*
	 * Pour simplifier, nous allons ignorez la classe Donateur
	 * renseignez seulement le nom du bienfaiteur
	 */
	
	private String bienfaiteur;

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

	public String getBienfaiteur() {
		return bienfaiteur;
	}

	public void setBienfaiteur(String bienfaiteur) {
		this.bienfaiteur = bienfaiteur;
	}
	
	

}
