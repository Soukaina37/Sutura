package in.sutura.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import in.sutura.entities.Parametre;

public interface ParametreRepository extends JpaRepository<Parametre, Long>{
	@Query("SELECT p from Parametre p where p.periode=:x ")
	Parametre findByPeriode(@Param("x")int periode);
	
}
