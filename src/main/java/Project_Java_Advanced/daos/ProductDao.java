package Project_Java_Advanced.daos;

import Project_Java_Advanced.utils.ConnectionUtil;
import Project_Java_Advanced.entities.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class ProductDao implements CRUD<Product> {

    private Connection connection;
    private static final Logger log=Logger.getLogger(ProductDao.class);
    public static final String select_products="select * from products";
    public static final String select_by_id="select * from products where id=?";
    public static final String insert="insert into products(product_name,product_description,price) values(?,?,?)";
    public static final String delete="delete from products where id=?";
    public static final String update="update products set product_name=?,product_description=?,price=? where id=?";

    public ProductDao() {
        this.connection = ConnectionUtil.getConnection();
    }


    @Override
    public Product selectById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select_by_id);
            preparedStatement.setObject(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return Product.of(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error");
        }
    }

    @Override
    public List<Product> selectAll() {
        try {
            Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(select_products);

        List<Product> products= new ArrayList<>();

        while (resultSet.next()){
            products.add(Product.of(resultSet));
        }
        return products;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error selecting");
        }
    }

    @Override
    public Product insert(Product product) {

        String msg=String.format("Will be inserted product with name=%s,description=%s and " +
                "price=%f",product.getName(),product.getDescription(),product.getPrice());
        log.debug(msg);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setObject(1,product.getName());
        preparedStatement.setObject(2,product.getDescription());
        preparedStatement.setObject(3,product.getPrice());

        preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

        generatedKeys.next();

        product.setId(generatedKeys.getInt(1));

        return product;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error insering");
        }
    }

    @Override
    public void update(Product product) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setObject(1,product.getName());
            preparedStatement.setObject(2,product.getDescription());
            preparedStatement.setObject(3,product.getPrice());
            preparedStatement.setObject(4,product.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Update error");
        }
    }

    @Override
    public void delete(int id){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setObject(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            String msg=String.format("Error deleting product from database with id=%d",id);
            log.error(msg,e);
        }
    }
}
