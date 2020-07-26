package com.interview.innso.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.interview.innso.entity.Canal;
import com.interview.innso.entity.Dossier;
import com.interview.innso.entity.Message;
import com.interview.innso.repository.DossierRepository;
import com.interview.innso.repository.MessageRepository;

@RestController
@RequestMapping("/api/v1")
public class ServiceController {

  @Autowired
  private DossierRepository dossierRepository;
  
  @Autowired
  private MessageRepository messageRepository;

  /**
   * Get all Dossiers list.
   *
   * @return the list
   */
  @GetMapping("/Dossiers")
  public List<Dossier> getAllDossiers() {
    return dossierRepository.findAll();
  }

  /**
   * Create message.
   *
   * @param msg the message
   * @return the message
   */
  @PostMapping("/messages")
  public Message createMessage(@RequestParam(value = "canal", required = true) Canal canal,
		  @RequestParam(value = "id_dossier", required = false) Integer dossierId,
		  @RequestBody Message msg) {
	  Dossier dossier;
	  if(dossierId!=null) {		  
		  dossier = dossierRepository.findById(dossierId)
				  .orElseThrow(() -> new InvalidConfigurationPropertyValueException("dossierId", dossierId, "Dossier not with id " + dossierId));
		  msg.setDossier(dossier);
	  }
	  msg.setCanal(canal);
	  return messageRepository.save(msg);
  }
  
  /**
   * Create Dossier.
   *
   * @param msg the Dossier
   * @return the Dossier
   */
  @PostMapping("/Dossiers")
  public Dossier createDossier(@RequestParam(value = "id_msg", required = false) Integer idMsg, 
		  @RequestBody Dossier dossier) {
	  Message msg;
	  if(idMsg!=null) {		  
		  msg = messageRepository.findById(idMsg)
				  .orElseThrow(() -> new InvalidConfigurationPropertyValueException("idMsg", idMsg, "Dossier not with id " + idMsg));
		  msg.setDossier(dossier);
		  if(dossier.getMessages()!=null) {
			  dossier.getMessages().add(msg);
		  } else {
			  List<Message> messages = new ArrayList<Message>();
			  messages.add(msg);
			  dossier.setMessages(messages);
		  }
	  }
    return dossierRepository.save(dossier);
  }

  /**
   * Update user response entity.
   *
   * @param dossierId the Dossier id
   * @param dossierDetails the Dossier details
   * @return the response entity
   * @throws InvalidConfigurationPropertyValueException the resource not found exception
   */
  @PutMapping("/Dossiers/{id}")
  public ResponseEntity<Dossier> updateDossier(@RequestBody Dossier dossierDetails)
      throws InvalidConfigurationPropertyValueException {
    final Dossier updatedUser = dossierRepository.save(dossierDetails);
    return ResponseEntity.ok(updatedUser);
  }
}
