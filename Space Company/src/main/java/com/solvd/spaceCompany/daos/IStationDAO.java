package com.solvd.spaceCompany.daos;

import com.solvd.spaceCompany.models.Station;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IStationDAO extends IDAO<Station> {
    List<Station> getAllBySpaceCompanyId(@Param("id") Long spaceCompanyId);
}
