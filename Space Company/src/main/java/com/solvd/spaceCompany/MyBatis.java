package com.solvd.spaceCompany;

import com.solvd.spaceCompany.daos.IAddressDAO;
import com.solvd.spaceCompany.daos.ICEODAO;
import com.solvd.spaceCompany.daos.IStationDAO;
import com.solvd.spaceCompany.models.Address;
import com.solvd.spaceCompany.models.CEO;
import com.solvd.spaceCompany.models.SpaceCompany;
import com.solvd.spaceCompany.models.Station;
import com.solvd.spaceCompany.services.SpaceCompanyService;
import com.solvd.spaceCompany.services.StationService;
import com.solvd.spaceCompany.utils.ConnectionFactory;

import java.io.IOException;

public class MyBatis {
    public static void main(String[] args) throws IOException {
        SpaceCompanyService spaceCompanyService = new SpaceCompanyService();
        StationService stationService = new StationService();

        IAddressDAO addressMapper = ConnectionFactory.getAddressMapper();
        IStationDAO stationMapper = ConnectionFactory.getStationMapper();
        ICEODAO ceoMapper = ConnectionFactory.getCEOMapper();

        Address address = new Address();

        SpaceCompany company = spaceCompanyService.getSpaceCompany(1L);
        Station station = stationService.getStation(2L);

//        address.setCity("Los Angeles");
//        address.setNumber(1189189);
//        address.setStreet("2nd street");
//        address.setStation(station);

        //addressMapper.insert(address);
        //addressMapper.update(address, 2L);
        //addressMapper.getAll();
        //addressMapper.getAddressesByStationId(1L);

        CEO ceo = new CEO();
        ceo.setFirstName("Jorge");
        ceo.setLastName("Smith");
        ceo.setAge(56);
        ceo.setEmail("asd@gmail.com");
        ceo.setSpaceCompany(company);

        //ceoMapper.insert(ceo);
        System.out.println(station.getSatellites());

    }
}
