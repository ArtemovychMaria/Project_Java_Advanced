package Project_Java_Advanced.services;


import Project_Java_Advanced.daos.BucketDao;
import Project_Java_Advanced.entities.Bucket;

import java.util.Date;
import java.util.List;

public class BucketService {

    private BucketDao bucketDao;
    private static BucketService bucketService;

    public BucketService(){
        bucketDao=new BucketDao();
    }

    public static BucketService getBucketService(){
        if(bucketService==null){
            bucketService=new BucketService();
        }
        return bucketService;
    }

    public Bucket insert(int userId, int productId, Date purchaseDate){
        return bucketDao.insert(Bucket.builder()
        .setUserId(userId)
        .setProductId(productId)
        .setPurchaseDate(purchaseDate)
        .build());
    }

    public Bucket getByID(int id){
        return bucketDao.selectById(id);
    }

    public List<Bucket> selectAll(){
        return bucketDao.selectAll();
    }

    public void update(Bucket bucket){

        bucketDao.update(bucket);
    }

    public void  delete(int id){
        bucketDao.delete(id);
    }
}
