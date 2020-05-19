package com.solvd.spaceCompany.daos;

import com.solvd.spaceCompany.ConnectionPool;
import com.solvd.spaceCompany.daos.interfaces.IDAO;
import com.solvd.spaceCompany.models.SpaceCompany;
import com.solvd.spaceCompany.models.Station;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StationDAO implements IDAO<Station> {
    private final Logger LOGGER = LogManager.getLogger(StationDAO.class);

    private static final String INSERT_STATION =
            "INSERT INTO Stations (name, space_company_id) " +
                    "VALUES (?, ?)";

    private static final String GET_STATION =
            "SELECT * FROM Stations WHERE id=?";

    private static final String GET_ALL_STATIONS =
            "SELECT * FROM Stations";

    private static final String UPDATE_STATION =
            "UPDATE Stations" +
                    "SET name, space_company_id WHERE id=?";

    private static final String DELETE_STATION =
            "DELETE Stations" +
                    "WHERE id=?";

    @Override
    public Station get(Long id) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(GET_STATION);
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

    public Station extractFromResultSet(ResultSet resultSet) throws SQLException {
        Station station = new Station();

        station.setId(resultSet.getLong("id"));
        station.setName(resultSet.getString("name"));

        return station;
    }

    @Override
    public List<Station> getAll() {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(GET_ALL_STATIONS);
            ResultSet resultSet = ps.executeQuery();
            List<Station> stations = new ArrayList<>();

            while (resultSet.next()) {
                Station station = extractFromResultSet(resultSet);
                stations.add(station);
            }
            return stations;
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
    public void insert(Station station, Long spaceCompanyId) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(INSERT_STATION);
            ps.setString(1, station.getName());
            ps.setLong(2, spaceCompanyId);

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
    public void update(Station station, Long spaceCompanyId) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(UPDATE_STATION);
            ps.setString(1, station.getName());
            ps.setLong(2, spaceCompanyId);
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
            PreparedStatement ps = connection.prepareStatement(DELETE_STATION);
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
