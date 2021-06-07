package in.sutura.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import in.sutura.entities.Cotisation;
import in.sutura.entities.Etudiant;

@Repository
@Service
public interface CotisationRepository extends JpaRepository<Cotisation, Long>{
	@Query("SELECT c from Cotisation c where c.etudiant=:x ORDER BY date ASC")
	public List<Cotisation> findByEtudiant(@Param("x")Etudiant e1);
	
	//et non, pour avoir la liste par ordre
	//public List<Cotisation> findByEtudiant(Etudiant e);

}
