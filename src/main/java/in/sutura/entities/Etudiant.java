package in.sutura.entities;

import java.sql.Date;
import java.util.Collection;

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

}
