package com.solvd.spaceCompany.daos.mysqlImpl;

import com.solvd.spaceCompany.ConnectionPool;
import com.solvd.spaceCompany.daos.IEngineerDAO;
import com.solvd.spaceCompany.models.Engineer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EngineerDAO implements IEngineerDAO {
    private final Logger LOGGER = LogManager.getLogger(EngineerDAO.class);

    private static final String INSERT_ENGINEER =
            "INSERT INTO Engineers (first_name, last_name, age, speciality, space_company_id) " +
                    "VALUES (?, ?, ?, ?, ?)";

    private static final String GET_ENGINEER =
            "SELECT * FROM Engineers WHERE id = ?";

    private static final String GET_ALL_ENGINEERS =
            "SELECT * FROM Engineers";

    private static final String UPDATE_ENGINEER =
            "UPDATE Engineers " +
                    "SET first_name = ?, last_name = ?, age = ?, speciality = ?, space_company_id = ? WHERE id=?";

    private static final String DELETE_ENGINEER =
            "DELETE Engineers " +
                    "WHERE id=?";

    private static final String GET_ALL_BY_SPACE_COMPANY_ID =
            "SELECT * FROM Engineers " +
                    "WHERE space_company_id = ?";

    @Override
    public Engineer get(Long id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = pool.getConnection();
            ps = connection.prepareStatement(GET_ENGINEER);
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
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = pool.getConnection();
            ps = connection.prepareStatement(GET_ALL_ENGINEERS);
            resultSet = ps.executeQuery();
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
    public void insert(Engineer engineer) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = pool.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(INSERT_ENGINEER , Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, engineer.getFirstName());
            ps.setString(2, engineer.getLastName());
            ps.setInt(3, engineer.getAge());
            ps.setString(4, engineer.getSpeciality());
            ps.setLong(5, engineer.getSpaceCompany().getId());

            ps.executeUpdate();

            resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                engineer.setId(resultSet.getLong(1));
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
    public void update(Engineer engineer, Long id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = pool.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(UPDATE_ENGINEER);
            ps.setString(1, engineer.getFirstName());
            ps.setString(2, engineer.getLastName());
            ps.setInt(3, engineer.getAge());
            ps.setString(4, engineer.getSpeciality());
            ps.setLong(5, engineer.getSpaceCompany().getId());
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
            ps = connection.prepareStatement(DELETE_ENGINEER);
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

    @Override
    public List<Engineer> getAllBySpaceCompanyId(Long spaceCompanyId) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = pool.getConnection();
            ps = connection.prepareStatement(GET_ALL_BY_SPACE_COMPANY_ID);
            ps.setLong(1, spaceCompanyId);
            resultSet = ps.executeQuery();
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
}
