package com.solvd.spaceCompany.daos.mysqlImpl;

import com.solvd.spaceCompany.utils.ConnectionPool;
import com.solvd.spaceCompany.daos.ICEODAO;
import com.solvd.spaceCompany.models.CEO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CEODAO implements ICEODAO {

    private final Logger LOGGER = LogManager.getLogger(CEODAO.class);

    private static final String INSERT_CEO =
            "INSERT INTO CEO (first_name, last_name, age, email, space_company_id) " +
                    "VALUES (?, ?, ?, ?, ?)";

    private static final String GET_CEO =
            "SELECT * FROM CEO WHERE id = ?";

    private static final String GET_ALL_CEOS =
            "SELECT * FROM CEO";

    private static final String UPDATE_CEO =
            "UPDATE CEO " +
                    "SET first_name = ?, last_name = ?, age = ?, email = ?, space_company_id = ? WHERE id=?";

    private static final String DELETE_CEO =
            "DELETE FROM CEO " +
                    "WHERE id=?";

    @Override
    public CEO get(Long id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = pool.getConnection();
            ps = connection.prepareStatement(GET_CEO);
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
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = pool.getConnection();
            ps = connection.prepareStatement(GET_ALL_CEOS);
            resultSet = ps.executeQuery();
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
    public void insert(CEO ceo) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = pool.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(INSERT_CEO, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, ceo.getFirstName());
            ps.setString(2, ceo.getLastName());
            ps.setInt(3, ceo.getAge());
            ps.setString(4, ceo.getEmail());
            ps.setLong(5, ceo.getSpaceCompany().getId());

            ps.executeUpdate();

            resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                ceo.setId(resultSet.getLong(1));
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
    public void update(CEO ceo, Long id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = pool.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(UPDATE_CEO);
            ps.setString(1, ceo.getFirstName());
            ps.setString(2, ceo.getLastName());
            ps.setInt(3, ceo.getAge());
            ps.setString(4, ceo.getEmail());
            ps.setLong(5, ceo.getSpaceCompany().getId());
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
            ps = connection.prepareStatement(DELETE_CEO);
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
