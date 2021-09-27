package com.example.TMS.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Data
@Setter
@Getter
public class UserDTO {
    private Long ownerId;
    private String  userName;
    private String userType;
    private String password;
    private String city;
    private Long phoneNumber;
    private int isActive;
    private  int isDeleted;

}
