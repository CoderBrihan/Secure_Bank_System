package com.example.Banking_System.repository;

import com.example.Banking_System.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity, ObjectId> {
                    UserEntity findByAccountNo(String accountNo);
}
