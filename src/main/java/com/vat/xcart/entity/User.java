package com.vat.xcart.entity;

import com.vat.xcart.constant.Gender;
import com.vat.xcart.constant.Role;
import com.vat.xcart.constant.Status;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data  // Lombok annotation for @Getter, @Setter, @ToString, @EqualsAndHashCode
@Builder  // Lombok annotation for builder pattern
@NoArgsConstructor  // No-argument constructor
@AllArgsConstructor  // All-argument constructor
@EnableMongoAuditing
@Document(collection = "users")  // MongoDB collection
public class User {

    @Id
    private String id;

    @NotNull
    @Size(min = 3, max = 50)
    @Email
    private String username;

    // For hashed passwords
    @NotNull
    @Size(min = 6, max = 100)
    private String password;

    private String firstName;
    private String lastName;

    @NotNull
    private Gender gender;

    @NotNull
    private LocalDate dateOfBirth;

    @NotNull
    private Role role;

    private String securityQuestion;
    private String securityAnswer;

    @NotNull
    private Status status;

    @CreatedDate
    private LocalDateTime createDateTime;

    @LastModifiedDate
    private LocalDateTime updateDateTime;

    @NotNull
    private Long mobileNumber;
}

