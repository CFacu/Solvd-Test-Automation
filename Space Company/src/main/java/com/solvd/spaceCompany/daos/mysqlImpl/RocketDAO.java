package com.solvd.spaceCompany.daos.mysqlImpl;

import com.solvd.spaceCompany.utils.ConnectionPool;
import com.solvd.spaceCompany.daos.IRocketDAO;
import com.solvd.spaceCompany.models.Rocket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RocketDAO implements IRocketDAO {
    private final Logger LOGGER = LogManager.getLogger(RocketDAO.class);

    private static final String INSERT_ROCKET =
            "INSERT INTO Rockets (name, weight, fuel_capacity, passengers_capacity, cargo_capacity, space_company_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String GET_ROCKET =
            "SELECT * FROM Rockets WHERE id = ?";

    private static final String GET_ALL_ROCKETS =
            "SELECT * FROM Rockets";

    private static final String UPDATE_ROCKET =
            "UPDATE Rockets " +
                    "SET name = ?, weight = ?, fuel_capacity = ?, passengers_capacity = ?, cargo_capacity = ?, space_company_id = ? WHERE id = ?";

    private static final String DELETE_ROCKET =
            "DELETE FROM Rockets " +
                    "WHERE id=?";

    private static final String GET_ALL_BY_SPACE_COMPANY_ID =
            "SELECT * FROM Rockets " +
                    "WHERE space_company_id = ?";

    @Override
    public Rocket get(Long id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = pool.getConnection();
            ps = connection.prepareStatement(GET_ROCKET);
            ps.setLong(1, id);
            resultSet = ps.executeQuery();
            resultSet.next();
            return extractFromResultSet(resultSet);
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e);
            } finally {
                try {
                    if (resultSet != null) {
                        resultSet.close();
                    }
                } catch (SQLException e) {
                    LOGGER.error(e);
                } finally {
                    pool.releaseConnection(connection);
                }
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
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = pool.getConnection();
            ps = connection.prepareStatement(GET_ALL_ROCKETS);
            resultSet = ps.executeQuery();
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
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e);
            } finally {
                try {
                    if (resultSet != null) {
                        resultSet.close();
                    }
                } catch (SQLException e) {
                    LOGGER.error(e);
                } finally {
                    pool.releaseConnection(connection);
                }
            }
        }
        return null;
    }

    @Override
    public void insert(Rocket rocket) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = pool.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(INSERT_ROCKET, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, rocket.getName());
            ps.setFloat(2, rocket.getWeight());
            ps.setFloat(3, rocket.getFuelCapacity());
            ps.setInt(4, rocket.getPassengerCapacity());
            ps.setFloat(5, rocket.getCargoCapacity());
            ps.setLong(6, rocket.getSpaceCompany().getId());

            ps.executeUpdate();

            resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                rocket.setId(resultSet.getLong(1));
            }

            connection.commit();

        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e);
            } finally {
                try {
                    if (resultSet != null) {
                        resultSet.close();
                    }
                } catch (SQLException e) {
                    LOGGER.error(e);
                } finally {
                    pool.releaseConnection(connection);
                }
            }
        }
    }

    @Override
    public void update(Rocket rocket, Long id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = pool.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(UPDATE_ROCKET);
            ps.setString(1, rocket.getName());
            ps.setFloat(2, rocket.getWeight());
            ps.setFloat(3, rocket.getFuelCapacity());
            ps.setInt(4, rocket.getPassengerCapacity());
            ps.setFloat(5, rocket.getCargoCapacity());
            ps.setLong(6, rocket.getSpaceCompany().getId());
            ps.setLong(7, id);

            ps.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e);
            } finally {
                pool.releaseConnection(connection);
            }
        }
    }

    @Override
    public void delete(Long id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = pool.getConnection();
            ps = connection.prepareStatement(DELETE_ROCKET);
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e);
            } finally {
                pool.releaseConnection(connection);
            }
        }
    }

    @Override
    public List<Rocket> getAllBySpaceCompanyId(Long spaceCompanyId) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = pool.getConnection();
            ps = connection.prepareStatement(GET_ALL_BY_SPACE_COMPANY_ID);
            ps.setLong(1, spaceCompanyId);
            resultSet = ps.executeQuery();
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
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e);
            } finally {
                try {
                    if (resultSet != null) {
                        resultSet.close();
                    }
                } catch (SQLException e) {
                    LOGGER.error(e);
                } finally {
                    pool.releaseConnection(connection);
                }
            }
        }
        return null;
    }
}
