package com.solvd.spaceCompany.daos;

import com.solvd.spaceCompany.models.Astronaut;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IAstronautDAO extends IDAO<Astronaut> {
    List<Astronaut> getAllByStationId(@Param("id")Long stationId);
}
