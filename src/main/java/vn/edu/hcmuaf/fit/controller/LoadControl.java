package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.ProductDao;
import vn.edu.hcmuaf.fit.Dao.UserDao;
import vn.edu.hcmuaf.fit.bean.Log;
import vn.edu.hcmuaf.fit.database.DB;
import vn.edu.hcmuaf.fit.model.Product;
import vn.edu.hcmuaf.fit.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoadControl", value = "/LoadControl")
public class LoadControl extends HttpServlet {
    String name="discount-product";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> list = ProductDao.getInstance().getLast();
        List<Product> listd = ProductDao.getInstance().getDiscount();
        List<Product> listh = ProductDao.getInstance().getHot();


        request.setAttribute("listL", list);
        request.setAttribute("listD",listd);
        request.setAttribute("listH",listh);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
