package com.solvd.spaceCompany.daos.mysqlImpl;

import com.solvd.spaceCompany.ConnectionPool;
import com.solvd.spaceCompany.daos.IStationDAO;
import com.solvd.spaceCompany.models.Station;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StationDAO implements IStationDAO {
    private final Logger LOGGER = LogManager.getLogger(StationDAO.class);

    private static final String INSERT_STATION =
            "INSERT INTO Stations (name, space_company_id) " +
                    "VALUES (?, ?)";

    private static final String GET_STATION =
            "SELECT * FROM Stations WHERE id = ?";

    private static final String GET_ALL_STATIONS =
            "SELECT * FROM Stations";

    private static final String UPDATE_STATION =
            "UPDATE Stations " +
                    "SET name = ?, space_company_id = ? WHERE id = ?";

    private static final String DELETE_STATION =
            "DELETE Stations " +
                    "WHERE id = ?";

    private static final String GET_ALL_BY_SPACE_COMPANY_ID =
            "SELECT * FROM Stations " +
                    "WHERE space_company_id = ?";

    @Override
    public Station get(Long id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = pool.getConnection();
            ps = connection.prepareStatement(GET_STATION);
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

    public Station extractFromResultSet(ResultSet resultSet) throws SQLException {
        Station station = new Station();

        station.setId(resultSet.getLong("id"));
        station.setName(resultSet.getString("name"));

        return station;
    }

    @Override
    public List<Station> getAll() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = pool.getConnection();
            ps = connection.prepareStatement(GET_ALL_STATIONS);
            resultSet = ps.executeQuery();
            List<Station> stations = new ArrayList<>();

            while (resultSet.next()) {
                Station station = extractFromResultSet(resultSet);
                stations.add(station);
            }
            return stations;
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
    public void insert(Station station) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = pool.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(INSERT_STATION, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, station.getName());
            ps.setLong(2, station.getSpaceCompany().getId());

            ps.executeUpdate();

            resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                station.setId(resultSet.getLong(1));
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
    public void update(Station station, Long id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = pool.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(UPDATE_STATION);
            ps.setString(1, station.getName());
            ps.setLong(2, station.getSpaceCompany().getId());
            ps.setLong(3, id);
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
            ps = connection.prepareStatement(DELETE_STATION);
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
    public List<Station> getAllBySpaceCompanyId(Long spaceCompanyId) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = pool.getConnection();
            ps = connection.prepareStatement(GET_ALL_BY_SPACE_COMPANY_ID);
            ps.setLong(1, spaceCompanyId);
            resultSet = ps.executeQuery();
            List<Station> stations = new ArrayList<>();

            while (resultSet.next()) {
                Station station = extractFromResultSet(resultSet);
                stations.add(station);
            }
            return stations;
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
