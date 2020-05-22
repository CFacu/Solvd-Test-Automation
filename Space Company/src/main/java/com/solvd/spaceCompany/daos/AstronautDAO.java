package com.solvd.spaceCompany.daos;

import com.solvd.spaceCompany.ConnectionPool;
import com.solvd.spaceCompany.daos.interfaces.IDAO;
import com.solvd.spaceCompany.models.Astronaut;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AstronautDAO implements IDAO<Astronaut> {

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
            "DELETE Astronauts " +
                    "WHERE id = ?";

    @Override
    public Astronaut get(Long id) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(GET_ASTRONAUT);
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
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(GET_ALL_ASTRONAUTS);
            ResultSet resultSet = ps.executeQuery();
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
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
        return null;
    }

    @Override
    public void insert(Astronaut astronaut) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(INSERT_ASTRONAUT);
            ps.setString(1, astronaut.getFirstName());
            ps.setString(2, astronaut.getLastName());
            ps.setInt(3, astronaut.getAge());
            ps.setString(4, astronaut.getDuty());
            ps.setLong(5, astronaut.getStation().getId());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                astronaut.setId(rs.getLong(1));
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
    public void update(Astronaut astronaut, Long id) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(UPDATE_ASTRONAUT);
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
            PreparedStatement ps = connection.prepareStatement(DELETE_ASTRONAUT);
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