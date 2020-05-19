package com.solvd.spaceCompany.daos;

import com.solvd.spaceCompany.ConnectionPool;
import com.solvd.spaceCompany.daos.interfaces.IDAO;
import com.solvd.spaceCompany.models.Engineer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EngineerDAO implements IDAO<Engineer> {
    private final Logger LOGGER = LogManager.getLogger(EngineerDAO.class);

    private static final String INSERT_ENGINEER =
            "INSERT INTO Engineers (first_name, last_name, age, speciality, space_company_id) " +
                    "VALUES (?, ?, ?, ?, ?)";

    private static final String GET_ENGINEER =
            "SELECT * FROM Engineers WHERE id=?";

    private static final String GET_ALL_ENGINEERS =
            "SELECT * FROM Engineers";

    private static final String UPDATE_ENGINEER =
            "UPDATE Engineers" +
                    "SET first_name, last_name, age, speciality, space_company_id WHERE id=?";

    private static final String DELETE_ENGINEER =
            "DELETE Engineers" +
                    "WHERE id=?";

    @Override
    public Engineer get(Long id) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(GET_ENGINEER);
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

    public Engineer extractFromResultSet(ResultSet resultSet) throws SQLException {
        Engineer engineer = new Engineer();

        engineer.setId(resultSet.getLong("id"));
        engineer.setFirstName(resultSet.getString("first_name"));
        engineer.setLastName(resultSet.getString("last_name"));
        engineer.setAge(resultSet.getInt("age"));
        engineer.setSpeciality(resultSet.getString("speciality"));

        return engineer;
    }

    @Override
    public List<Engineer> getAll() {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(GET_ALL_ENGINEERS);
            ResultSet resultSet = ps.executeQuery();
            List<Engineer> engineers = new ArrayList<>();

            while (resultSet.next()) {
                Engineer engineer = extractFromResultSet(resultSet);
                engineers.add(engineer);
            }
            return engineers;
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
    public void insert(Engineer engineer, Long spaceCompanyId) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(INSERT_ENGINEER);
            ps.setString(1, engineer.getFirstName());
            ps.setString(2, engineer.getLastName());
            ps.setInt(3, engineer.getAge());
            ps.setString(4, engineer.getSpeciality());
            ps.setLong(5, spaceCompanyId);

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
    public void update(Engineer engineer, Long spaceCompanyId) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(UPDATE_ENGINEER);
            ps.setString(1, engineer.getFirstName());
            ps.setString(2, engineer.getLastName());
            ps.setInt(3, engineer.getAge());
            ps.setString(4, engineer.getSpeciality());
            ps.setLong(5, spaceCompanyId);
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
            PreparedStatement ps = connection.prepareStatement(DELETE_ENGINEER);
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
