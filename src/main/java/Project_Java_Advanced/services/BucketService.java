package Project_Java_Advanced.services;


import Project_Java_Advanced.daos.BucketDao;
import Project_Java_Advanced.entities.Bucket;

import java.util.List;

public class BucketService {

    private BucketDao bucketDao;
    private BucketService bucketService;

    private BucketService(){

        this.bucketDao=new BucketDao();
    }

    public BucketService getUserService(){
        if(bucketService==null){
            bucketService=new BucketService();
        }
        return bucketService;
    }

    public Bucket insert(Bucket bucket){
        return bucketDao.insert(bucket);
    }

    public Bucket selectByID(int id){
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
