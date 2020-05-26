package com.solvd.spaceCompany.daos;

import com.solvd.spaceCompany.models.Astronaut;

import java.util.List;

public interface IAstronautDAO extends IDAO<Astronaut> {
    List<Astronaut> getAllByStationId(Long stationId);
}
