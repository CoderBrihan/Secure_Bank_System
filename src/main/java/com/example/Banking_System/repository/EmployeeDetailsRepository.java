package com.example.Banking_System.repository;

import com.example.Banking_System.entity.EmployeeDetailsEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeDetailsRepository extends MongoRepository<EmployeeDetailsEntity, ObjectId> {
}
