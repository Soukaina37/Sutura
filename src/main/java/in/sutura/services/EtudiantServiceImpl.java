package in.sutura.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sutura.entities.Etudiant;
import in.sutura.entities.Personne;
import in.sutura.repositories.EtudiantRepository;
import in.sutura.repositories.PersonneRepository;

@Service
public class EtudiantServiceImpl implements EtudiantService {

    private EtudiantRepository etudiantRepository;
    private PersonneRepository personneRepository;

    @Autowired
    public void setEtudiantRepository(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }

    @Override
    public Iterable<Etudiant> listAllEtudiants() {
        return etudiantRepository.findAll();
    }

    @Override
    public Optional<Personne> getEtudiantById(Long id) {
        return personneRepository.findById(id);
    }

    @Override
    public Etudiant saveEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    @Override
    public void deleteEtudiant(Long id) {
    	personneRepository.deleteById(id);
    }
    @Override
    public long countEtudiants() {
        return etudiantRepository.count();
    }


}
