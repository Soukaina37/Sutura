package in.sutura.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import in.sutura.entities.Etudiant;
import in.sutura.entities.Remboursement;

@Repository
@Service
public interface RemboursementRepository extends JpaRepository <Remboursement, Long> {

	public List<Remboursement> findByEtudiant(Etudiant e);
	
	

}
