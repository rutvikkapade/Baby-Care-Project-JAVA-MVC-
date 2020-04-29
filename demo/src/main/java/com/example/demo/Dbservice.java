package com.example.demo;

import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Service
public class Dbservice<list> {
    String url= System.getenv("DBHOSTNAME");
    String user=System.getenv("DBUSERNAME");
    String pass=System.getenv("DBPASSWORD");
    String schema=System.getenv("DBSCHEMA");
    public void addValues(String fname,String lname,String email,String password){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dburl="jdbc:mysql://"+url+":3306/"+schema;
            Connection con=DriverManager.getConnection(dburl,user,pass);
            Statement stmt=con.createStatement();
            Random rand = new Random();
            int randin = rand.nextInt(8999)+1000;
            String num=Integer.toString(randin);
            String id=String.join("",fname,num);
            String sql="insert into user values"+"('"+id+"','"+fname+"','"+lname+"','"+email+"','"+password+"',"+0+");";
            stmt.executeUpdate(sql);
            String sql2="select * from user;";
            ResultSet rs=stmt.executeQuery(sql2);
            while(rs.next()){
                System.out.println(

                );
            }
            con.close();
        }catch(Exception e){
            System.out.println(e);
            System.out.println("connection not established!");
        }
    }
    public int auth(String email,String Password){
        int m=0;
        try{
            m=0;
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dburl="jdbc:mysql://"+url+":3306/"+schema;
            Connection con=DriverManager.getConnection(dburl,user,pass);
            Statement stmt=con.createStatement();
            String sql="SELECT password FROM USER WHERE email='"+email+"'";
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
                String s=rs.getString("password").trim();
                Password=Password.trim();
                if(s.equals(Password)){
                    m=1;
                }
            }
            con.close();

    }catch(Exception e){
            System.out.println(e);
        }
        return m;
    }
    public String getIdd(String email){
        String s="oo";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dburl="jdbc:mysql://"+url+":3306/"+schema;
            Connection con=DriverManager.getConnection(dburl,user,pass);
            Statement stmt=con.createStatement();
            String sql= "select id from user where email='"+email+"';";
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
                s=rs.getString("id").trim();
            }
            con.close();

        }catch (Exception e){
            System.out.println(e);
        }
        return s;
    }
    public int addBname(String bname,String religion,String Location,String email){
        int m=0;
        try{
            String sql=null;
            int k=0;
            String idd = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dburl="jdbc:mysql://"+url+":3306/"+schema;
            Connection con=DriverManager.getConnection(dburl,user,pass);
            Statement stmt=con.createStatement();
            sql="select have from user where email='"+email+"';";
            ResultSet rs=stmt.executeQuery(sql);
            while (rs.next()){
                k=rs.getInt("have");
            }if(k==0){
                sql="update user set have=1 where email='"+email+"';";
                stmt.executeUpdate(sql);
                Random rand = new Random();
                int randin = rand.nextInt(899)+100;
                String num=Integer.toString(randin);
                String id=String.join("",bname,num);
                sql="select id from user where email='"+email+"';";
                rs=stmt.executeQuery(sql);
                while(rs.next()){
                    idd=rs.getString("id");
                }
                sql="create table "+idd+" (nameId varchar(255),name varchar(255),religion varchar(255),location varchar(255),PRIMARY KEY(name));";
                stmt.execute(sql);
                sql="insert into "+idd+" values('"+id+"','"+bname+"','"+religion+"','"+Location+"');";
                stmt.execute(sql);
                sql="insert into allnames values('"+idd+"','"+bname+"','"+religion+"','"+Location+"');";
                stmt.execute(sql);
                con.close();
                m=1;
            }else{
                sql="select id from user where email='"+email+"';";
                rs=stmt.executeQuery(sql);
                while(rs.next()){
                    idd=rs.getString("id");
                }
                Random rand = new Random();
                int randin = rand.nextInt(8999)+1000;
                String num=Integer.toString(randin);
                String id=String.join("",bname,num);
                sql="insert into "+idd+" values('"+id+"','"+bname+"','"+religion+"','"+Location+"');";
                stmt.execute(sql);
                sql="insert into allnames values('"+idd+"','"+bname+"','"+religion+"','"+Location+"');";
                stmt.execute(sql);
                con.close();
                m=1;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return m;
    }

    public ResultSet resultset(String email) {
        ResultSet rs = null;
        String idd=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dburl="jdbc:mysql://"+url+":3306/"+schema;
            Connection con=DriverManager.getConnection(dburl,user,pass);
            Statement stmt=con.createStatement();
            String sql="SELECT id FROM user where email='"+email+"';";
            rs=stmt.executeQuery(sql);
            while (rs.next()){
                idd=rs.getString("id");
            }
            sql="Select * from "+idd+";";
            rs=stmt.executeQuery(sql);
        }catch(Exception e){
            System.out.println(e);
        }
        return rs;
    }

    public List<Baby> createList(ResultSet resultSet) throws SQLException {
        List<Baby> list = new ArrayList<Baby>();
        while (resultSet.next()) {
            Baby baby= new Baby();
            baby.setId(resultSet.getString("nameId"));
            baby.setName(resultSet.getString("name"));
            baby.setReligion(resultSet.getString("religion"));
            baby.setLocation(resultSet.getString("location"));
            list.add(baby);
            }
        return list;
    }

    public List<Baby> createList1(ResultSet resultSet) throws SQLException {
        List<Baby> list = new ArrayList<Baby>();
        while (resultSet.next()) {
            Baby baby= new Baby();
            baby.setId(resultSet.getString("id"));
            baby.setName(resultSet.getString("BabyName"));
            baby.setReligion(resultSet.getString("religion"));
            baby.setLocation(resultSet.getString("location"));
            list.add(baby);
        }
        return list;
    }

    public ResultSet resultset1() {
        ResultSet rs = null;
        String idd=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dburl="jdbc:mysql://"+url+":3306/"+schema;
            Connection con=DriverManager.getConnection(dburl,user,pass);
            Statement stmt=con.createStatement();
            String sql="SELECT * FROM allnames;";
            rs=stmt.executeQuery(sql);
        }catch(Exception e){
            System.out.println(e);
        }
        return rs;
    }
}
