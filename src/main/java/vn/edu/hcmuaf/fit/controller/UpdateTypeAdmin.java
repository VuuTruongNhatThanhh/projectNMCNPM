package vn.edu.hcmuaf.fit.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UpdateTypeAdmin", value = "/UpdateTypeAdmin")
public class UpdateTypeAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<TypeProduct> types = TypeProductDao.getInstance().getAll();
        String id = request.getParameter("id");
        String idType = request.getParameter("idType");

        request.setAttribute("id", id);
        request.setAttribute("idType", idType);
        request.setAttribute("types", types);
        request.setAttribute("action", "UpdateTypeAdmin");
        request.setAttribute("title", "Sửa loại sản phẩm");

        request.getRequestDispatcher("AdminWeb/addType.jsp").forward(request, response);    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String idType = request.getParameter("typeFather");

        TypeProductDao.getInstance().update(id, name,idType);
        response.sendRedirect("/TypeAdmin");

    }
}
