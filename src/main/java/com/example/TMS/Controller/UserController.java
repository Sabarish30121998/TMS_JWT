package com.example.TMS.Controller;

import com.example.TMS.BaseResponse.BaseResponse;
import com.example.TMS.BaseResponse.PaginationResponse;
import com.example.TMS.DTO.TokenDTO;
import com.example.TMS.DTO.UserDTO;
import com.example.TMS.ServiceImplements.UserService;
import com.example.TMS.Utils.TokenGeneration;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    TokenGeneration tokenGeneration;

   @PostMapping("/create")
    public BaseResponse create1(@RequestBody UserDTO userDTO)
   {
       return userService.create(userDTO);
   }

   @GetMapping("/getbyid")
   @Authorization(value = "Bearer")
    public BaseResponse getbyid1(@RequestParam Long id){
       return userService.getbyid(id);
   }

   @DeleteMapping("/deletebyid")
   @Authorization(value = "Bearer")
    public BaseResponse deletebyid1(@RequestParam Long id)
   {
       return userService.deletebyid(id);
   }

   @PutMapping("/putupdate")
   @Authorization(value = "Bearer")
    public BaseResponse update1(@RequestBody UserDTO userDTO,@RequestParam Long id)
   {
       return userService.putupdate(userDTO,id);
   }

   @GetMapping("/pagination/{currpagenumber}/{totalnumberofrecordsinpage}/{username}")
   @Authorization(value = "Bearer")
    public PaginationResponse pagination1(@PathVariable int currpagenumber, @PathVariable int totalnumberofrecordsinpage, @PathVariable String username)
   {
       return  userService.pagination(currpagenumber,totalnumberofrecordsinpage,username);
   }

   @GetMapping("/login")
    public BaseResponse token1(@RequestBody TokenDTO tokenDTO)
   {
       return userService.login(tokenDTO);
   }


 /* @GetMapping("/signin")
    public BaseResponse signintoken(@RequestBody TokenDTO tokenDTO)
   {
       return  userService.signin(tokenDTO);
   }*/

    /*@GetMapping("/summa")
    public BaseResponse sampleAPItoVerify(@RequestHeader (value = "authorization",defaultValue = "") String auth) throws Exception {

        tokenGeneration.verify(auth);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData("Executed");
        return baseResponse;
    }*/

}
