package vn.edu.hcmuaf.fit.controller;




import vn.edu.hcmuaf.fit.Dao.ProductDao;
import vn.edu.hcmuaf.fit.Dao.TypeProductDao;
import vn.edu.hcmuaf.fit.model.Product;
import vn.edu.hcmuaf.fit.model.TypeProduct;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;
import java.util.*;

@WebServlet(name = "AddProductAmin", value = "/AddProductAmin")
public class AddProductAmin extends HttpServlet {
    private static  String name = "product";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<TypeProduct> types = TypeProductDao.getInstance().getAll();
        request.setAttribute("id", ProductDao.getInstance().createId());
        request.setAttribute("types", types);
        request.setAttribute("action", "AddProductAmin");
        request.setAttribute("title", "Thêm sản phẩm");
        //3. Hiển thị chức năng thêm sản phẩm
        request.getRequestDispatcher("AdminWeb/addProduct.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String idType = request.getParameter("type");
        String dis = request.getParameter("dis");
        double discount = Integer.parseInt(dis);
        String dicription = request.getParameter("dicription_product");
        // 6. Gửi thông tin người dùng mới thêm vào
        // 7. Kiểm tra thông tin của database
        try {
            // 8. Thông báo kết quả thêm vào thành công
            ProductDao.getInstance().addDB(id, name, discount, dicription, idType);
            // 9. Hiển thị kết quả và quay về trang quản lí sản phẩm
            request.getSession().setAttribute("message", "Thêm thành công");
            List<Product> products = ProductDao.getInstance().getAll();
            request.setAttribute("products", products);

            request.getRequestDispatcher("AdminWeb/product.jsp").forward(request, response);
        } catch (Exception e) {
            // 8.1 Thông báo kết quả thêm vào thất bại
            request.setAttribute("message","Thật hiện không thành công");
            // 9.1 Hiển thị kết quả và quay về chức năng thêm sản phẩm
            request.getRequestDispatcher("AdminWeb/addProduct.jsp").forward(request, response);
        }



    }
}
