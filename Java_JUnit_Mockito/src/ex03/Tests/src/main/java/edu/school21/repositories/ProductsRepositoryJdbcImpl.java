package edu.school21.repositories;

import edu.school21.models.Product;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {
    private EmbeddedDatabase database;

    public ProductsRepositoryJdbcImpl(EmbeddedDatabase database) {
        this.database = database;
    }

    @Override
    public List<Product> findAll() {
        try {
            List<Product> productList = new ArrayList<>();
            Statement statement = database.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from product;");
            while (resultSet.next()){
                Product product = new Product(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("price")
                );
                productList.add(product);
            }
            return productList;
        }
        catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<Product> findById(Long id) {
        try {
            Statement statement = database.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from product where id = " + id + ";");
            if (resultSet.next()) {
                return Optional.of(new Product(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("price")
                ));
            }
            else {
                return Optional.empty();
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(Product product) {
        try {
            PreparedStatement statement = database.getConnection().prepareStatement(
                    "UPDATE product set name = ?, price = ? where id = ?;");
            statement.setString(1, product.getName());
            statement.setInt(2, product.getPrice());
            statement.setLong(3, product.getId());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Product product) {
        try {
            PreparedStatement statement = database.getConnection().prepareStatement(
                    "insert into product(name, price) values(?, ?);");
            statement.setString(1, product.getName());
            statement.setInt(2,  product.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            PreparedStatement statement = database.getConnection().prepareStatement(
                    "delete from product where id = ?;");
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
