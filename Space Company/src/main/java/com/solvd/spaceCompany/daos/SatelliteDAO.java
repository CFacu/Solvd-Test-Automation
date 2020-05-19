package com.solvd.spaceCompany.daos;

import com.solvd.spaceCompany.ConnectionPool;
import com.solvd.spaceCompany.daos.interfaces.IDAO;
import com.solvd.spaceCompany.models.Satellite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SatelliteDAO implements IDAO<Satellite> {
    private final Logger LOGGER = LogManager.getLogger(SatelliteDAO.class);

    private static final String INSERT_SATELLITE =
            "INSERT INTO Satellites (name, weight, cargo_capacity, fuel_capacity, stations_id) " +
                    "VALUES (?, ?, ?, ?, ?)";

    private static final String GET_SATELLITE =
            "SELECT * FROM Satellites WHERE id=?";

    private static final String GET_ALL_SATELLITES =
            "SELECT * FROM Satellites";

    private static final String UPDATE_SATELLITE =
            "UPDATE Satellites" +
                    "SET name, weight, cargo_capacity, fuel_capacity, stations_id WHERE id=?";

    private static final String DELETE_SATELLITE =
            "DELETE Satellites" +
                    "WHERE id=?";

    @Override
    public Satellite get(Long id) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(GET_SATELLITE);
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                return extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
        return null;
    }

    public Satellite extractFromResultSet(ResultSet resultSet) throws SQLException {
        Satellite satellite = new Satellite();

        satellite.setId(resultSet.getLong("id"));
        satellite.setName(resultSet.getString("name"));
        satellite.setWeight(resultSet.getFloat("weight"));
        satellite.setCargoCapacity(resultSet.getFloat("cargo_capacity"));
        satellite.setFuelCapacity(resultSet.getFloat("fuel_capacity"));

        return satellite;
    }

    @Override
    public List<Satellite> getAll() {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(GET_ALL_SATELLITES);
            ResultSet resultSet = ps.executeQuery();
            List<Satellite> satellites = new ArrayList<>();

            while (resultSet.next()) {
                Satellite satellite = extractFromResultSet(resultSet);
                satellites.add(satellite);
            }
            return satellites;
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
        return null;
    }

    @Override
    public void insert(Satellite satellite, Long stationId) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(INSERT_SATELLITE);
            ps.setString(1, satellite.getName());
            ps.setFloat(2, satellite.getWeight());
            ps.setFloat(3, satellite.getCargoCapacity());
            ps.setFloat(4, satellite.getFuelCapacity());
            ps.setLong(5, stationId);

            ps.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
    }

    @Override
    public void update(Satellite satellite, Long stationId) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(UPDATE_SATELLITE);
            ps.setString(1, satellite.getName());
            ps.setFloat(2, satellite.getWeight());
            ps.setFloat(3, satellite.getCargoCapacity());
            ps.setFloat(4, satellite.getFuelCapacity());
            ps.setLong(5, stationId);
            ps.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
    }

    @Override
    public void delete(Long id) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(DELETE_SATELLITE);
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
    }
}
