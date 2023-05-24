package vn.edu.hcmuaf.fit.controller;




import vn.edu.hcmuaf.fit.Dao.ProductDao;
import vn.edu.hcmuaf.fit.model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
// 5. Người dụng chọn chức năng xóa sản phẩm theo tên hoặc mã sản phẩm
// 6a. Hệ thống tiến hành tìm sản phẩm
// 6a.1.Hệ thống tiến hành xóa sản phẩm
// 6a.2. Hệ thống thông báo sản phẩm không tồn tại
@WebServlet(name = "RemoveProductAdmin", value = "/RemoveProductAdmin")
public class RemoveProductAdmin extends HttpServlet {
    private static  String name = "product";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //3. Yêu cầu lấy thông tin sản phẩm
        String idP = request.getParameter("idP");

        try {
            //4. Kiểm tra mã sản phẩm
            Product p = ProductDao.getInstance().getProductById(idP);
            //5.2. Thông báo không tìm thấy sản phẩm
            if (p == null) {
                //6.2. Thông báo không tìm thấy sản phẩm
                request.setAttribute("message", "Không tìm thấy sản phẩm");
                response.sendRedirect("/ProductAdmin");
                //5.1. Thông báo  tìm thấy sản phẩm
            } else {
                //6.1. Thực hiện xóa sản phẩm và hiển thị kết quả
                ProductDao.getInstance().delete(idP);

                PrintWriter out = response.getWriter();
                if (p != null) {
                    out.println(
                            "                                <th scope=\"row\">" + p.getId() + "</th>\n" +
                                    "                                <td>\n" +
                                    "                                    <a>Hiện có " + p.getPics().size() + " ảnh</a>\n" +
                                    "                                </td>\n" +
                                    "                                <td>" + p.getName() + "</td>\n" +
                                    "                                <td><a>Hiện có " + p.getWeights().size() + " khối lượng</a></td>\n" +
                                    "                                <td>" + p.getDiscount() + "<span>%</span></td>\n" +
                                    "                                   <td>" + p.getDescribe() + "</td>\n" +
                                    "                                <td>" + p.getDate() + "</td>\n" +
                                    getStringCondition(p.isDelete()) +
                                    "                                <td>" + p.getNameType() + "</td>\n" +
                                    "                                <td>\n" +
                                    "                                    <button onclick=\"removeP('" + p.getId() + "')\" class=\"btn btn-primary btn-sm trash\"\n" +
                                    "                                            type=\"button\" title=\"Xóa\">\n" +
                                    "                                        <i class=\"fas fa-trash-alt\"></i>\n" +
                                    "                                    </button>\n" +
                                    "                                    <button class=\"btn btn-primary btn-sm edit\" type=\"button\" title=\"Sửa\"\n" +
                                    "                                            data-toggle=\"modal\" data-target=\"#ModalUP\">\n" +
                                    "                           <a style=\"color: white;\"  href=\"UpdateProductAdmin?id=" + p.getId() + "&idType=" + p.getIdType() + "\"><i class=\"fas fa-edit\"></i></a>\n" +
                                    "                                    </button>\n" +
                                    "                                </td>\n");
                } else {
                    out.println("");
                }
                request.setAttribute("message", "Xóa thành công");
                response.sendRedirect("/ProductAdmin");
            }
        } catch (Exception e) {

        }
    }
    public String getStringCondition(boolean check) {
        if (check)
            return "<td><span class=\"badge bg-success\">Còn bán</span></td>\n";
        return "<td><span class=\"badge bg-danger\">Ngừng bán</span></td>\n";

    }
}
