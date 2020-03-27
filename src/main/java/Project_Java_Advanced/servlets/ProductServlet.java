package Project_Java_Advanced.servlets;

import Project_Java_Advanced.entities.Product;
import Project_Java_Advanced.services.ProductService;
import com.google.common.base.Strings;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    ProductService productService=ProductService.getProductService();

    // to get resource (product)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productId = request.getParameter("id");
        Product product = productService.getByID(Integer.parseInt(productId));
        request.setAttribute("productName", product.getName());
        request.setAttribute("productD", product.getDescription());
        request.setAttribute("productP", product.getPrice());
        request.setAttribute("productId", product.getId());

        request.getRequestDispatcher("productInfo.jsp").forward(request, response);

    }

    // to update resource (product)
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        super.doPut(req, resp);
    }

    // to delete resource (product)
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        super.doDelete(req, resp);
    }

}
