package com.solvd.spaceCompany.daos;

import com.solvd.spaceCompany.models.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IAddressDAO extends IDAO<Address>{
    List<Address> getAddressesByStationId(@Param("id") Long id);
}
