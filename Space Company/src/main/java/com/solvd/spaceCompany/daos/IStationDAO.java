package com.solvd.spaceCompany.daos;

import com.solvd.spaceCompany.models.Station;

import java.util.List;

public interface IStationDAO extends IDAO<Station> {
    List<Station> getAllBySpaceCompanyId(Long spaceCompanyId);
}
