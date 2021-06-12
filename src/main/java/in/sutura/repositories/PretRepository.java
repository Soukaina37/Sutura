package in.sutura.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import in.sutura.entities.Etudiant;
import in.sutura.entities.Pret;

@Repository
@Service
public interface PretRepository extends JpaRepository<Pret, Long>{
	
	@Modifying
	@Transactional
	@Query("UPDATE Pret p SET p=:pret WHERE p.id = :id")
	public int update(@Param("id") Long id, @Param("pret") Pret pret);
	
	
	@Modifying
	@Transactional
	@Query("SELECT p from Pret p where p.etudiant = :x AND p.etatRemboursement = 'true' ORDER BY p.dateDemande ASC")
	List<Pret> findByEtudiantOrdonne(@Param("x") Etudiant e); 
	
	@Query("SELECT p from Pret p where p.etat = 'termine' AND p.etatRemboursement = 'false'")
	public List<Pret> remboursement_proches();
	
	@Query("SELECT p from Pret p where p.etat != 'termine' AND p.etat != 'supprime' ORDER BY p.priorite DESC")
	public List<Pret> findAllForRecalcul();

	@Query("SELECT p from Pret p where p.etat = 'elu' ORDER BY p.priorite DESC")
	public Iterable<Pret> findElu();

	@Query("SELECT p from Pret p where p.etat = 'pret' ORDER BY p.priorite DESC")
	public Iterable<Pret> findPret();

	@Query("SELECT p from Pret p where p.etat != 'elu' AND p.etat != 'pret' AND p.etat != 'termine' ORDER BY p.priorite DESC")
	public Iterable<Pret> findAutres();
	
	@Query("SELECT p from Pret p where p.etat = 'termine' ORDER BY p.dateModification DESC")
	public Iterable<Pret> findTermines();
	
	@Modifying
	@Transactional
	@Query("Select p from Pret p where p.etudiant = :x AND p.etatRemboursement = 'false' AND p.etat != 'supprime'")
	public List<Pret> PretEnCours(@Param("x") Etudiant etudiant);
	
}
