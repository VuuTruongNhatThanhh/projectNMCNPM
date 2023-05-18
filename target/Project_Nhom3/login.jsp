
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0">
    <meta charset="UTF-8">
    <title>Đăng nhập</title>

    <%--    <link rel="stylesheet" type="text/css" href="main.css"><link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700;800&display=swap" rel="stylesheet">--%>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css" type="text/css">
    <link rel="stylesheet" href="fontawesome-free-6.2.0-web/css/all.min.css">
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <link rel="stylesheet" href="css/login.css">
</head>
<body id="abc">

<%--2. Hiển thị trang đăng nhập--%>
<div class="main">
    <div class="container b-container" id="b-container">
        <%--        6. Gửi dữ liệu lên LoginController bằng action --%>
        <form class="form" id="b-form" method="post" action="Login">
            <h2 class="form_title title">Đăng nhập</h2>
            <%--            3. Nhập email và password--%>
            <input class="form__input" name="username" type="email" placeholder="Email" required>
            <input class="form__input" name="pass" type="password" id="password" placeholder="Mật khẩu" required>
            <a href="forgetpass.jsp" class="form__link">Quên mật khẩu?</a>
            <%--                 8.1 Nếu thông tin không tồn tại hoặc sai thì thông báo lỗi "Thông tin đăng nhập không chính xác"--%>

            <%
                String error = (String) request.getAttribute("error");
                error = (error == null) ? "" : error;
            %>
            <p style="color: red;font-size: 18px"><%=error%>
            </p>
            <%--            4. Nhấn nút Đăng nhập --%>
            <%--            5. Kiểm tra tính hợp lệ email, password bằng thuộc tính required (ở trên)--%>
            <input type="submit" class="form__button button submit user-link" value="ĐĂNG NHẬP">
        </form>

    </div>


    <div class="switch" id="switch-cnt">
        <div class="switch__circle"></div>
        <div class="switch__circle switch__circle--t"></div>
        <div class="switch__container" id="switch-c1">
            <h2 class="switch__title title">CHÀO MỪNG BẠN TRỞ LẠI !</h2>
            <p class="switch__description description">Bạn chưa có tài khoản? Hãy đăng ký ngay để có tài khoản miễn
                phí.</p>
            <a href="signup.jsp">
                <button class="switch__button button switch-btn">ĐĂNG KÝ</button>
            </a>
        </div>
    </div>
</div>

<script type="text/javascript">
</script>
<%--<% String user_name=(String)request.getParameter("user_name");--%>
<%--    String user_email=(String)request.getParameter("user_email"); %>--%>


<script src="bootstrap/js/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
<script src="js/main.js"></script>
<script src="js/login.js"></script>
</body>
</html>
