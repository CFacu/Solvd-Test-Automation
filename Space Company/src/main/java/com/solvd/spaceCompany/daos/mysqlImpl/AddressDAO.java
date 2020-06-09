package com.solvd.spaceCompany.daos.mysqlImpl;

import com.solvd.spaceCompany.utils.ConnectionPool;
import com.solvd.spaceCompany.daos.IAddressDAO;
import com.solvd.spaceCompany.models.Address;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressDAO implements IAddressDAO {

    private final Logger LOGGER = LogManager.getLogger(AddressDAO.class);

    private static final String INSERT_ADDRESS =
            "INSERT INTO Addresses (city, street, number, stations_id) " +
                    "VALUES (?, ?, ?, ?)";

    private static final String GET_ADDRESS =
            "SELECT * FROM Addresses WHERE id=?";

    private static final String GET_ALL_ADDRESSES =
            "SELECT * FROM Addresses";

    private static final String UPDATE_ADDRESS =
            "UPDATE Addresses " +
                    "SET city = ?, street= ?, number= ?, stations_id = ? WHERE id=?";

    private static final String DELETE_ADDRESS =
            "DELETE FROM Addresses " +
                    "WHERE id=?";

    private static final String GET_ADDRESS_BY_STATION_ID =
            "SELECT * FROM Addresses " +
                    "WHERE stations_id = ?";

    @Override
    public Address get(Long id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = pool.getConnection();
            ps = connection.prepareStatement(GET_ADDRESS);
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

    public Address extractFromResultSet(ResultSet resultSet) throws SQLException{
        Address address = new Address();

        address.setId(resultSet.getLong("id"));
        address.setStreet(resultSet.getString("street"));
        address.setCity(resultSet.getString("city"));
        address.setNumber(resultSet.getInt("number"));

        return address;
    }

    @Override
    public List<Address> getAll() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = pool.getConnection();
            ps = connection.prepareStatement(GET_ALL_ADDRESSES);
            resultSet = ps.executeQuery();
            List<Address> addresses = new ArrayList<>();

            while (resultSet.next()) {
                Address address = extractFromResultSet(resultSet);
                addresses.add(address);
            }
            return addresses;
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
    public void insert(Address address){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = pool.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(INSERT_ADDRESS, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, address.getCity());
            ps.setString(2, address.getStreet());
            ps.setInt(3, address.getNumber());
            ps.setLong(4, address.getStation().getId());

            ps.executeUpdate();
            resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                address.setId(resultSet.getLong(1));
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
    public void update(Address address, Long id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = pool.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(UPDATE_ADDRESS);
            ps.setString(1, address.getCity());
            ps.setString(2, address.getStreet());
            ps.setInt(3, address.getNumber());
            ps.setLong(4, address.getStation().getId());
            ps.setLong(5, id);
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
            ps = connection.prepareStatement(DELETE_ADDRESS);
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
    public List<Address> getAddressesByStationId(Long id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = pool.getConnection();
            ps = connection.prepareStatement(GET_ADDRESS_BY_STATION_ID);
            ps.setLong(1, id);
            resultSet = ps.executeQuery();
            List<Address> addresses = new ArrayList<>();

            while (resultSet.next()) {
                Address address = extractFromResultSet(resultSet);
                addresses.add(address);
            }
            return addresses;
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
