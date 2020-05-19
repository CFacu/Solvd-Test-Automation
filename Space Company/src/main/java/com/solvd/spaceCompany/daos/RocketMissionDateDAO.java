package com.solvd.spaceCompany.daos;

import com.solvd.spaceCompany.ConnectionPool;
import com.solvd.spaceCompany.daos.interfaces.IDAO;
import com.solvd.spaceCompany.models.RocketMissionDate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RocketMissionDateDAO implements IDAO<RocketMissionDate> {
    private final Logger LOGGER = LogManager.getLogger(RocketMissionDateDAO.class);

    private static final String INSERT_ROCKET_MISSION_DATE =
            "INSERT INTO Rocket_Mission_Date (launch_date, rocket_id, mission_id) " +
                    "VALUES (?, ?, ?)";

    private static final String GET_ROCKET_MISSION_DATE =
            "SELECT * FROM Rocket_Mission_Date WHERE id=?";

    private static final String GET_ALL_ROCKET_MISSIONS_DATE =
            "SELECT * FROM Rocket_Mission_Date";

    private static final String UPDATE_ROCKET_MISSION_DATE =
            "UPDATE Rocket_Mission_Date" +
                    "SET launch_date, rocket_id, mission_id WHERE id=?";

    private static final String DELETE_ROCKET_MISSION_DATE =
            "DELETE Rocket_Mission_Date" +
                    "WHERE id=?";

    @Override
    public RocketMissionDate get(Long id) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(GET_ROCKET_MISSION_DATE);
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

    public RocketMissionDate extractFromResultSet(ResultSet resultSet) throws SQLException {
        RocketMissionDate mission = new RocketMissionDate();

        mission.setId(resultSet.getLong("id"));
        mission.setLaunchDate(resultSet.getDate("launch_date"));

        return mission;
    }

    @Override
    public List<RocketMissionDate> getAll() {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(GET_ALL_ROCKET_MISSIONS_DATE);
            ResultSet resultSet = ps.executeQuery();
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
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
        return null;
    }

    @Override
    public void insert(RocketMissionDate rocketMissionDate, Long id) {

    }

    @Override
    public void update(RocketMissionDate rocketMissionDate, Long id) {

    }

    public void insert(RocketMissionDate mission, Long rocketId, Long missionId) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(INSERT_ROCKET_MISSION_DATE);
            ps.setDate(1, mission.getLaunchDate());
            ps.setLong(2, rocketId);
            ps.setLong(3, missionId);

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

    public void update(RocketMissionDate mission, Long rocketId, Long missionId) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(UPDATE_ROCKET_MISSION_DATE);
            ps.setDate(1, mission.getLaunchDate());
            ps.setLong(2, rocketId);
            ps.setLong(3, missionId);
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
            PreparedStatement ps = connection.prepareStatement(DELETE_ROCKET_MISSION_DATE);
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
