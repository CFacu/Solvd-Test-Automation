package com.solvd.spaceCompany.daos;

import com.solvd.spaceCompany.ConnectionPool;
import com.solvd.spaceCompany.daos.interfaces.IDAO;
import com.solvd.spaceCompany.models.Rocket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RocketDAO implements IDAO<Rocket> {
    private final Logger LOGGER = LogManager.getLogger(RocketDAO.class);

    private static final String INSERT_ROCKET =
            "INSERT INTO Rockets (name, weight, fuel_capacity, passengers_capacity, cargo_capacity, space_company_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String GET_ROCKET =
            "SELECT * FROM Rockets WHERE id=?";

    private static final String GET_ALL_ROCKETS =
            "SELECT * FROM Rockets";

    private static final String UPDATE_ROCKET =
            "UPDATE Rockets" +
                    "SET name, weight, fuel_capacity, passengers_capacity, cargo_capacity, space_company_id WHERE id=?";

    private static final String DELETE_ROCKET =
            "DELETE Rockets" +
                    "WHERE id=?";

    @Override
    public Rocket get(Long id) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(GET_ROCKET);
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

    public Rocket extractFromResultSet(ResultSet resultSet) throws SQLException {
        Rocket rocket = new Rocket();

        rocket.setId(resultSet.getLong("id"));
        rocket.setName(resultSet.getString("name"));
        rocket.setWeight(resultSet.getFloat("weight"));
        rocket.setCargoCapacity(resultSet.getFloat("cargo_capacity"));
        rocket.setFuelCapacity(resultSet.getFloat("fuel_capacity"));
        rocket.setPassengerCapacity(resultSet.getInt("passengers_capacity"));

        return rocket;
    }

    @Override
    public List<Rocket> getAll() {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(GET_ALL_ROCKETS);
            ResultSet resultSet = ps.executeQuery();
            List<Rocket> rockets = new ArrayList<>();

            while (resultSet.next()) {
                Rocket rocket = extractFromResultSet(resultSet);
                rockets.add(rocket);
            }
            return rockets;
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
    public void insert(Rocket rocket, Long spaceCompanyId) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(INSERT_ROCKET);
            ps.setString(1, rocket.getName());
            ps.setFloat(2, rocket.getWeight());
            ps.setFloat(3, rocket.getFuelCapacity());
            ps.setInt(4, rocket.getPassengerCapacity());
            ps.setFloat(5, rocket.getCargoCapacity());
            ps.setLong(6, spaceCompanyId);

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
    public void update(Rocket rocket, Long spaceCompanyId) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(UPDATE_ROCKET);
            ps.setString(1, rocket.getName());
            ps.setFloat(2, rocket.getWeight());
            ps.setFloat(3, rocket.getFuelCapacity());
            ps.setInt(4, rocket.getPassengerCapacity());
            ps.setFloat(5, rocket.getCargoCapacity());
            ps.setLong(6, spaceCompanyId);
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
            PreparedStatement ps = connection.prepareStatement(DELETE_ROCKET);
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
