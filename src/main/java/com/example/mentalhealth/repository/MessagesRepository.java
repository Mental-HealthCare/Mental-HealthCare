package com.example.mentalhealth.repository;

import com.example.mentalhealth.models.Messages;
import org.springframework.data.repository.CrudRepository;

public interface MessagesRepository extends CrudRepository<Messages,Integer> {
}
