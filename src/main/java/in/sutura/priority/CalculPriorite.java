package in.sutura.priority;

import in.sutura.entities.Pret;

public class CalculPriorite {
	
	public static Pret Calcul(Pret p, int precision) {
		int priorite = precision;
		
		p.setPriorite(priorite);
		
		
		return p;
		
	}
	
	//IMPLEMENTER LES METHODES PRIVEES UTILISEES PAR Calcul(Pret p, int precision)

}
