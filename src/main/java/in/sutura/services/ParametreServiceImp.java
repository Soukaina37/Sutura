package in.sutura.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sutura.entities.Parametre;
import in.sutura.repositories.ParametreRepository; 



@Service
public class ParametreServiceImp implements ParametreService {
	
	private ParametreRepository parametreRepository;
    @Autowired
    public void setParametreRepository(ParametreRepository parametreRepository) {
        this.parametreRepository = parametreRepository;
    }

	@Override
	public Iterable<Parametre> listAllParametres() {
		return parametreRepository.findAll();
	}

	@Override
	public Optional<Parametre> getParametreById(Long id) {
		return parametreRepository.findById(id);
	}

	@Override
	public Parametre saveParametre(Parametre parametre) {
		return parametreRepository.save(parametre);
	}

	@Override
	public Parametre getParametresByPeriode(int periode) {
		return parametreRepository.findByPeriode(periode);
	}

}
