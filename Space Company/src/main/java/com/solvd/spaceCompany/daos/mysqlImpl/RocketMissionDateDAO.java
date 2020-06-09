package com.solvd.spaceCompany.daos.mysqlImpl;

import com.solvd.spaceCompany.utils.ConnectionPool;
import com.solvd.spaceCompany.daos.IDAO;
import com.solvd.spaceCompany.models.RocketMissionDate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RocketMissionDateDAO implements IDAO<RocketMissionDate> {
    private final Logger LOGGER = LogManager.getLogger(RocketMissionDateDAO.class);

    private static final String INSERT_ROCKET_MISSION_DATE =
            "INSERT INTO Rocket_Mission_Date (launch_date, rocket_id, mission_id) " +
                    "VALUES (?, ?, ?)";

    private static final String GET_ROCKET_MISSION_DATE =
            "SELECT * FROM Rocket_Mission_Date WHERE id = ?";

    private static final String GET_ALL_ROCKET_MISSIONS_DATE =
            "SELECT * FROM Rocket_Mission_Date";

    private static final String UPDATE_ROCKET_MISSION_DATE =
            "UPDATE Rocket_Mission_Date " +
                    "SET launch_date = ?, rocket_id = ?, mission_id = ? WHERE id = ?";

    private static final String DELETE_ROCKET_MISSION_DATE =
            "DELETE FROM Rocket_Mission_Date " +
                    "WHERE id=?";

    @Override
    public RocketMissionDate get(Long id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = pool.getConnection();
            ps = connection.prepareStatement(GET_ROCKET_MISSION_DATE);
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

    public RocketMissionDate extractFromResultSet(ResultSet resultSet) throws SQLException {
        RocketMissionDate mission = new RocketMissionDate();

        mission.setId(resultSet.getLong("id"));
        mission.setLaunchDate(resultSet.getDate("launch_date"));

        return mission;
    }

    @Override
    public List<RocketMissionDate> getAll() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = pool.getConnection();
            ps = connection.prepareStatement(GET_ALL_ROCKET_MISSIONS_DATE);
            resultSet = ps.executeQuery();
            List<RocketMissionDate> missions = new ArrayList<>();

            while (resultSet.next()) {
                RocketMissionDate mission = extractFromResultSet(resultSet);
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
    public void insert(RocketMissionDate mission) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = pool.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(INSERT_ROCKET_MISSION_DATE, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, mission.getLaunchDate());
            ps.setLong(2, mission.getRocket().getId());
            ps.setLong(3, mission.getMission().getId());

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

    public void update(RocketMissionDate mission, Long id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = pool.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(UPDATE_ROCKET_MISSION_DATE);
            ps.setDate(1, mission.getLaunchDate());
            ps.setLong(2, mission.getRocket().getId());
            ps.setLong(3, mission.getMission().getId());
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
            ps = connection.prepareStatement(DELETE_ROCKET_MISSION_DATE);
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
