package com.solvd.spaceCompany.daos;

import com.solvd.spaceCompany.ConnectionPool;
import com.solvd.spaceCompany.daos.interfaces.IDAO;
import com.solvd.spaceCompany.models.Address;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressDAO implements IDAO<Address> {

    private final Logger LOGGER = LogManager.getLogger(AddressDAO.class);

    private static final String INSERT_ADDRESS =
            "INSERT INTO Addresses (city, street, number, stations_id) " +
                    "VALUES (?, ?, ?, ?)";

    private static final String GET_ADDRESS =
            "SELECT * FROM Addresses WHERE id=?";

    private static final String GET_ALL_ADDRESSES =
            "SELECT * FROM Addresses";

    private static final String UPDATE_ADDRESS =
            "UPDATE Addresses" +
                    "SET city, street, number, stations_id WHERE id=?";

    private static final String DELETE_ADDRESS =
            "DELETE Addresses" +
                    "WHERE id=?";

    @Override
    public Address get(Long id) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(GET_ADDRESS);
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

    public Address extractFromResultSet(ResultSet resultSet) throws SQLException {
        Address address = new Address();

        address.setId(resultSet.getLong("id"));
        address.setStreet(resultSet.getString("street"));
        address.setCity(resultSet.getString("city"));
        address.setNumber(resultSet.getInt("number"));

        return address;
    }

    @Override
    public List<Address> getAll() {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(GET_ALL_ADDRESSES);
            ResultSet resultSet = ps.executeQuery();
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
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
        return null;
    }

    public void insert(Address address, Long stationId){
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(INSERT_ADDRESS);
            ps.setString(1, address.getCity());
            ps.setString(2, address.getStreet());
            ps.setInt(3, address.getNumber());
            ps.setLong(4, stationId);
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

    public void update(Address address, Long stationId) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(UPDATE_ADDRESS);
            ps.setString(1, address.getCity());
            ps.setString(2, address.getStreet());
            ps.setInt(3, address.getNumber());
            ps.setLong(4, stationId);
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
            PreparedStatement ps = connection.prepareStatement(DELETE_ADDRESS);
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
