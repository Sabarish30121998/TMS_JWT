package com.example.TMS.Controller;

import com.example.TMS.BaseResponse.BaseResponse;
import com.example.TMS.DTO.VehicleTypeDTO;
import com.example.TMS.ServiceImplements.VehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/vehicletype")
public class VehicleTypeController {

    @Autowired
    VehicleTypeService vehicleTypeService;

    @PostMapping("/create")
    public BaseResponse create1(@RequestBody VehicleTypeDTO vehicleTypeDTO)
    {
        return vehicleTypeService.create(vehicleTypeDTO);
    }

    @GetMapping("/getbyid")
    public BaseResponse getbyid1(@RequestParam long id)
    {
        return vehicleTypeService.getbyid(id);
    }

    @DeleteMapping("/deletebyid")
    public BaseResponse deletebyid1(@RequestParam long id)
    {
        return vehicleTypeService.deletebyid(id);
    }

    @PutMapping("/putupdate")
    public BaseResponse putupdate1(@RequestBody VehicleTypeDTO vehicleTypeDTO,@RequestParam Long id)
    {
        return vehicleTypeService.putupdate(vehicleTypeDTO,id);
    }
}
