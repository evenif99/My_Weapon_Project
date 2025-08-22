package com.itgroup.dao;

import com.itgroup.DataManager;
import com.itgroup.bean.Tanks;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TanksDao extends SuperDao{
    public TanksDao() {
    }

    public List<Tanks> searchMenu() {
        List<Tanks> tanks = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = null;

        String sql = "select * from tanks order by wname asc";

        try{
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()){
                Tanks bean = new Tanks();
                bean.setWid(rs.getString("wid"));
                bean.setWname(rs.getString("wname"));
                bean.setCp(rs.getString("cp"));
                bean.setPrice(rs.getInt("price"));
                bean.setReleasedate(rs.getString("releasedate"));
                bean.setCalibers(rs.getString("calibers"));

                tanks.add(bean);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
                e.printStackTrace();
            }
        }
        return tanks;
    }

    public int insertWMenu(Tanks bean) {
        int cnt = -1;

        String sql = "insert into tanks(wid, wname, cp, price, releasedate, calibers)";
        sql += "values(?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, bean.getWid());
            pstmt.setString(2, bean.getWname());
            pstmt.setString(3, bean.getCp());
            pstmt.setInt(4, bean.getPrice());
            pstmt.setString(5, bean.getReleasedate());
            pstmt.setString(6, bean.getCalibers());

            cnt = pstmt.executeUpdate();

            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try{
                conn.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }finally {
            try {
                if(pstmt != null){pstmt.close();}
                if (conn != null){conn.close();}
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        return cnt;
    }

    public Tanks searchInfo(String wid) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Tanks bean = null;

        String sql = "select * from tanks where wid = ?";

        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, wid);
            rs= pstmt.executeQuery();

            if (rs.next()){
                bean = new Tanks();
                bean.setWid(rs.getString("wid"));
                bean.setWname(rs.getString("wname"));
                bean.setCp(rs.getString("cp"));
                bean.setPrice(rs.getInt("price"));
                bean.setReleasedate(rs.getString("releasedate"));
                bean.setCalibers(rs.getString("calibers"));
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        return bean;
    }

    public int updateWMenu(Tanks bean) {
        int cnt = -1;

        String sql = "update tanks set wname = ?, cp = ?, price = ?, calibers =? ";
        sql += "where wid = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, bean.getWname());
            pstmt.setString(2, bean.getCp());
            pstmt.setInt(3, bean.getPrice());
            pstmt.setString(4, bean.getCalibers());
            pstmt.setString(5, bean.getWid());

            cnt = pstmt.executeUpdate();

            conn.commit();

        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return cnt;
    }
}
