package com.solvd.spaceCompany.daos.mysqlImpl;

import com.solvd.spaceCompany.utils.ConnectionPool;
import com.solvd.spaceCompany.daos.ISpaceCompanyDAO;
import com.solvd.spaceCompany.models.SpaceCompany;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SpaceCompanyDAO implements ISpaceCompanyDAO {
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
            "DELETE FROM Space_Company " +
                    "WHERE id = ?";

    @Override
    public SpaceCompany get(Long id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = pool.getConnection();
            ps = connection.prepareStatement(GET_SPACE_COMPANY);
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

    public SpaceCompany extractFromResultSet(ResultSet resultSet) throws SQLException {
        SpaceCompany spaceCompany = new SpaceCompany();

        spaceCompany.setId(resultSet.getLong("id"));
        spaceCompany.setName(resultSet.getString("name"));

        return spaceCompany;
    }

    @Override
    public List<SpaceCompany> getAll() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = pool.getConnection();
            ps = connection.prepareStatement(GET_ALL_SPACE_COMPANIES);
            resultSet = ps.executeQuery();
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
    public void insert(SpaceCompany spaceCompany) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = pool.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(INSERT_SPACE_COMPANY, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, spaceCompany.getName());

            ps.executeUpdate();

            resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                spaceCompany.setId(resultSet.getLong(1));
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
    public void update(SpaceCompany spaceCompany, Long id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = pool.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(UPDATE_SPACE_COMPANY);
            ps.setString(1, spaceCompany.getName());
            ps.setLong(2, id);

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
            ps = connection.prepareStatement(DELETE_SPACE_COMPANY);
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
