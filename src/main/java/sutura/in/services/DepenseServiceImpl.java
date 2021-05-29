package sutura.in.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sutura.entities.Depense;
import in.sutura.repositories.DepenseRepository;

@Service
public class DepenseServiceImpl implements DepenseService {

    private DepenseRepository depenseRepository;

    @Autowired
    public void setDepenseRepository(DepenseRepository depenseRepository) {
        this.depenseRepository = depenseRepository;
    }

    @Override
    public Iterable<Depense> listAllDepenses() {
        return depenseRepository.findAll();
    }

    @Override
    public Optional<Depense> getDepenseById(Long id) {
        return depenseRepository.findById(id);
    }

    @Override
    public Depense saveDepense(Depense depense) {
        return depenseRepository.save(depense);
    }

    @Override
    public void deleteDepense(Long id) {
        depenseRepository.deleteById(id);
    }

    @Override
    public long countDepenses() {
        return depenseRepository.count();
    }
}
