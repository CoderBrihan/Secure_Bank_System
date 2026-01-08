package com.example.Banking_System.repository;

import com.example.Banking_System.entity.TransactionEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<TransactionEntity, ObjectId> {
    TransactionEntity findByTransactionId(String transactionId);
}
