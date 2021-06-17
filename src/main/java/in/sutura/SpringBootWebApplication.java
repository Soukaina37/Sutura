package in.sutura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import in.sutura.entities.Parametre;
import in.sutura.repositories.ParametreRepository;

@SpringBootApplication
public class SpringBootWebApplication implements CommandLineRunner {
	
	@Autowired
	private ParametreRepository parametreRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebApplication.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		Parametre p = new Parametre();
		p.setId((long) 1);
		p.setPeriode(1);
		p.setValeurUrgenceSante(20);
		p.setValeurUrgenceAlimentation(15);
		p.setValeurUrgenceLoyer(10);
		p.setValeurUrgenceTransport(5);
		
		p.setValeurRemboursementProcheSup4000(10);
		p.setValeurRemboursementProcheEntre3000et4000(6);
		p.setValeurRemboursementProcheEntre1000et3000(3);
		p.setValeurRemboursementProcheInf1000(1);
		
		p.setValeurMontantPretInf500(20);
		p.setValeurMontantPretEntre500et1000(10);
		p.setValeurMontantPretSup1000(2);
		
		p.setNombreRemboursementSup2(4);
		p.setNombreRemboursementEq1(2);
		p.setNombreRemboursementElse(1);
		
		p.setAncienneteSup4(4);
		p.setAncienneteEntre2Et3(2);
		p.setAncienneteInf2(1);
		
		p.setGenreHomme(1);
		p.setGenreFemme(2);
		
		p.setNombreAnneeMarocInf2(2);
		p.setNombreAnneeElse(0);
		
		p.setDernierRemboursementAvantEcheance(4);
		
		p.setValeurEcheanceInf30(10);
		p.setValeurEcheanceEntre30Et60(2);
		p.setValeurEcheanceElse(0);
		
		p.setValeurDureeSup30(5);
		p.setValeurDureeEntre15Et30(2);
		
		parametreRepository.save(p);
		
		
	}
	
    

}