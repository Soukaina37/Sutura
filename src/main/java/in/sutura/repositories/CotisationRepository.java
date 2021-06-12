package in.sutura.repositories;

import java.util.List;
import java.util.Optional;

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
	public Optional<List<Cotisation>> findByEtudiant(@Param("x")Etudiant e1);

	@Query("SELECT c from Cotisation c where c.etudiant=:x AND c.periode = :y ORDER BY date ASC")
	public Optional<List<Cotisation>> findByEtudiantByPeriode(@Param("x")Etudiant e1, @Param("y")int periode);
	
	@Query("SELECT c from Cotisation c where c.caisse.id = :x ")
	public List<Cotisation> findCotisationByCaisse(@Param("x")Long id);
	
}
