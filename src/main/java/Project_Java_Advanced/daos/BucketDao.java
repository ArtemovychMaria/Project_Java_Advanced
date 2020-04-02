package Project_Java_Advanced.daos;

import Project_Java_Advanced.EntityManagerUtils;
import Project_Java_Advanced.entities.Bucket;
import Project_Java_Advanced.entities.Product;
import Project_Java_Advanced.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;

public class BucketDao implements CRUD <Bucket> {
    private Connection connection;
    private static final Logger log = Logger.getLogger(BucketDao.class);
    public static final String select_buckets = "select * from buckets";
    public static final String select_by_id = "select * from buckets where id=?";
    public static final String select_all_by_user_id = "select b from Bucket b where b.userId = :userId";
    public static final String insert = "insert into bucket(user_id,product_id,purchase_date) values(?,?,?)";
    public static final String delete = "delete from bucket where id=?";
    public static final String update = "update buckets set user_id=?,product_id=?,purchase_date=? where id=?";

    public BucketDao() {
//        this.connection = ConnectionUtil.getConnection();
    }

    @Override
    public Bucket selectById(int id) {

        EntityManager entityManager = EntityManagerUtils.getEntityManager();
        return entityManager.find(Bucket.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Bucket> getAllByUserId(int userId) {
        EntityManager entityManager = EntityManagerUtils.getEntityManager();
        return entityManager.createQuery(select_all_by_user_id)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<Bucket> selectAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(select_buckets);

            List<Bucket> buckets = new ArrayList<>();

            while (resultSet.next()) {
                buckets.add(Bucket.of(resultSet));
            }
            return buckets;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error selecting");
        }
    }


    @Override
    public Bucket insert(Bucket bucket) {

        String msg = String.format("Will be inserted bucket with " +
                "userId=%d and productId=%d", bucket.getUserId(), bucket.getProductId());
        log.debug(msg);

        EntityManager entityManager = EntityManagerUtils.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(bucket);
        entityManager.getTransaction().commit();

        return bucket;
    }

    @Override
    public void update(Bucket bucket) {
        String msg = String.format("Will be updated bucket with id=%d", bucket.getId());
        log.debug(msg);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setObject(1, bucket.getUserId());
            preparedStatement.setObject(2, bucket.getProductId());
            preparedStatement.setObject(3, bucket.getPurchaseDate());
            preparedStatement.setObject(4, bucket.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Update error");
        }
    }

    @Override
    public void delete(int id) {

        EntityManager entityManager = EntityManagerUtils.getEntityManager();
        entityManager.getTransaction().begin();
        Bucket bucket = entityManager.find(Bucket.class, id);
        entityManager.remove(bucket);
        entityManager.flush();
        entityManager.getTransaction().commit();
    }
}
