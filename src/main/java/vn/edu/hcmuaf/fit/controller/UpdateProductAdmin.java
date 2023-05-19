package vn.edu.hcmuaf.fit.controller;



import vn.edu.hcmuaf.fit.Dao.ProductDao;
import vn.edu.hcmuaf.fit.Dao.TypeProductDao;
import vn.edu.hcmuaf.fit.model.Product;
import vn.edu.hcmuaf.fit.model.TypeProduct;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UpdateProductAdmin", value = "/UpdateProductAdmin")
public class UpdateProductAdmin extends HttpServlet {
    private static  String name = "product";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<TypeProduct> types = TypeProductDao.getInstance().getAll();
        String id = request.getParameter("id");
        String idType = request.getParameter("idType");
        // 3. Yêu cầu lấy thông tin sản phẩm
        try {
            //4. Kiểm tra mã sản phẩm
            Product p = ProductDao.getInstance().getProductById(id);
            if(p == null) {
                //5.2. Thông báo không tìm thấy sản phẩm
                request.setAttribute("message","Không tìm thấy sản phẩm");
                List<Product> products = ProductDao.getInstance().getAll();
                request.setAttribute("products", products);
            } else {
                // 5.1. Gửi thông tin sản phẩm
                request.setAttribute("id", id);
                request.setAttribute("p", p);
                request.setAttribute("idType", idType);
                request.setAttribute("types", types);
                request.setAttribute("action", "UpdateProductAdmin");
                request.setAttribute("title", "Sửa sản phẩm");
                // 5.2 Hiển thị chức năng sửa sản phẩm

                request.getRequestDispatcher("AdminWeb/addProduct.jsp").forward(request, response);
            }


        } catch (Exception e) {

        }

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 9. Yêu cầu cập nhât lại thông tin
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String idType = request.getParameter("type");
        String dis = request.getParameter("dis");
        double discount = Integer.parseInt(dis);
        String dicription = request.getParameter("dicription_product");

        // 10. Kiểm tra thông tin
        try {

                // 11.1.Thông báo cập nhật thành công
             ProductDao.getInstance().update(id, name, discount, dicription, idType);
            //12.1 Thông báo thành công và quay lại trang quản lí sản phẩm
            request.setAttribute("message","Sửa thành công");
            List<Product> products = ProductDao.getInstance().getAll();
            request.setAttribute("products", products);
            request.getRequestDispatcher("AdminWeb/product.jsp").forward(request, response);
        } catch (Exception e) {
            // 11.2 Thông báo kết quả  thất bại
            request.setAttribute("message","Thực hiện không thành công");
            // 12.2 Hiển thị kết quả và quay về chức năng sủa sản phẩm
            response.sendRedirect("/UpdateProductAdmin");
        }
    }
}
