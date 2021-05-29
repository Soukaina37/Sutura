package sutura.in.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sutura.entities.Remboursement;
import in.sutura.repositories.RemboursementRepository;

@Service
public class RemboursementServiceImpl implements RemboursementService {

    private RemboursementRepository remboursementRepository;

    @Autowired
    public void setRemboursementRepository(RemboursementRepository remboursementRepository) {
        this.remboursementRepository = remboursementRepository;
    }

    @Override
    public Iterable<Remboursement> listAllRemboursements() {
        return remboursementRepository.findAll();
    }

    @Override
    public Optional<Remboursement> getRemboursementById(Long id) {
        return remboursementRepository.findById(id);
    }

    @Override
    public Remboursement saveRemboursement(Remboursement remboursement) {
        return remboursementRepository.save(remboursement);
    }

    @Override
    public void deleteRemboursement(Long id) {
        remboursementRepository.deleteById(id);
    }
    @Override
    public long countRemboursements() {
        return remboursementRepository.count();
    }

}
