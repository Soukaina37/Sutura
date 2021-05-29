package in.sutura.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name = "Justificatif")
public class Justificatif {
	@Id  @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String pieceIdentite;
	private String documentJustificatif;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPieceIdentite() {
		return pieceIdentite;
	}
	public void setPieceIdentite(String pieceIdentite) {
		this.pieceIdentite = pieceIdentite;
	}
	public String getDocumentJustificatif() {
		return documentJustificatif;
	}
	public void setDocumentJustificatif(String documentJustificatif) {
		this.documentJustificatif = documentJustificatif;
	}
	
	
	

}
