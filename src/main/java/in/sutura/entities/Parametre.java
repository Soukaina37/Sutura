package in.sutura.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Parametre implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name="id", unique=true, nullable=false)
    private Long id; 
	
	int periode = 1;
	
	//Paramètres pour le calcul de priorité
	
	int valeurUrgenceSante = 20;
	int valeurUrgenceAlimentation = 15;
	int valeurUrgenceLoyer = 10;
	int valeurUrgenceTransport = 5;
	
	int valeurRemboursementProcheSup4000 = 10;
	int valeurRemboursementProcheEntre3000et4000 = 6;
	int valeurRemboursementProcheEntre2000et3000 = 3;
	int valeurRemboursementProcheInf1000 = 1;
	
	int valeurMontantPretInf500 = 20;
	int valeurMontantPretEntre500et1000 = 10;
	int valeurMontantPretSup1000 = 2;
	
	int nombreRemboursementSup2 = 4;
	int nombreRemboursementEq1 = 2;
	int nombreRemboursementElse = 1;
	
	int ancienneteSup4 = 4;
	int ancienneteEntre2Et3 = 2;
	int ancienneteInf2 = 1;
	
	int genreHomme = 1;
	int genreFemme = 2;
	
	int nombreAnneeMarocInf2 = 2;
	int nombreAnneeElse = 0;
	
	int dernierRemboursementAvantEcheance = 4;
	
	int valeurEcheanceInf30 = 10;
	int valeurEcheanceEntre30Et60 = 2;
	int valeurEcheanceElse = 0;
	
	
	//Paramètres pour le recalcul de la priorité
	
	int valeurDureeSup30 = 5;
	int valeurDureeEntre15Et30 = 2;
	
	public int getValeurUrgenceSante() {
		return valeurUrgenceSante;
	}
	public void setValeurUrgenceSante(int valeurUrgenceSante) {
		this.valeurUrgenceSante = valeurUrgenceSante;
	}
	public int getValeurUrgenceAlimentation() {
		return valeurUrgenceAlimentation;
	}
	public void setValeurUrgenceAlimentation(int valeurUrgenceAlimentation) {
		this.valeurUrgenceAlimentation = valeurUrgenceAlimentation;
	}
	public int getValeurUrgenceLoyer() {
		return valeurUrgenceLoyer;
	}
	public void setValeurUrgenceLoyer(int valeurUrgenceLoyer) {
		this.valeurUrgenceLoyer = valeurUrgenceLoyer;
	}
	public int getValeurUrgenceTransport() {
		return valeurUrgenceTransport;
	}
	public void setValeurUrgenceTransport(int valeurUrgenceTransport) {
		this.valeurUrgenceTransport = valeurUrgenceTransport;
	}
	public int getValeurRemboursementProcheSup4000() {
		return valeurRemboursementProcheSup4000;
	}
	public void setValeurRemboursementProcheSup4000(int valeurRemboursementProcheSup4000) {
		this.valeurRemboursementProcheSup4000 = valeurRemboursementProcheSup4000;
	}
	public int getValeurRemboursementProcheEntre3000et4000() {
		return valeurRemboursementProcheEntre3000et4000;
	}
	public void setValeurRemboursementProcheEntre3000et4000(int valeurRemboursementProcheEntre3000et4000) {
		this.valeurRemboursementProcheEntre3000et4000 = valeurRemboursementProcheEntre3000et4000;
	}
	public int getValeurRemboursementProcheEntre2000et3000() {
		return valeurRemboursementProcheEntre2000et3000;
	}
	public void setValeurRemboursementProcheEntre2000et3000(int valeurRemboursementProcheEntre2000et3000) {
		this.valeurRemboursementProcheEntre2000et3000 = valeurRemboursementProcheEntre2000et3000;
	}
	public int getValeurRemboursementProcheInf1000() {
		return valeurRemboursementProcheInf1000;
	}
	public void setValeurRemboursementProcheInf1000(int valeurRemboursementProcheInf1000) {
		this.valeurRemboursementProcheInf1000 = valeurRemboursementProcheInf1000;
	}
	public int getValeurMontantPretInf500() {
		return valeurMontantPretInf500;
	}
	public void setValeurMontantPretInf500(int valeurMontantPretInf500) {
		this.valeurMontantPretInf500 = valeurMontantPretInf500;
	}
	public int getValeurMontantPretSup1000() {
		return valeurMontantPretSup1000;
	}
	public void setValeurMontantPretSup1000(int valeurMontantPretSup1000) {
		this.valeurMontantPretSup1000 = valeurMontantPretSup1000;
	}
	public int getNombreRemboursementSup2() {
		return nombreRemboursementSup2;
	}
	public void setNombreRemboursementSup2(int nombreRemboursementSup2) {
		this.nombreRemboursementSup2 = nombreRemboursementSup2;
	}
	public int getNombreRemboursementEq1() {
		return nombreRemboursementEq1;
	}
	public void setNombreRemboursementEq1(int nombreRemboursementEq1) {
		this.nombreRemboursementEq1 = nombreRemboursementEq1;
	}
	public int getNombreRemboursementElse() {
		return nombreRemboursementElse;
	}
	public void setNombreRemboursementElse(int nombreRemboursementElse) {
		this.nombreRemboursementElse = nombreRemboursementElse;
	}
	public int getAncienneteSup4() {
		return ancienneteSup4;
	}
	public void setAncienneteSup4(int ancienneteSup4) {
		this.ancienneteSup4 = ancienneteSup4;
	}
	public int getAncienneteEntre2Et3() {
		return ancienneteEntre2Et3;
	}
	public void setAncienneteEntre2Et3(int ancienneteEntre2Et3) {
		this.ancienneteEntre2Et3 = ancienneteEntre2Et3;
	}
	public int getAncienneteInf2() {
		return ancienneteInf2;
	}
	public void setAncienneteInf2(int ancienneteInf2) {
		this.ancienneteInf2 = ancienneteInf2;
	}
	public int getGenreHomme() {
		return genreHomme;
	}
	public void setGenreHomme(int genreHomme) {
		this.genreHomme = genreHomme;
	}
	
	public int getValeurMontantPretEntre500et1000() {
		return valeurMontantPretEntre500et1000;
	}
	public void setValeurMontantPretEntre500et1000(int valeurMontantPretEntre500et1000) {
		this.valeurMontantPretEntre500et1000 = valeurMontantPretEntre500et1000;
	}
	public int getGenreFemme() {
		return genreFemme;
	}
	public void setGenreFemme(int genreFemme) {
		this.genreFemme = genreFemme;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getPeriode() {
		return periode;
	}
	public void setPeriode(int periode) {
		this.periode = periode;
	}
	public int getNombreAnneeMarocInf2() {
		return nombreAnneeMarocInf2;
	}
	public void setNombreAnneeMarocInf2(int nombreAnneeMarocInf2) {
		this.nombreAnneeMarocInf2 = nombreAnneeMarocInf2;
	}
	public int getNombreAnneeElse() {
		return nombreAnneeElse;
	}
	public void setNombreAnneeElse(int nombreAnneeElse) {
		this.nombreAnneeElse = nombreAnneeElse;
	}
	public int getDernierRemboursementAvantEcheance() {
		return dernierRemboursementAvantEcheance;
	}
	public void setDernierRemboursementAvantEcheance(int dernierRemboursementAvantEcheance) {
		this.dernierRemboursementAvantEcheance = dernierRemboursementAvantEcheance;
	}
	public int getValeurEcheanceInf30() {
		return valeurEcheanceInf30;
	}
	public void setValeurEcheanceInf30(int valeurEcheanceInf30) {
		this.valeurEcheanceInf30 = valeurEcheanceInf30;
	}
	public int getValeurEcheanceEntre30Et60() {
		return valeurEcheanceEntre30Et60;
	}
	public void setValeurEcheanceEntre30Et60(int valeurEcheanceEntre30Et60) {
		this.valeurEcheanceEntre30Et60 = valeurEcheanceEntre30Et60;
	}
	public int getValeurEcheanceElse() {
		return valeurEcheanceElse;
	}
	public void setValeurEcheanceElse(int valeurEcheanceElse) {
		this.valeurEcheanceElse = valeurEcheanceElse;
	}
	public int getValeurDureeSup30() {
		return valeurDureeSup30;
	}
	public void setValeurDureeSup30(int valeurDureeSup30) {
		this.valeurDureeSup30 = valeurDureeSup30;
	}
	public int getValeurDureeEntre15Et30() {
		return valeurDureeEntre15Et30;
	}
	public void setValeurDureeEntre15Et30(int valeurDureeEntre15Et30) {
		this.valeurDureeEntre15Et30 = valeurDureeEntre15Et30;
	}
	public Parametre() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
