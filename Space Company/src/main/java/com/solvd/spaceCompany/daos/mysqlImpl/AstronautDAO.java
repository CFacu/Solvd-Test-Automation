package com.solvd.spaceCompany.daos.mysqlImpl;

import com.solvd.spaceCompany.utils.ConnectionPool;
import com.solvd.spaceCompany.daos.IAstronautDAO;
import com.solvd.spaceCompany.models.Astronaut;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AstronautDAO implements IAstronautDAO {

    private final Logger LOGGER = LogManager.getLogger(AstronautDAO.class);

    private static final String INSERT_ASTRONAUT =
            "INSERT INTO Astronauts (first_name, last_name, age, duty, stations_id) " +
                    "VALUES (?, ?, ?, ?, ?)";

    private static final String GET_ASTRONAUT =
            "SELECT * FROM Astronauts WHERE id = ?";

    private static final String GET_ALL_ASTRONAUTS =
            "SELECT * FROM Astronauts";

    private static final String UPDATE_ASTRONAUT =
            "UPDATE Astronauts " +
                    "SET first_name = ?, last_name = ?, age = ?, duty = ?, stations_id = ? WHERE id = ?";

    private static final String DELETE_ASTRONAUT =
            "DELETE FROM Astronauts " +
                    "WHERE id = ?";

    private static final String GET_ALL_BY_STATION_ID =
            "SELECT * FROM Astronauts " +
                    "WHERE stations_id = ?";

    @Override
    public Astronaut get(Long id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = pool.getConnection();
            ps = connection.prepareStatement(GET_ASTRONAUT);
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

    @Override
    public Astronaut extractFromResultSet(ResultSet resultSet) throws SQLException {
        Astronaut astronaut = new Astronaut();

        astronaut.setId(resultSet.getLong("id"));
        astronaut.setFirstName(resultSet.getString("first_name"));
        astronaut.setLastName(resultSet.getString("last_name"));
        astronaut.setAge(resultSet.getInt("age"));
        astronaut.setDuty(resultSet.getString("duty"));

        return astronaut;
    }

    @Override
    public List<Astronaut> getAll() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = pool.getConnection();
            ps = connection.prepareStatement(GET_ALL_ASTRONAUTS);
            resultSet = ps.executeQuery();
            List<Astronaut> astronauts = new ArrayList<>();

            while (resultSet.next()) {
                Astronaut astronaut = extractFromResultSet(resultSet);
                astronauts.add(astronaut);
            }
            return astronauts;
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
    public void insert(Astronaut astronaut) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = pool.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(INSERT_ASTRONAUT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, astronaut.getFirstName());
            ps.setString(2, astronaut.getLastName());
            ps.setInt(3, astronaut.getAge());
            ps.setString(4, astronaut.getDuty());
            ps.setLong(5, astronaut.getStation().getId());

            ps.executeUpdate();

            resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                astronaut.setId(resultSet.getLong(1));
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
    public void update(Astronaut astronaut, Long id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = pool.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(UPDATE_ASTRONAUT);
            ps.setString(1, astronaut.getFirstName());
            ps.setString(2, astronaut.getLastName());
            ps.setInt(3, astronaut.getAge());
            ps.setString(4, astronaut.getDuty());
            ps.setLong(5, astronaut.getStation().getId());
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
            ps = connection.prepareStatement(DELETE_ASTRONAUT);
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
    public List<Astronaut> getAllByStationId(Long stationId) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = pool.getConnection();
            ps = connection.prepareStatement(GET_ALL_BY_STATION_ID);
            ps.setLong(1, stationId);
            resultSet = ps.executeQuery();
            List<Astronaut> astronauts = new ArrayList<>();

            while (resultSet.next()) {
                Astronaut astronaut = extractFromResultSet(resultSet);
                astronauts.add(astronaut);
            }
            return astronauts;
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