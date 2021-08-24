package com.example.mentalhealth.repository;

import com.example.mentalhealth.models.ChatMessage;
import org.springframework.data.repository.CrudRepository;

public interface WebSocketRepo extends CrudRepository<ChatMessage, Integer> {

}
