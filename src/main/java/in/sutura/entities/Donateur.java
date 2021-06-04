package in.sutura.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity @Table(name = "Donateur")
public class Donateur extends Personne {

	private static final long serialVersionUID = 1L;
	
	//on a enlevé l'id parce que Donateur hérite l'id de Personne
	//car on a utilisé le mapping TABLE_PER_CLASS
	@Column(name="date")
	    private Date date;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
