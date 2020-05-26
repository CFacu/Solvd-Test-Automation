package com.solvd.spaceCompany.daos;

import com.solvd.spaceCompany.models.Engineer;

import java.util.List;

public interface IEngineerDAO extends IDAO<Engineer> {
    List<Engineer> getAllBySpaceCompanyId(Long spaceCompanyId);
}
