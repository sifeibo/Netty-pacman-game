package Pacman.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
public class Jdbc {
    int x1,x2,y1,y2;
    Connection con = null;
    Statement statement = null;
    ResultSet res = null;
    String driver = "com.mysql.jdbc.Driver";
    String url  = "jdbc:mysql://localhost:3306/pacman";
    String name = "root";
    String passwd = "root";

    public Jdbc(){
        try{
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url,name,passwd);
            statement = con.createStatement();

        }catch(ClassNotFoundException e){
            System.out.println("对不起，找不到这个Driver");
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    //用户注册功能的实现，添加数据
    public int insert(String username,String password){
        String sql = "insert into user(username,password) values(\""+username+"\",\""+password+"\")";
        try{
            int a = statement.executeUpdate(sql);
            con.close();
            statement.close();
            return 1;
        }catch(SQLException e){
            e.printStackTrace();

            return 2;
        }
    }
    //对比用户名和密码是否匹配
    public int compare(String username,String password){
        int m = 0;
        String sql = "select password from user where username=\""+username+"\"";
        try{
            res = statement.executeQuery(sql);
            if(res.next()){
                String pa = res.getString(1);
                if(pa.equals(password)){
                    m = 1;
                }else {
                    m = 2;
                }
            }else {
                m = 3;
            }
            res.close();
            con.close();
            statement.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
        return m;
    }

    public int getX1(){
        String sql = "select x1 from game where id = 1";
        try {
            res = statement.executeQuery(sql);
            if (res.next()){
                x1 = res.getInt("x1");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return x1;
    }
    public int getX2(){
        String sql = "select x2 from game where id = 1";
        try {
            res = statement.executeQuery(sql);
            if (res.next()){
                x2 = res.getInt("x2");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return x2;
    }
    public int getY1(){
        String sql = "select y1 from game where id = 1";
        try {
            res = statement.executeQuery(sql);
            if(res.next()){
                y1 = res.getInt("y1");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return y1;
    }
    public int getY2(){
        String sql = "select y2 from game where id = 1";
        try {
            res = statement.executeQuery(sql);
            if (res.next()){
                y2 = res.getInt("y2");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return y2;
    }

    public void updatePost1(int x1,int y1){
        String sql = "update game  set x1 = "+x1+",y1 = "+y1+" where id = 1";
        try{
            int a = statement.executeUpdate(sql);
            if(a==1){
                System.out.println("更新位置");
            }
            con.close();
            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updatePost2(int x2,int y2){
        String sql = "update game  set x2 = "+x2+",y2 = "+y2+" where id = 1";
        try{
            int a = statement.executeUpdate(sql);
            if(a==1){
                System.out.println("更新位置");
            }
            con.close();
            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
