package metier.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity 
@Table(name = "PLANTES")
public class Plante implements Serializable{
@Id
@Column (name="ID_Plante")
@GeneratedValue (strategy=GenerationType.IDENTITY)
private Long idPlante;
@Column (name="NOM_Plante")
private String nomPlante;
@Column (name="Couleur")
private String couleur;
@ManyToOne
private Type type;

public Plante(String nomPlante, String couleur,Type t) {

super();
this.nomPlante = nomPlante;
this.couleur = couleur;
this.setType(t);
}
public Type getType() {
return type;
}
public void setType(Type type) {
this.type = type;
}
public Plante() {
super();
}
public Plante(String nomPlante, String couleur) {
super();
this.nomPlante = nomPlante;
this.couleur = couleur;
}
public Long getIdPlante() {
return idPlante;
}
public void setIdPlante(Long idPlante) {
this.idPlante = idPlante;
}
public String getNomPlante() {
return nomPlante;
}
public void setNomPlante(String nomPlante) {
this.nomPlante = nomPlante;
}
public String getCouleur() {
return couleur;
}
public void setCouleur(String couleur) {
this.couleur = couleur;
}
@Override
public String toString() {
return "Plante [idPlante=" + idPlante + ", nomPlante=" +
		nomPlante + ", couleur=" + couleur + "]";
}
}