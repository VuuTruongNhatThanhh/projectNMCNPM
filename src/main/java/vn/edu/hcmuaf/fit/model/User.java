package vn.edu.hcmuaf.fit.model;

import vn.edu.hcmuaf.fit.Dao.ShipmentDetailDao;
import vn.edu.hcmuaf.fit.database.DBConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class User {
    private String id;
    private String email;
    private String password;
    private String name;
    private int roleId;
    private String myHash;
    private int activate;
    private String date_sendmail_verify;




    public User(String id, String email, String password, String name, int roleId, String myHash, int activate, String date_sendmail_verify) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.roleId = roleId;
        this.myHash = myHash;
        this.activate = activate;
        this.date_sendmail_verify = date_sendmail_verify;
    }

    public User(String id, String email, String password, String name, int roleId, String myHash) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.roleId = roleId;
        this.myHash = myHash;

    }

    public User() {

    }

    public User(String name, String newpass, int roleId) {
    }





    public String getMyHash() {
        return myHash;
    }

    public void setMyHash(String myHash) {
        this.myHash = myHash;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public ShipmentDetails getShip() {
        return ShipmentDetailDao.getInstance().get(this.id);
    }


    public int getActivate() {
        return activate;
    }


    public void setActivate(int activate) {
        this.activate = activate;
    }

    public String getDate_sendmail_verify() {
        return date_sendmail_verify;
    }

    public void setDate_sendmail_verify(String date_sendmail_verify) {
        this.date_sendmail_verify = date_sendmail_verify;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", roleId=" + roleId +
                ", myHash='" + myHash + '\'' +
                ", activate=" + activate +
                ", date_sendmail_verify='" + date_sendmail_verify + '\'' +
                '}';
    }
    public static User getAccout(String email, String pass) {
        List<User> res = new LinkedList<>();
        try {
//            7. Kết nối csdl bảng taikhoan
            PreparedStatement ps = DBConnect.getInstance().get("select * from taikhoan where EMAIL=? AND MK =?");
            ps.setString(1, email);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                res.add(new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getInt(7), rs.getString(8)));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (res.size() == 0) return null;
        return  res.get(0);
    }

}