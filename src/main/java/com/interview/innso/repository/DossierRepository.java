package com.interview.innso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.interview.innso.entity.Dossier;

@Repository
public interface DossierRepository extends JpaRepository<Dossier, Integer>  {

}
