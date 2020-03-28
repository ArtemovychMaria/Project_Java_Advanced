package Project_Java_Advanced.servlets.controllers;

import Project_Java_Advanced.dtos.BucketProductDto;
import Project_Java_Advanced.entities.Bucket;
import Project_Java_Advanced.entities.Product;
import Project_Java_Advanced.services.BucketService;
import Project_Java_Advanced.services.ProductService;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@WebServlet("/api/buckets")
public class BucketController extends HttpServlet {

    private BucketService bucketService = BucketService.getBucketService();
    private ProductService productService = ProductService.getProductService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String productId = req.getParameter("productId");
        int userId = (int) req.getSession().getAttribute("userId");

        bucketService.insert(userId,Integer.parseInt(productId),new Date());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        int userId = (int) req.getSession().getAttribute("userId");

        List<Bucket> buckets = bucketService.getAllByUserId(userId);

        Set<Integer> productIds = buckets.stream()
                .map(Bucket::getProductId)
                .collect(Collectors.toSet());

        Map<Integer, Product> productsGroupedById = productService.getByIds(productIds)
                .stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));

        List<BucketProductDto> result = buckets.stream()
                .map(bucket -> {
                    BucketProductDto dto = new BucketProductDto();
                    dto.id = bucket.getId();
                    dto.purchaseDate = bucket.getPurchaseDate();
                    int productId = bucket.getProductId();

                    dto.product = productsGroupedById.get(productId);
                    return dto;
                })
                .collect(Collectors.toList());

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int bucketId = Integer.parseInt(req.getParameter("bucketId"));
        int userId = (int) req.getSession().getAttribute("userId");


        Bucket bucket = bucketService.getByID(bucketId);

        if (bucket.getUserId() == userId) {
            bucketService.delete(bucketId);
        } else {
            resp.getWriter().write("No no no it's not your bucket, will charge you 100$");
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

    }
}
