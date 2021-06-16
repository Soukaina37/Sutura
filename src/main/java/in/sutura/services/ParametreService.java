package in.sutura.services;

import java.util.Optional;

import in.sutura.entities.Parametre;

public interface ParametreService {

	Iterable<Parametre> listAllParametres();

	Optional<Parametre> getParametreById(Long id);
	
	Parametre saveParametre(Parametre parametre);

	Parametre getParametresByPeriode(int periode);

}
