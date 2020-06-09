package com.solvd.spaceCompany.daos;

import com.solvd.spaceCompany.models.Satellite;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ISatelliteDAO extends IDAO<Satellite> {
    List<Satellite> getAllByStationId(@Param("id")Long stationId);
}
