package in.sutura.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import in.sutura.entities.Administrateur;

public interface AdministrateurRepository extends JpaRepository<Administrateur, Long> {

}
