package com.interview.innso.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "message")
@EntityListeners(AuditingEntityListener.class)
public class Message {
    @Id
    private Integer id;
    
    @Column(name = "date_msg", nullable = false)
    private Date dateMsg;
    
    @Column(name = "user_name")
    private String username;
    
    @Column(name = "content", nullable = false)
    private String content;
    
    @Enumerated(EnumType.ORDINAL)
    private Canal canal;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(nullable = true)
    @JsonIgnore
    private Dossier dossier;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDateMsg() {
		return dateMsg;
	}
	public void setDateMsg(Date dateMsg) {
		this.dateMsg = dateMsg;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Canal getCanal() {
		return canal;
	}
	public void setCanal(Canal canal) {
		this.canal = canal;
	}
	
	public Dossier getDossier() {
		return dossier;
	}
	public void setDossier(Dossier dossier) {
		this.dossier = dossier;
	}
}
