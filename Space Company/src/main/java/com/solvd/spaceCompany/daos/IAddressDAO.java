package com.solvd.spaceCompany.daos;

import com.solvd.spaceCompany.models.Address;

import java.util.List;

public interface IAddressDAO extends IDAO<Address>{
    Address getAddressByStationId(Long id);
}
