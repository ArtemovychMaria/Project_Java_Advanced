package Project_Java_Advanced.services;



import Project_Java_Advanced.daos.ProductDao;
import Project_Java_Advanced.entities.Product;

import java.util.List;

public class ProductService {

    private ProductDao productDao;
    private static ProductService productService;

    private ProductService(){

        this.productDao=new ProductDao();
    }

    public static ProductService getProductService(){
        if(productService==null) {
            productService=new ProductService();
        }
        return productService;
    }

    public Product insert(Product product){
        return productDao.insert(product);
    }

    public Product selectByID(int id){
        return productDao.selectById(id);
    }

    public List<Product> selectAll(){
        return productDao.selectAll();
    }

    public void update(Product product){

        productDao.update(product);
    }

    public void  delete(int id){
        productDao.delete(id);
    }
}
