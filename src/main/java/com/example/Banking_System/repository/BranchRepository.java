package com.example.Banking_System.repository;

import com.example.Banking_System.entity.BranchEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BranchRepository extends MongoRepository<BranchEntity, ObjectId> {
    BranchEntity findBranchByIFSC(String IFSC);
    BranchEntity findBranchByName(String branchName);
}
