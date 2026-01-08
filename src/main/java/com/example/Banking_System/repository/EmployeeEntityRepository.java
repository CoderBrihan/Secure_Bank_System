package com.example.Banking_System.repository;

import com.example.Banking_System.entity.EmployeeEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeEntityRepository extends MongoRepository<EmployeeEntity, ObjectId> {
    EmployeeEntity findByUsername(String userName);
}
