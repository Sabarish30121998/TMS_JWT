package com.example.TMS.Controller;

import com.example.TMS.BaseResponse.BaseResponse;
import com.example.TMS.DTO.LoadedDTO;
import com.example.TMS.ServiceImplements.LoadedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loaded")
public class LoadedController {

    @Autowired
    LoadedService loadedService;

    @PostMapping("/create")
    public BaseResponse create (@RequestBody LoadedDTO loadedDTO)
    {
        return  loadedService.create(loadedDTO);
    }

    @GetMapping("/getbyid")
    public BaseResponse getbyid(@RequestParam long id)
    {
        return loadedService.getbyid(id);
    }

    @DeleteMapping("/deletebyid")
    public BaseResponse deletebyid(@RequestParam long id)
    {
        return loadedService.deletebyid(id);
    }

    @PutMapping("/putupdate")
    public BaseResponse putupdate(@RequestBody LoadedDTO loadedDTO,@RequestParam Long id)
    {
        return loadedService.putupdate(loadedDTO,id);
    }



    /*@PostMapping("/creationbyown")
    public BaseResponse create(@RequestBody VehicleDTO vehicleDTO)
    {
        return loadedService.create(vehicleDTO);
    }*/
}
