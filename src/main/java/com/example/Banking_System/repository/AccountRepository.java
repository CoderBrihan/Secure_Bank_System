package com.example.Banking_System.repository;

import com.example.Banking_System.entity.AccountEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface AccountRepository extends MongoRepository<AccountEntity, ObjectId> {
            AccountEntity findByAccountNo(String accountNo);
}
