package in.sutura.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import in.sutura.entities.Personne;

public interface PersonneRepository extends JpaRepository<Personne, Long>{

}
