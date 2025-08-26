package com.itgroup.dao;

import com.itgroup.bean.Customers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomersDao extends SuperDao{
    public List<Customers> sellCountry() {
        List<Customers> customers = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = null;

        String sql = "select country from customers";

        try{
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()){
                Customers bean = new Customers();
                bean.setCountry(rs.getString("country"));

                customers.add(bean);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return customers;
    }

    public Customers checkSellamount(String cid) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Customers bean = null;

        String sql = "select ordervolume from customers where cid = ?";

        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cid.toUpperCase());
            rs = pstmt.executeQuery();
            if (rs.next()){
                bean = new Customers();
                bean.setOrdervolume(rs.getInt("ordervolume"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
            return bean;
    }

    public void availablePurchase(String cid, String tankWid, int amount) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // tanks와 customers 조인해서 budget과 tank price 가져오기
        String sql = "SELECT c.cid, c.country, c.budget, t.price, t.amount " +
                "FROM customers c, tanks t " +
                "WHERE c.cid = ? AND t.wid = ?";

        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cid);
            pstmt.setString(2, tankWid);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                String country = rs.getString("country");
                int budget = rs.getInt("budget");
                int price = rs.getInt("price");
                int stock = rs.getInt("amount");

                int totalCost = price * amount;

                System.out.println("국가: " + country + ", 전차: " + tankWid);
                if (budget < totalCost) {
                    System.out.println("구매 불가능 (예산 부족)");
                } else if (stock < amount) {
                    System.out.println("구매 불가능 (재고 부족)");
                } else {
                    System.out.println("구매 가능 (" + amount + "대 구매 가능)");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public List<String> preferWeapon(String cid) {
        List<String> preferredTanks = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "";

        try {
            conn = super.getConnection();

            switch (cid.toUpperCase()) {
                case "CAN":
                case "PL":
                    sql = "SELECT wname FROM tanks WHERE calibers >= 'L/55'";
                    break;
                case "IND":
                case "PKS":
                    sql = "SELECT wname FROM tanks WHERE calibers = 'L/44'";
                    break;
                case "RUS":
                    sql = "SELECT wname FROM tanks WHERE wname = 'T14'";
                    break;
                default:
                    return preferredTanks; // 국가 없으면 빈 리스트 반환
            }

            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                preferredTanks.add(rs.getString("wname"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return preferredTanks; // List<String> 반환
    }
}
