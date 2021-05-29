package sutura.in.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sutura.entities.Don;
import in.sutura.repositories.DonRepository;


@Service
public class DonServiceImpl implements DonService {

   private DonRepository donRepository;

   @Autowired
   public void setDonRepository(DonRepository donRepository) {
       this.donRepository = donRepository;
   }

   @Override
   public Iterable<Don> listAllDons() {
       return donRepository.findAll();
   }

   @Override
   public Optional<Don> getDonById(Long id) {
       return donRepository.findById(id);
   }

   @Override
   public Don saveDon(Don don) {
       return donRepository.save(don);
   }

   @Override
   public void deleteDon(Long id) {
       donRepository.deleteById(id);
   }


   @Override
      public long countDons() {
          return donRepository.count();
      }

}
