package com.example.Banking_System.entity;

import com.mongodb.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Unwrapped;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document(collection = "Branch_Details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BranchEntity {

    @Id
    private ObjectId id;
    @Indexed(unique = true)
    private String IFSC;
    private String name;
    private String managerName;
    private List<String> accountNumbers =  new ArrayList<>();
    @DBRef
    private  List<EmployeeEntity> employeeList = new ArrayList<>();
}
