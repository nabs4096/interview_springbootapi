package com.interview.innso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.interview.innso.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>  {

}
