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
public class Loaded {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long loadedId;
    private String loadName;
    private String sourceFrom;
    private String toDestination;
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

    @ManyToOne(cascade = CascadeType.ALL)
    private Vehicle vehicle;

}
