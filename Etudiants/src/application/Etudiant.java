package application;

public class Etudiant
{
	private String prenom;
	private String nom;
	private String departement;
	private Double age;
	
	//constructeur vide
	public Etudiant() {
		this(null,null);
	}
	
	//constructeur avec 2 parametres
	public Etudiant(String prenom, String nom) {
		this.prenom = prenom;
		this.nom = nom;
		this.departement = "";
		this.age = 0.0;
	}
	
	//setters & getters
	public String getPrenom()
	{
		return prenom;
	}
	public void setPrenom(String prenom)
	{
		this.prenom = prenom;
	}
	public String getNom()
	{
		return nom;
	}
	public void setNom(String nom)
	{
		this.nom = nom;
	}
	public String getDepartement()
	{
		return departement;
	}
	public void setDepartement(String departement)
	{
		this.departement = departement;
	}
	public Double getAge()
	{
		return age;
	}
	public void setAge(Double age)
	{
		this.age = age;
	}
	
}
