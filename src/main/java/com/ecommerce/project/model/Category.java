package com.ecommerce.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "categories")     // an entity in JPA means, a table in DB. This particular class will be mapped to a table in DB by spring, and the table will contain attributes of the class members. The name of the entity is "categories"
@Data   // makes every field as final, generates setters and getters, no-args constructor
@NoArgsConstructor      // generates a default constructor, assigns default values to the final fields
@AllArgsConstructor     // generates a parameterized constructor will all fields as parameters
public class Category {

    // means this attribute will be the primary key of the table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //specifies how primary key values should be generated. Has values auto, identity, sequence, table, none

//    a "sequence" is an object of db that resides in the db, which generates sequence of unique values. allocationSize = 1 means sequence increment it by 1
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = 'order_seq')
//    @SequenceGenerator(name = 'order_seq', sequenceName = 'order_sequence', allocationSize = 1)

//    if db doesnt support sequence, table can be used
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = 'table_gen')
//    @TableGenerator(name = 'table_gen', table = 'id_gen', pkColumnName = 'gen_key', valueColumnName = 'gen_value', pkColumnValue = 'task_id', allocationSize = 1)
    private Long categoryId;

    @NotBlank(message = "Category name can NOT be blank!")       // ensures that a string is not null, not empty, and does not contain only whitespace. If 'message' attribute is not specified, that the default message is shown.
    @Size(min = 5, message = "Category name MUST contain at least 5 characters")        // ensures that a string, collection or an array has a specified range
    private String categoryName;

//    public Long getCategoryId() {
//        return categoryId;
//    }
//
//    public void setCategoryId(Long categoryId) {
//        this.categoryId = categoryId;
//    }

//    for every data member, getter and setters must be present then only we can see the values reflected in the DB.
//    If getter and setter are not provided, null is stored in the values of the attributes.
//    public String getCategoryName() {
//        return categoryName;
//    }
//
//    public void setCategoryName(String categoryName) {
//        this.categoryName = categoryName;
//    }

//    public Category(Long categoryId, String categoryName) {
//        this.categoryId = categoryId;
//        this.categoryName = categoryName;
//    }

//    good practice to have a default constructor in JPA
//    public Category() {
//
//    }
}
