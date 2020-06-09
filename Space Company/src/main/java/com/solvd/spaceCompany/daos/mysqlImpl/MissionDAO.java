package com.solvd.spaceCompany.daos.mysqlImpl;

import com.solvd.spaceCompany.utils.ConnectionPool;
import com.solvd.spaceCompany.daos.IMissionDAO;
import com.solvd.spaceCompany.models.Mission;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MissionDAO implements IMissionDAO {
    private final Logger LOGGER = LogManager.getLogger(MissionDAO.class);

    private static final String INSERT_MISSION =
            "INSERT INTO Mission (name, objective, span_in_years) " +
                    "VALUES (?, ?, ?)";

    private static final String GET_MISSION =
            "SELECT * FROM Mission WHERE id = ?";

    private static final String GET_ALL_MISSIONS =
            "SELECT * FROM Mission";

    private static final String UPDATE_MISSION =
            "UPDATE Mission " +
                    "SET name = ?, objective = ?, span_in_years = ? WHERE id = ?";

    private static final String DELETE_MISSION =
            "DELETE Mission " +
                    "WHERE id=?";

    @Override
    public Mission get(Long id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = pool.getConnection();
            ps = connection.prepareStatement(GET_MISSION);
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

    public Mission extractFromResultSet(ResultSet resultSet) throws SQLException {
        Mission mission = new Mission();

        mission.setId(resultSet.getLong("id"));
        mission.setName(resultSet.getString("name"));
        mission.setObjective(resultSet.getString("objective"));
        mission.setSpan(resultSet.getInt("span_in_years"));

        return mission;
    }

    @Override
    public List<Mission> getAll() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = pool.getConnection();
            ps = connection.prepareStatement(GET_ALL_MISSIONS);
            resultSet = ps.executeQuery();
            List<Mission> missions = new ArrayList<>();

            while (resultSet.next()) {
                Mission mission = extractFromResultSet(resultSet);
                missions.add(mission);
            }
            return missions;
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
    public void insert(Mission mission) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = pool.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(INSERT_MISSION, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, mission.getName());
            ps.setString(2, mission.getObjective());
            ps.setInt(3, mission.getSpan());

            ps.executeUpdate();

            resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                mission.setId(resultSet.getLong(1));
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
    public void update(Mission mission, Long id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = pool.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(UPDATE_MISSION);
            ps.setString(1, mission.getName());
            ps.setString(2, mission.getObjective());
            ps.setInt(3, mission.getSpan());
            ps.setLong(4, id);

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
            ps = connection.prepareStatement(DELETE_MISSION);
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
}
