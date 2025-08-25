package com.itgroup.dao;

import com.itgroup.bean.Tanks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TanksDao extends SuperDao {
    public TanksDao() {
    }

    public List<Tanks> searchMenu() {
        List<Tanks> tanks = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = null;

        String sql = "select * from tanks order by wname asc";

        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
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
            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        } finally {
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
            try {
                conn.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return cnt;
    }

    public Tanks searchInfo(String wid) {
        int cnt = -1; // 기본값 (없을 경우 구분하기 위해 -1로 설정)
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Tanks bean = null;

        String sql = "select * from tanks where wid = ?";

        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, wid);

            rs = pstmt.executeQuery();

            if (rs.next()) {  // wid는 PK라서 한 건만 나옴
                bean = new Tanks();
                bean.setWid(rs.getString("wid"));
                bean.setWname(rs.getString("wname"));
                bean.setCp(rs.getString("cp"));
                bean.setPrice(rs.getInt("price"));
                bean.setReleasedate(rs.getString("releasedate"));
                bean.setCalibers(rs.getString("calibers"));
                bean.setAmount(rs.getInt("amount"));
            }
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
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
            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        } finally {
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

    public int checkAmount(String wid) {
        int amount = -1; // 기본값 (없을 경우 구분하기 위해 -1로 설정)
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "select amount from tanks where wid = ?";

        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, wid);

            rs = pstmt.executeQuery();

            if (rs.next()) {  // wid는 PK라서 한 건만 나옴
                amount = rs.getInt("amount");
            }
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return amount;

    }

    public int deleteWInfo(String wid) {
        int cnt = -1;
        String sql = "delete from tanks where wid = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, wid);

            cnt = pstmt.executeUpdate();

            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
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