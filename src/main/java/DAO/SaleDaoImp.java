package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import models.Sale;
import services.DatabaseConnection;

public class SaleDaoImp implements Dao<Sale> {

    @Override
    public Optional<Sale> get(long id) {
        String query = "SELECT * FROM sale WHERE id = ?";
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
             
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                Sale sale = new Sale(
                        resultSet.getInt("id"),
                        resultSet.getInt("product_id"),
                        resultSet.getInt("quantity"),
                        resultSet.getDouble("total_price"),
                        resultSet.getString("sale_date"));
                return Optional.of(sale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Sale> getAll() {
        List<Sale> sales = new ArrayList<>();
        String query = "SELECT * FROM sale";
        
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
             
            while (resultSet.next()) {
                Sale sale = new Sale(
                        resultSet.getInt("id"),
                        resultSet.getInt("product_id"),
                        resultSet.getInt("quantity"),
                        resultSet.getDouble("total_price"),
                        resultSet.getString("sale_date"));
                sales.add(sale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sales;
    }
    
    public List<Sale> getMostRecentSales() {
        List<Sale> sales = new ArrayList<>();
        String query = "SELECT * FROM sale ORDER BY saleDate DESC LIMIT 8"; // Fetch the top 8 most recent sales

        
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
             
            while (resultSet.next()) {
                Sale sale = new Sale(
                        resultSet.getInt("id"),
                        resultSet.getInt("productId"),
                        resultSet.getInt("quantity"),
                        resultSet.getDouble("totalPrice"),
                        resultSet.getString("saleDate"));
                sales.add(sale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sales;
    }

    @Override
    public void save(Sale sale) {
        String query = "INSERT INTO sale (product_id, quantity, total_price, sale_date) VALUES (?, ?, ?, ?)";
        
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
             
            statement.setInt(1, sale.getProductId());
            statement.setInt(2, sale.getQuantity());
            statement.setDouble(3, sale.getTotalPrice());
            statement.setString(4, sale.getSaleDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Sale sale) {
        String query = "UPDATE sale SET product_id = ?, quantity = ?, total_price = ?, sale_date = ? WHERE id = ?";
        
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
             
            statement.setInt(1, sale.getProductId());
            statement.setInt(2, sale.getQuantity());
            statement.setDouble(3, sale.getTotalPrice());
            statement.setString(4, sale.getSaleDate());
            statement.setInt(5, sale.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM sale WHERE id = ?";
        
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
             
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
