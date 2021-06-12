package in.sutura.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import in.sutura.entities.Don;


public interface DonRepository extends JpaRepository<Don, Long> {

	@Query("SELECT d from Don d where d.caisse.id = :x ")
	public List<Don> findDonByCaisse(@Param("x")Long id);
	
}
