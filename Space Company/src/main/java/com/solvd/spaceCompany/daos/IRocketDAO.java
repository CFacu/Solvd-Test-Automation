package com.solvd.spaceCompany.daos;

import com.solvd.spaceCompany.models.Rocket;

import java.util.List;

public interface IRocketDAO extends IDAO<Rocket>{
    List<Rocket> getAllBySpaceCompanyId(Long spaceCompanyId);
}
