package com.example.TMS.EntityORModel;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
//import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@Setter
@Getter
public class Vehicle {
    @Id
    //@NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;
    private String vehicleName;
    private String registrationNumber;
    private int isActive;
    private  int isDeleted;

    /*@Value("${some.key:0}")
    private int isActive;
    @Value("${some.key:0}")
    private  int isDeleted;*/



    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToOne
    private  VehicleType vehicleTypeid;

    @ManyToOne
    private  User user;
}
