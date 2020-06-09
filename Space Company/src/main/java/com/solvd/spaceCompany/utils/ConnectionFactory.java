package com.solvd.spaceCompany.utils;

import com.solvd.spaceCompany.daos.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Reader;

public class ConnectionFactory {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionFactory.class);
    private static SqlSessionFactory factory;

    static {
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
        } catch (IOException e) {
            LOGGER.error(e);
        }
        factory = new SqlSessionFactoryBuilder().build(reader);
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return factory;
    }

    public static IAddressDAO getAddressMapper(){
        return getSqlSessionFactory().openSession(true).getMapper(IAddressDAO.class);
    }

    public static IAstronautDAO getAstronautMapper(){
        return getSqlSessionFactory().openSession(true).getMapper(IAstronautDAO.class);
    }

    public static ICEODAO getCEOMapper(){
        return getSqlSessionFactory().openSession(true).getMapper(ICEODAO.class);
    }

    public static IEngineerDAO getEngineerMapper(){
        return getSqlSessionFactory().openSession(true).getMapper(IEngineerDAO.class);
    }

    public static IMissionDAO getMissionMapper(){
        return getSqlSessionFactory().openSession(true).getMapper(IMissionDAO.class);
    }

    public static IRocketDAO getRocketMapper(){
        return getSqlSessionFactory().openSession(true).getMapper(IRocketDAO.class);
    }

    public static ISatelliteDAO getSatelliteMapper(){
        return getSqlSessionFactory().openSession(true).getMapper(ISatelliteDAO.class);
    }

    public static ISpaceCompanyDAO getSpaceCompanyMapper(){
        return getSqlSessionFactory().openSession(true).getMapper(ISpaceCompanyDAO.class);
    }

    public static IStationDAO getStationMapper(){
        return getSqlSessionFactory().openSession(true).getMapper(IStationDAO.class);
    }
}
