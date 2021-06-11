package in.sutura.services;

import java.util.List;
import java.util.Optional;

import in.sutura.entities.Caisse;
import in.sutura.entities.Pret;

public interface PretService {

    Iterable<Pret> listAllPrets();

    Optional<Pret> getPretById(Long id);

    Pret savePret(Pret etudiant);

    void deletePret(Long id);
    
    long countPrets();
    
    void update(Pret pret);
    
    //CALCUL DE LA PRIORITE
    double calcul_priorite(Pret p);
    
    void classement_priorite(Pret p);
    
    double recalcul_priorite(Pret p);
    
    //RECALCUL DE LA PRIORITE
	
	//Obtenir la liste de tous les prêts concernant la même caisse 
	//et qui ne sont pas termine et supprime
	List<Pret> getList(Caisse caisse);

	Iterable<Pret> listAllPretsElu();

	Iterable<Pret> listAllPretsPret();

	Iterable<Pret> listAllPretsAutres();
    

}

