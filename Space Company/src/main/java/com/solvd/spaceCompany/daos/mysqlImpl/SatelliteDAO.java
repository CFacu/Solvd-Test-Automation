package com.solvd.spaceCompany.daos.mysqlImpl;

import com.solvd.spaceCompany.ConnectionPool;
import com.solvd.spaceCompany.daos.ISatelliteDAO;
import com.solvd.spaceCompany.models.Satellite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SatelliteDAO implements ISatelliteDAO {
    private final Logger LOGGER = LogManager.getLogger(SatelliteDAO.class);

    private static final String INSERT_SATELLITE =
            "INSERT INTO Satellites (name, weight, cargo_capacity, fuel_capacity, stations_id) " +
                    "VALUES (?, ?, ?, ?, ?)";

    private static final String GET_SATELLITE =
            "SELECT * FROM Satellites WHERE id = ?";

    private static final String GET_ALL_SATELLITES =
            "SELECT * FROM Satellites";

    private static final String UPDATE_SATELLITE =
            "UPDATE Satellites " +
                    "SET name = ?, weight = ?, cargo_capacity = ?, fuel_capacity = ?, stations_id = ? WHERE id = ?";

    private static final String DELETE_SATELLITE =
            "DELETE Satellites " +
                    "WHERE id=?";

    private static final String GET_ALL_BY_STATION_ID =
            "SELECT * FROM Satellites " +
                    "WHERE stations_id = ?";

    @Override
    public Satellite get(Long id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = pool.getConnection();
            ps = connection.prepareStatement(GET_SATELLITE);
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
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = pool.getConnection();
            ps = connection.prepareStatement(GET_ALL_SATELLITES);
            resultSet = ps.executeQuery();
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
    public void insert(Satellite satellite) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = pool.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(INSERT_SATELLITE, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, satellite.getName());
            ps.setFloat(2, satellite.getWeight());
            ps.setFloat(3, satellite.getCargoCapacity());
            ps.setFloat(4, satellite.getFuelCapacity());
            ps.setLong(5, satellite.getStation().getId());

            ps.executeUpdate();

            resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                satellite.setId(resultSet.getLong(1));
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
    public void update(Satellite satellite, Long id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = pool.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(UPDATE_SATELLITE);
            ps.setString(1, satellite.getName());
            ps.setFloat(2, satellite.getWeight());
            ps.setFloat(3, satellite.getCargoCapacity());
            ps.setFloat(4, satellite.getFuelCapacity());
            ps.setLong(5, satellite.getStation().getId());
            ps.setLong(6, id);

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
            ps = connection.prepareStatement(DELETE_SATELLITE);
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
    public List<Satellite> getAllByStationId(Long stationId) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = pool.getConnection();
            ps = connection.prepareStatement(GET_ALL_BY_STATION_ID);
            ps.setLong(1, stationId);
            resultSet = ps.executeQuery();
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
