package in.sutura.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import in.sutura.entities.Caisse;


public interface CaisseRepository extends JpaRepository<Caisse, Long>{
	@Modifying
	@Transactional
	@Query("UPDATE Caisse c SET c=:caisse WHERE c.id = :id")
	public int update(@Param("id") Long id, @Param("caisse") Caisse caisse);

}
