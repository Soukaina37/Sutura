package in.sutura.priority;

import in.sutura.entities.Pret;

public class CalculPriorite {
	
	public static double calcul_priorite(Pret p) {
		//un peu de traitement concernant les COMMENTAIRES liés aux demandes
		//après, nous utiliserons l'intelligence artificielle pour évaluer 
		//la pertinence du commentaire et d'affecter automatiquement une note sur 10.
    	int precision = p.getCommentaire().length()/20;
		double priorite = precision;
		
		return priorite;
		
	}
	
		
}
