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
public class Depense {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int idAdmin;
	private Date date;
	private double montant;
	private String description;
	
	@ManyToOne
	@JoinColumn(name="CODE_ADMIN")
	private Administrateur administrateur;
	
	@ManyToOne
	@JoinColumn(name="CODE_CAISSE")
	private Caisse caisse;
	
	@OneToOne( cascade = CascadeType.ALL ) 
    @JoinColumn( name="T_Justificatif" )
    private Justificatif justificatif;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(int idAdmin) {
		this.idAdmin = idAdmin;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
