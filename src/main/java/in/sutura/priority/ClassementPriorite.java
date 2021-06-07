package in.sutura.priority;

import in.sutura.entities.Pret;

public class ClassementPriorite {
	
	public static void classement_priorite(Pret p) {
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

}
