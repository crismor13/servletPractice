package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import models.AccountingRecord;
import services.DatabaseConnection;

public class AccountingRecordDaoImp implements Dao<AccountingRecord>{
	@Override
    public Optional<AccountingRecord> get(long id) {
        String query = "SELECT * FROM accounting_record WHERE id = ?";
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
             
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                AccountingRecord record = new AccountingRecord(
                        resultSet.getInt("id"),
                        resultSet.getString("date"),
                        resultSet.getString("description"),
                        resultSet.getDouble("amount"),
                        resultSet.getString("type"));
                return Optional.of(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<AccountingRecord> getAll() {
        List<AccountingRecord> records = new ArrayList<>();
        String query = "SELECT * FROM accounting_record";
        
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
             
            while (resultSet.next()) {
                AccountingRecord record = new AccountingRecord(
                        resultSet.getInt("id"),
                        resultSet.getString("date"),
                        resultSet.getString("description"),
                        resultSet.getDouble("amount"),
                        resultSet.getString("type"));
                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    @Override
    public void save(AccountingRecord record) {
        String query = "INSERT INTO accounting_record (date, description, amount, type) VALUES (?, ?, ?, ?)";
        
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
             
            statement.setString(1, record.getDate());
            statement.setString(2, record.getDescription());
            statement.setDouble(3, record.getAmount());
            statement.setString(4, record.getType());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(AccountingRecord record) {
        String query = "UPDATE accounting_record SET date = ?, description = ?, amount = ?, type = ? WHERE id = ?";
        
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
             
            statement.setString(1, record.getDate());
            statement.setString(2, record.getDescription());
            statement.setDouble(3, record.getAmount());
            statement.setString(4, record.getType());
            statement.setInt(5, record.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM accounting_record WHERE id = ?";
        
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
             
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
