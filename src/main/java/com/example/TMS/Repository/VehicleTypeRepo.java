package com.example.TMS.Repository;

import com.example.TMS.EntityORModel.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleTypeRepo extends JpaRepository<VehicleType, Long> {
}
