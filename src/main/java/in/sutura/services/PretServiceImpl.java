package in.sutura.services;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sutura.entities.Caisse;
import in.sutura.entities.Cotisation;
import in.sutura.entities.Etudiant;
import in.sutura.entities.Pret;
import in.sutura.entities.Remboursement;
import in.sutura.repositories.CotisationRepository;
import in.sutura.repositories.PretRepository;
import in.sutura.repositories.RemboursementRepository;

@Service
public class PretServiceImpl implements PretService {

    private PretRepository pretRepository;

    @Autowired
    public void setPretRepository(PretRepository pretRepository) {
        this.pretRepository = pretRepository;
    }
    
    private CotisationRepository cotisationRepository;
    
    @Autowired
    public void setCotisationRepository(CotisationRepository cotisationRepository) {
    	this.cotisationRepository = cotisationRepository;
    }
    
    private RemboursementRepository remboursementRepository;
    
    @Autowired
    public void setRemboursementRepository(RemboursementRepository remboursementRepository) {
    	this.remboursementRepository = remboursementRepository;
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
    
    //CALCUL DE LA PRIORITE

	@Override
	public double calcul_priorite(Pret p) {
		//un peu de traitement concernant les COMMENTAIRES liés aux demandes
		//après, nous utiliserons l'intelligence artificielle pour évaluer 
		//la pertinence du commentaire et d'affecter automatiquement une note sur 10.
    	int precision = p.getCommentaire().length()/20;
		double priorite = precision;
		priorite += valeur_urgence(p);
		
		
		//-----------------------------------
		Etudiant e1 = new Etudiant();
		e1 = p.getEtudiant();
		
		List<Cotisation> cotisations = new ArrayList<Cotisation>();
		
		//Récupérer toutes les cotisations concernant l'étudiant de la plus ancienne à la plus récente
		cotisations = cotisationRepository.findByEtudiant(e1);
		
		//Récupérer la dernière cotisation concernant un étudiant
		Cotisation c1 = cotisations.get(cotisations.size()-1);
		
		int a = valeur_urgence(p);
		//int b = valeur_remboursement_proche();
		int c = valeur_montant(p);
		int d = valeur_credibilite(p, c1, e1);
		//int e = valeur_echeance(p);
		int somme= a + c + d; //ajouter + b et + e
		priorite += somme; 
		//-----------------------------------------------
		return priorite;
	}
	
	//METHODES PRIVEES LIEES AU CALCUL DE LA PRIORITE
	
	//A
		private int valeur_urgence(Pret p) {
			String raison = p.getRaison();
			int valeur=0;
			if(raison=="sante") {
				valeur+=20;
			}
			if(raison=="alimentation") {
				valeur+=15;
			}
			if(raison=="loyer") {
				valeur+=10;
			}
			if(raison=="transport") {
				valeur+=5;
			}
			return valeur;
			
		}
		
		//B
		private int valeur_remboursement_proche() {
			int valeur=0;
			double montant = 0;
			
			/*
			//On récupère la liste de tous les prêts qui sont à l'état termine and dont l'état de remboursement est false
			List<Pret> prets = pretRepository.remboursement_proches();
			//Pour chaque prêt de la liste, on va vérifier si la date d'échéance est inférieure à 30 jours
			for(Pret p: prets) {
				Date echeance = p.getEcheance();
				int jours = diffJours(echeance);
				if(jours>30) {
					montant+=p.getMontant();
				}
			}
			*/
			
			//listes contient le montant total des prêts (non remboursés) à l'état terminé dont l'échéance de remboursement est au plutard dans 30 jours et dont l'état de paiement est "terminé"
			//faire un select dans pretRepository: état=termine; etatRemboursement=false et échéance<30jours
			if(montant>4000) {
				valeur=10;
			}
			if(montant>3000 && montant<=4000) {
				valeur=6;
			}
			if(montant>2000 && montant<=3000) {
				valeur=3;
			}
			if(montant>1000 && montant<=2000) {
				valeur=2;
			}
			if(montant<=1000) {
				valeur=1;
			}
			return valeur;
		}
		
		//C
		private int valeur_montant(Pret p) {
			int valeur = 0;
			double montant = p.getMontant();
			if(montant<=500) {
				valeur=10;
			}
			if(montant>500 && montant<=100) {
				valeur=10;
			}
			if(montant>100) {
				valeur=2;
			}
			
			return valeur;
		}
		
		//D
		private int valeur_credibilite(Pret p, Cotisation c, Etudiant e) {
			int valeur = 0;
			
			//Critères du nombre de remboursements
			List<Remboursement> list = new ArrayList<Remboursement>();
			//Récupérer la liste des remboursements concernant l'étudiant
			list = remboursementRepository.findByEtudiant(e);
			int n = list.size();
			if(n>2) {
				valeur = 4;
			}
			if(n==1) {
				valeur = 2;
			}
			else {
				valeur = 1;
			}
			
			//Critère de l'ancienneté <=> au nombre total de cotisations
			List<Cotisation> cotisations = new ArrayList<Cotisation>();
			//Récupérer toutes les cotisations concernant l'étudiant
			cotisations = cotisationRepository.findByEtudiant(e);
			int anciennete = cotisations.size();
			if(anciennete>=4) {
				valeur+=4;
			}
			if(anciennete==2 || anciennete==3) {
				valeur+=2;
			}
			if(anciennete<2) {
				valeur+=1;
			}
			
			//Critère lié au genre
			String genre = e.getSexe();
			if(genre=="femme") {
				valeur+=2;
			}
			if(genre=="homme") {
				valeur+=1;
			}
			
			//Nombre d'années passées au Maroc
			//Au début de chaque année, on incrémente ce nombre pour chaque étudiant
			//et pour chaque administration
			//Créer un bouton pour cela -A FAIRE APRES-
			
			int nombreAnnee = e.getNbAnneeMaroc();
			if(nombreAnnee<=2) {
				valeur+=2;
			}
			else {
				valeur+=0;
			}
			
			//Fin d'année ou non
			/*
			 * if(avantAvril(p.getDateDemande())) { valeur+=1; }
			 */
			
			/*
			//Si l'éventuel dernier remboursement concernant l'étudiant est effectué avant échéance
			//Pour cela, on peut considérer directement les prets et non les remboursements
			List<Pret> prets = new ArrayList<Pret>();
			//Récupérer la liste des prets (dont etatRemboursement=true) concernant l'étudiant du plus ancien au plus récent
			prets = pretRepository.findByEtudiantOrdonne(e);
			Pret pret = prets.get(prets.size()-1);
			boolean isIt = isDernierRemboursementAvantEcheanceByEtudiant(pret);
			if(isIt) {
				valeur+=4;
			}
			*/
			
			return valeur;
			
		}

		//E
		/*
		private int valeur_echeance(Pret p) {
			int valeur=0;
			Date d = p.getEcheance();
			int diff = diffJours(d);
			if(diff<30) {
				valeur = 10;
			}
			if(diff>30 && diff<=60) {
				valeur=2;
			}
			else {
				valeur=0;
			}
			
			return valeur;
			
		}
		*/
		
		
		//IMPLEMENTATION DES SOUS-METHODES
		private int diffJours(Date date) {
			int days=0;
			String format = "dd MM yyyy";
			SimpleDateFormat parser = new java.text.SimpleDateFormat( format );
			parser.format(date);
			Date today = (Date) new java.util.Date();
			parser.format(today);
			long diff = today.getTime() - date.getTime();
			days = (int) (diff / (1000*60*60*24));
			return days;
		}
		
		/*
		
		private boolean avantAvril(Date date) {
			boolean is_avantAvril = false;
			String format = "dd MM";
			SimpleDateFormat parser = new java.text.SimpleDateFormat( format );
			parser.format(date);
													//DEMANDER AU PROF COMMENT INITIALISER UNE DATE A UN JOUR DONNE
			Date avril = (Date) new java.util.Date();
			String formatAvril = "01 04";
			SimpleDateFormat parserAvril = new java.text.SimpleDateFormat( formatAvril );
			parserAvril.format(avril);
			
			long diff = avril.getTime() - date.getTime();
			if(diff>0) {
				is_avantAvril=true;
			}
			return is_avantAvril;
		}
		*/
		
		private boolean isDernierRemboursementAvantEcheanceByEtudiant(Pret p) {
			
			boolean isIt=false;
			Date echeance = p.getEcheance();
			Date modification = p.getDateModification();
			
			if(modification.before(echeance)) {
				isIt=true;
			}
			
			return isIt;
		}
		
		
		//APRES CALCUL ET RECALCUL DE LA PRIORITE, ON FAIT LE CLASSEMENT

		@Override
		public void classement_priorite(Pret p) {
			double priorite = p.getPriorite();
			if (priorite>80) {
				p.setEtat("elu");
			}
			if (priorite>60 && priorite<80) {
				p.setEtat("pret");
			}
			if (priorite>40 && priorite<=60) {
				p.setEtat("permute-pret");
			}
			if (priorite>20 && priorite<=40) {
				p.setEtat("bloque");
			}
			if (priorite<=20) {
				p.setEtat("permute-bloque");
			}
		}

		@Override
		public void update(Pret pret) {
			Long id = pret.getId();
			pretRepository.update(id,pret);
			
		}
		
		/*
		@Override
		public List<Pret> getList(Caisse caisse) {
			return pretRepository.findAllForRecalcul();
		}
		*/
		
		//RECALCUL DE LA PRIORITE
/*
		@Override
		public double recalcul_priorite(Pret p) {
			//On modifie la date de modification
			p.setDateModification(new Date());
			double nouvellePriorite = p.getPriorite();
			nouvellePriorite += valeur_duree(p);
			return nouvellePriorite += valeur_remboursement_proche();
		}
		
		private int valeur_duree(Pret p) {
			int valeur=0;
			Date d = p.getDateModification();
			int diff= diffJours(d);//qui retourne le nombre de jours entre la date d et aujourd'hui --->Calcule la différence entre deux dates
			if(diff>30) {
				valeur+=5;
				Date today = new Date(diff);
				p.setDateModification(today);
			}
			if(diff<15 && diff<=30) {
				valeur+=2;
				Date today = new Date(diff);
				p.setDateModification(today);
			}
			return valeur;
			
		}
	
	*/
}
