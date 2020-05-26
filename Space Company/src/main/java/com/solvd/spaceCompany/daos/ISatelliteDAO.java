package com.solvd.spaceCompany.daos;

import com.solvd.spaceCompany.models.Satellite;

import java.util.List;

public interface ISatelliteDAO extends IDAO<Satellite> {
    List<Satellite> getAllByStationId(Long stationId);
}
