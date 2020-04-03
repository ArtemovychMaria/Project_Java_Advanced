package Project_Java_Advanced.daos;

import Project_Java_Advanced.EntityManagerUtils;
import Project_Java_Advanced.utils.ConnectionUtil;
import Project_Java_Advanced.entities.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import javax.persistence.EntityManager;

public class ProductDao implements CRUD<Product> {

    private Connection connection;
    private static final Logger log=Logger.getLogger(ProductDao.class);
    public static final String select_products="select p from Product p";
    public static final String select_by_id="select * from products where id=?";
    public static final String select_all_id_in="select p from Product p where p.id in (:productIds) ";
    public static final String insert="insert into products(product_name,product_description,price) values(?,?,?)";
    public static final String delete="delete from products where id=?";
    public static final String update="update products set product_name=?,product_description=?,price=? where id=?";

    public ProductDao() {
//        this.connection = ConnectionUtil.getConnection();
    }


    @Override
    public Product selectById(int id) {

        EntityManager entityManager=EntityManagerUtils.getEntityManager();
        return entityManager.find(Product.class,id);
    }

    @SuppressWarnings("unchecked")
    @Override
        public List<Product> selectAll() {
        EntityManager entityManager=EntityManagerUtils.getEntityManager();

        return entityManager.createQuery(select_products)
                .getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Product> getByIds(Set<Integer> productIds) {
        List<Product> productList=new ArrayList<>();
        EntityManager entityManager=EntityManagerUtils.getEntityManager();
        if(!productIds.isEmpty()) {
            productList = entityManager.createQuery(select_all_id_in)
                    .setParameter("productIds", productIds)
                    .getResultList();
        }

        return productList;
    }

    @Override
    public Product insert(Product product) {

        String msg=String.format("Will be inserted product with name=%s,description=%s and " +
                "price=%f",product.getName(),product.getDescription(),product.getPrice());
        log.debug(msg);

        EntityManager entityManager= EntityManagerUtils.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();

        return product;
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
