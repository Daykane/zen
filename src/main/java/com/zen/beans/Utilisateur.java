package com.zen.beans;

import java.sql.Timestamp;

public class Utilisateur {

    private Long      id;
    private String    email;
    private String    motDePasse;
    private String    nom;
    private Timestamp dateInscription;
    
    public Utilisateur(Long id, String email, String motDePasse, String nom) {
		super();
		this.id = id;
		this.email = email;
		this.motDePasse = motDePasse;
		this.nom = nom;
	}
    
	public Utilisateur() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
        return id;
    }
    public void setId( Long id ) {
        this.id = id;
    }

    public void setEmail( String email ) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public void setMotDePasse( String motDePasse ) {
        this.motDePasse = motDePasse;
    }
    public String getMotDePasse() {
        return motDePasse;
    }

    public void setNom( String nom ) {
        this.nom = nom;
    }
    public String getNom() {
        return nom;
    }

    public Timestamp getDateInscription() {
        return dateInscription;
    }
    public void setDateInscription( Timestamp dateInscription ) {
        this.dateInscription = dateInscription;
    }
}