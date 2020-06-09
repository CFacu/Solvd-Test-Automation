package com.solvd.spaceCompany.daos;

import com.solvd.spaceCompany.models.Engineer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IEngineerDAO extends IDAO<Engineer> {
    List<Engineer> getAllBySpaceCompanyId(@Param("id")Long spaceCompanyId);
}
