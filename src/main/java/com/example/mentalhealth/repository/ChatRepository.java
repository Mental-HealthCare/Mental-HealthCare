package com.example.mentalhealth.repository;

import com.example.mentalhealth.models.Chat;
import org.springframework.data.repository.CrudRepository;

public interface ChatRepository extends CrudRepository<Chat, Integer> {
}
