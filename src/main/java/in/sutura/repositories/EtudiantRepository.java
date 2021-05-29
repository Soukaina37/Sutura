package in.sutura.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import in.sutura.entities.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

}
