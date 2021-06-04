package in.sutura.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "Personne")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Personne implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id  @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
    private Long id; //il faut nécéssairement utilisé le type Long dans ce cas
	// et dans PersonneRepository, on doit ajouter l'annotation @Primary
	@Column(name="prenom")
    private String prenom;
	@Column(name="nom")
    private String nom;
	@Column(name="email")
    private String email;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Personne [id=" + id + ", prenom=" + prenom + ", nom=" + nom + ", email=" + email + "]";
	}
	
	
	

}
 