package sutura.in.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sutura.entities.Pret;
import in.sutura.repositories.PretRepository;

@Service
public class PretServiceImpl implements PretService {

    private PretRepository pretRepository;

    @Autowired
    public void setPretRepository(PretRepository pretRepository) {
        this.pretRepository = pretRepository;
    }

    @Override
    public Iterable<Pret> listAllPrets() {
        return pretRepository.findAll();
    }

    @Override
    public Optional<Pret> getPretById(Long id) {
        return pretRepository.findById(id);
    }

    @Override
    public Pret savePret(Pret pret) {
        return pretRepository.save(pret);
    }

    @Override
    public void deletePret(Long id) {
        pretRepository.deleteById(id);
    }
    @Override
    public long countPrets() {
        return pretRepository.count();
    }
}
