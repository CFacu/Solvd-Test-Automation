package com.solvd.spaceCompany.daos;

import com.solvd.spaceCompany.ConnectionPool;
import com.solvd.spaceCompany.daos.interfaces.IDAO;
import com.solvd.spaceCompany.models.CEO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CEODAO implements IDAO<CEO> {

    private final Logger LOGGER = LogManager.getLogger(CEODAO.class);

    private static final String INSERT_CEO =
            "INSERT INTO CEO (first_name, last_name, age, email, space_company_id) " +
                    "VALUES (?, ?, ?, ?, ?)";

    private static final String GET_CEO =
            "SELECT * FROM CEO WHERE id=?";

    private static final String GET_ALL_CEOS =
            "SELECT * FROM CEO";

    private static final String UPDATE_CEO =
            "UPDATE CEO" +
                    "SET first_name, last_name, age, email, space_company_id WHERE id=?";

    private static final String DELETE_CEO =
            "DELETE CEO" +
                    "WHERE id=?";

    @Override
    public CEO get(Long id) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(GET_CEO);
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

    public CEO extractFromResultSet(ResultSet resultSet) throws SQLException {
        CEO ceo = new CEO();

        ceo.setId(resultSet.getLong("id"));
        ceo.setFirstName(resultSet.getString("first_name"));
        ceo.setLastName(resultSet.getString("last_name"));
        ceo.setAge(resultSet.getInt("age"));
        ceo.setEmail(resultSet.getString("email"));

        return ceo;
    }

    @Override
    public List<CEO> getAll() {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(GET_ALL_CEOS);
            ResultSet resultSet = ps.executeQuery();
            List<CEO> ceos = new ArrayList<>();

            while (resultSet.next()) {
                CEO ceo = extractFromResultSet(resultSet);
                ceos.add(ceo);
            }
            return ceos;
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
    public void insert(CEO ceo, Long spaceCompanyId) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(INSERT_CEO);
            ps.setString(1, ceo.getFirstName());
            ps.setString(2, ceo.getLastName());
            ps.setInt(3, ceo.getAge());
            ps.setString(4, ceo.getEmail());
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
    public void update(CEO ceo, Long spaceCompanyId) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(UPDATE_CEO);
            ps.setString(1, ceo.getFirstName());
            ps.setString(2, ceo.getLastName());
            ps.setInt(3, ceo.getAge());
            ps.setString(4, ceo.getEmail());
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
            PreparedStatement ps = connection.prepareStatement(DELETE_CEO);
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
