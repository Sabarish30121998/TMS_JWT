package com.example.TMS.EntityORModel;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ownerId;
    private String  userName;
    private String userType;
    private String password;
    private String city;
    private Long phoneNumber;

  /*  @Value("${property_name:1}")
    private int isActive;
    @Value("${property_name:100}")
    private  int isDeleted;*/


    private int isActive;
    private  int isDeleted;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;


    public User(User user) {
        this.userName= userName;
        this.password= password;
    }
}
