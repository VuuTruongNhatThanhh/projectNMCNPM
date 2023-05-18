package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.bean.Log;
import vn.edu.hcmuaf.fit.database.DB;
import vn.edu.hcmuaf.fit.model.User;
import vn.edu.hcmuaf.fit.services.SHA1;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Login", value = "/Login")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("username");
        String pass = request.getParameter("pass");
//        8. Kiểm tra email, password có tồn tại (gọi hàm getAccount)
        User user = User.getAccout(email, SHA1.hashPassword(pass));
//        8.1 Nếu thông tin không tồn tại hoặc sai
        if (user == null) {
            request.setAttribute("error", "Thông tin đăng nhập không chính xác");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
//        9. Nếu thông tin tồn tại lưu vào session
        else {
            HttpSession session = request.getSession(true);
            session.setAttribute("auth", user);
//            10. Trả về trang index và hiện tên người dùng ở header.jsp trong trang chủ(index)
            response.sendRedirect("LoadControl");

        }
    }
}