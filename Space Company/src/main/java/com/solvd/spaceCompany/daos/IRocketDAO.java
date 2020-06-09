package com.solvd.spaceCompany.daos;

import com.solvd.spaceCompany.models.Rocket;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IRocketDAO extends IDAO<Rocket>{
    List<Rocket> getAllBySpaceCompanyId(@Param("id") Long spaceCompanyId);
}
