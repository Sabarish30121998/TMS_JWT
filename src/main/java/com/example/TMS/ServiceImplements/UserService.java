package com.example.TMS.ServiceImplements;

import com.example.TMS.BaseResponse.BaseResponse;
import com.example.TMS.BaseResponse.PaginationResponse;
import com.example.TMS.DTO.TokenDTO;
import com.example.TMS.DTO.UserDTO;
import com.example.TMS.EntityORModel.User;
import com.example.TMS.Repository.UserRepo;
import com.example.TMS.Service.UserServiceInterface;
import com.example.TMS.Utils.TokenGeneration;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service

public class UserService implements UserServiceInterface {

    @Autowired
    UserRepo userRepo;

    @Autowired
    TokenGeneration tokenGeneration;



    @Override
    public BaseResponse create(UserDTO userDTO) {
        BaseResponse baseResponse=new BaseResponse();
        User user = new User();

        user.setUserName(userDTO.getUserName());
        user.setUserType(userDTO.getUserType());
        user.setPassword(userDTO.getPassword());
        user.setCity(userDTO.getCity());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setIsDeleted(0);
        user.setIsActive(0);

        userRepo.save(user);

        baseResponse.setStatuscode("200");
        baseResponse.setStatusmessage("successfully value created");
        baseResponse.setData(user);
        return baseResponse;
    }

    @Override
    public BaseResponse getbyid(Long id) {

        BaseResponse baseResponse = new BaseResponse();
        Optional<User> sabari = userRepo.findById(id);
        if (sabari.isPresent()) {
            if (sabari.get().getIsDeleted() == 0) {
                baseResponse.setStatuscode("success");
                baseResponse.setStatusmessage("Data found");
                baseResponse.setData(sabari);
            } else {
                throw  new RuntimeException("This Data is deleted");
            }
        }
        else
        {
            throw  new RuntimeException("Please enter a valid User ID");
        }
        return baseResponse;
    }

    @Override
    public BaseResponse deletebyid(Long id) {
        BaseResponse baseResponse = new BaseResponse();
        Optional<User> sabari = userRepo.findById(id);
        if(sabari.isPresent()) {
            sabari.get().setIsDeleted(1);
            userRepo.save(sabari.get());
            baseResponse.setStatuscode("200");
            baseResponse.setStatusmessage("successfully deleted");
        }
        else
        {
            throw  new RuntimeException("Please enter the valid user id to delete");
        }
        return baseResponse;
    }

    @Override
    public BaseResponse putupdate(UserDTO userDTO,Long id) {
        BaseResponse baseResponse = new BaseResponse();
        Optional<User> sabari = userRepo.findById(id);

        if(sabari.isPresent()) {
            sabari.get().setUserName(sabari.get().getUserName());
            sabari.get().setUserType(userDTO.getUserType());
            sabari.get().setPassword(userDTO.getPassword());
            sabari.get().setCity(userDTO.getCity());
            sabari.get().setPhoneNumber(userDTO.getPhoneNumber());
            sabari.get().setIsActive(userDTO.getIsActive());
            sabari.get().setIsDeleted(userDTO.getIsDeleted());

            userRepo.save(sabari.get());
            baseResponse.setStatuscode("200");
            baseResponse.setStatusmessage("successfully value created");
            baseResponse.setData(sabari);
        }
        else{
            throw  new RuntimeException("Enter A Valid UserId");
        }

        return baseResponse;
    }


    @Override
    public PaginationResponse pagination(int currpagenumber, int totalnumberofrecordsinpage, String username) {
    PaginationResponse paginationResponse=new PaginationResponse();
        Pageable paging = PageRequest.of(currpagenumber,totalnumberofrecordsinpage);
        Page<User> users= userRepo.searchAllByUserNameLike("%" + username +"%",paging);
        paginationResponse.setResponse(users);
        paginationResponse.setPagecount(users.getTotalPages());

    return paginationResponse;
    }

    @Override
    public BaseResponse login(TokenDTO tokenDTO) {
        BaseResponse baseResponse = new BaseResponse();
        Optional<User> sabari = userRepo.findByUserNameAndPassword(tokenDTO.getUserName(),tokenDTO.getPassword());
        try
        {
          if(sabari.isPresent())
          {
              String tokens = TokenGeneration.generateToken("Admin",sabari.get().getUserName(),sabari.get().getPassword());
              tokenDTO.setToken(tokens);

              Map<String,Object> accessspecifier= new HashMap<>();
              accessspecifier.put("accessspecifier",tokens);

              baseResponse.setStatuscode("success");
              baseResponse.setStatusmessage("Token Generated");
              baseResponse.setData(accessspecifier);
          }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return baseResponse;
    }

   /* @Override
    public BaseResponse signin(TokenDTO tokenDTO) {
        BaseResponse baseResponse=new BaseResponse();

        Optional<User> user = userRepo.findByUserNameAndPassword(tokenDTO.getUserName(),tokenDTO.getPassword());
        if(user.isPresent())
        {
            String generatedtokens = tokenGeneration.tokencreation(user.get());

            // This Line is for our Understandings...But Not Necessary
            *//* //  tokenDTO.setToken(generatedtokens);  *//*

            Map<String,Object> access= new HashMap<>();
            access.put("accesstoken",generatedtokens);

            baseResponse.setStatuscode("success");
            baseResponse.setStatusmessage("Token Generated");
            baseResponse.setData(access);

        }
        else
        {
            throw  new RuntimeException("Please Enter the Valid UserName and Password");
        }
        return baseResponse;
    }
*/

    public UserDetails loadByUserId(String ownerId) {
        Optional<User> user = userRepo.findByUserName(ownerId);
        Optional<User> opt = Optional.ofNullable(user).orElseThrow(
                () -> new UsernameNotFoundException("userId Not Found"))
              .map(UserDetail::new);
        if(opt.isPresent()){
            return (UserDetails) opt.get();
        }
        return null;
    }
}
