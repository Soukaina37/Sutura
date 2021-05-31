package in.sutura.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sutura.entities.Etudiant;
import in.sutura.repositories.EtudiantRepository;

@Service
public class EtudiantServiceImpl implements EtudiantService {

    private EtudiantRepository etudiantRepository;

    @Autowired
    public void setEtudiantRepository(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }

    @Override
    public Iterable<Etudiant> listAllEtudiants() {
        return etudiantRepository.findAll();
    }

    @Override
    public Optional<Etudiant> getEtudiantById(Long id) {
        return etudiantRepository.findById(id);
    }

    @Override
    public Etudiant saveEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    @Override
    public void deleteEtudiant(Long id) {
    	etudiantRepository.deleteById(id);
    }
    @Override
    public long countEtudiants() {
        return etudiantRepository.count();
    }


}
