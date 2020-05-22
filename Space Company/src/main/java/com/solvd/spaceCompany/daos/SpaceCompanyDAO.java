package com.solvd.spaceCompany.daos;

import com.solvd.spaceCompany.ConnectionPool;
import com.solvd.spaceCompany.daos.interfaces.IDAO;
import com.solvd.spaceCompany.models.SpaceCompany;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SpaceCompanyDAO implements IDAO<SpaceCompany> {
    private final Logger LOGGER = LogManager.getLogger(SpaceCompanyDAO.class);

    private static final String INSERT_SPACE_COMPANY =
            "INSERT INTO Space_Company (name) " +
                    "VALUES (?)";

    private static final String GET_SPACE_COMPANY =
            "SELECT * FROM Space_Company WHERE id = ?";

    private static final String GET_ALL_SPACE_COMPANIES =
            "SELECT * FROM Space_Company";

    private static final String UPDATE_SPACE_COMPANY =
            "UPDATE Space_Company " +
                    "SET name = ? WHERE id = ?";

    private static final String DELETE_SPACE_COMPANY =
            "DELETE Space_Company " +
                    "WHERE id = ?";

    @Override
    public SpaceCompany get(Long id) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(GET_SPACE_COMPANY);
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

    public SpaceCompany extractFromResultSet(ResultSet resultSet) throws SQLException {
        SpaceCompany spaceCompany = new SpaceCompany();

        spaceCompany.setId(resultSet.getLong("id"));
        spaceCompany.setName(resultSet.getString("name"));

        return spaceCompany;
    }

    @Override
    public List<SpaceCompany> getAll() {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(GET_ALL_SPACE_COMPANIES);
            ResultSet resultSet = ps.executeQuery();
            List<SpaceCompany> spaceCompanies = new ArrayList<>();

            while (resultSet.next()) {
                SpaceCompany spaceCompany = extractFromResultSet(resultSet);
                spaceCompanies.add(spaceCompany);
            }
            return spaceCompanies;
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
    public void insert(SpaceCompany spaceCompany) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(INSERT_SPACE_COMPANY, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, spaceCompany.getName());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                spaceCompany.setId(rs.getLong(1));
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
    public void update(SpaceCompany spaceCompany, Long id) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(UPDATE_SPACE_COMPANY);
            ps.setString(1, spaceCompany.getName());
            ps.setLong(2, id);

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
            PreparedStatement ps = connection.prepareStatement(DELETE_SPACE_COMPANY);
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
