package com.interview.innso.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "dossier")
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
public class Dossier {
    @Id
    private Integer id;
    
    @Column(name = "nom_client", nullable = false, updatable = false)
    private String nomClient;
    
    @Column(name = "date_ouverture", nullable = false, updatable = false)
    private Date dateOuverture;
    
    @Column(name = "reference", nullable = true)
    private String reference;
    
    @OneToMany(mappedBy = "dossier", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Message> messages;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNomClient() {
		return nomClient;
	}
	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}
	public Date getDateOuverture() {
		return dateOuverture;
	}
	public void setDateOuverture(Date dateOuverture) {
		this.dateOuverture = dateOuverture;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
}
