package com.example.Banking_System.repository;

import com.example.Banking_System.entity.BankEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BankRepository extends MongoRepository<BankEntity, ObjectId> {
}
