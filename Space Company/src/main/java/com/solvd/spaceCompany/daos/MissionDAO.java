package com.solvd.spaceCompany.daos;

import com.solvd.spaceCompany.ConnectionPool;
import com.solvd.spaceCompany.daos.interfaces.IDAO;
import com.solvd.spaceCompany.models.Mission;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MissionDAO implements IDAO<Mission> {
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
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(GET_MISSION);
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
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(GET_ALL_MISSIONS);
            ResultSet resultSet = ps.executeQuery();
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
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
        return null;
    }

    @Override
    public void insert(Mission mission) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(INSERT_MISSION);
            ps.setString(1, mission.getName());
            ps.setString(2, mission.getObjective());
            ps.setInt(3, mission.getSpan());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                mission.setId(rs.getLong(1));
            }

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
    public void update(Mission mission, Long id) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(UPDATE_MISSION);
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
            PreparedStatement ps = connection.prepareStatement(DELETE_MISSION);
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
