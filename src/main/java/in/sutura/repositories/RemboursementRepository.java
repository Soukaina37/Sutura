package in.sutura.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import in.sutura.entities.Remboursement;

public interface RemboursementRepository extends JpaRepository <Remboursement, Long> {

}
