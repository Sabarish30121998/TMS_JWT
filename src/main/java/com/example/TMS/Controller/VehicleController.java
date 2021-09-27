package com.example.TMS.Controller;

import com.example.TMS.BaseResponse.BaseResponse;
import com.example.TMS.BaseResponse.PaginationResponse;
import com.example.TMS.DTO.VehicleDTO;
import com.example.TMS.ServiceImplements.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @PostMapping("/create")
    public BaseResponse create1(@RequestBody VehicleDTO vehicleDTO)
    {
        return vehicleService.create(vehicleDTO);
    }

    @DeleteMapping("/deletebyid")
    public BaseResponse deletebyid1(@RequestParam long id)
    {
        return vehicleService.deletebyid(id);
    }

    @GetMapping("/getbyid")
    public BaseResponse getbyid1(@RequestParam long id)
    {
        return vehicleService.getbyid(id);
    }

    @PutMapping("/putupdate")
    public BaseResponse putupdate1(@RequestParam long id,@RequestBody VehicleDTO vehicleDTO)
    {
        return vehicleService.putupdate(id,vehicleDTO);
    }

    @GetMapping("/pagination/{currpagenumber}/{totalnumberofrecordsinpage}/{registrationnumber}")
    public PaginationResponse pagination1(@PathVariable int currpagenumber,@PathVariable int totalnumberofrecordsinpage,@PathVariable String registrationnumber )
    {
        return vehicleService.pagination(currpagenumber,totalnumberofrecordsinpage,registrationnumber);
    }
}
