package com.itgroup;

import com.itgroup.bean.Customers;
import com.itgroup.bean.Tanks;
import com.itgroup.dao.CustomersDao;
import com.itgroup.dao.TanksDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

public class DataManager {
    private TanksDao tdao = null; // 실제 데이터 베이스와 연동하는 다오 클래스(생성자)
    private CustomersDao cdao = null;
    private Scanner scan = null; // 회원 정보 입력 받기 위한 스캐너 장치

    public DataManager() {
        this.tdao = new TanksDao();
        this.cdao = new CustomersDao();
        this.scan = new Scanner(System.in);
    }


    public void searchMenu() {
        List<Tanks> tanks = tdao.searchMenu();
        System.out.println("이름\t아이디\t생산국");
        for (Tanks bean : tanks) {
            String wname = bean.getWname();
            String wid = bean.getWid();
            String cp = bean.getCp();
            String message = wname + "\t" + wid + "\t" + cp;
            System.out.println(message);
        }
    }

    public void insertWMenu() {
        Tanks bean = new Tanks();
        int cnt;

        System.out.println("wid 입력 : ");
        String wid = scan.next();
        bean.setWid(wid);

        System.out.println("wname 입력 : ");
        String wname = scan.next();
        bean.setWname(wname);

        System.out.println("cp 입력 : ");
        String cp = scan.next();
        bean.setCp(cp);

        System.out.println("price 입력 : ");
        int price = scan.nextInt();
        bean.setPrice(price);

        System.out.println("releasedate 입력 : ");
        String releasedate = scan.next();
        bean.setReleasedate(releasedate);

        System.out.println("calibers 입력 : ");
        String calibers = scan.next();
        bean.setCalibers(calibers);

        cnt = tdao.insertWMenu(bean);

        if (cnt == -1) {
            System.out.println("정보 추가 실패");
        } else if (cnt == 1) {
            System.out.println("id " + wid + " 추가성공");
        } else {
        }
    }

    public void updateWMenu() {
        int cnt = -1;

        System.out.println("수정 대상 id :");
        String findWid = scan.next();

        Tanks bean = tdao.searchInfo(findWid);

        System.out.println("insert name : ");
        String wname = scan.next();

        System.out.println("insert cp : ");
        String cp = scan.next();

        System.out.println("insert price : ");
        int price = scan.nextInt();

        System.out.println("insert calibers : ");
        String calibers = scan.next();

        bean.setWname(wname);
        bean.setCp(cp);
        bean.setPrice(price);
        bean.setCalibers(calibers);

        cnt = tdao.updateWMenu(bean);

        if (cnt == -1) {
            System.out.println("update failed");
        } else if (cnt == 1) {
            System.out.println("update successed");
        } else {
        }
    }

    public void searchInfo() {
        System.out.println("조회할 전차 ID 입력 : ");
        String wid = scan.next();  // Scanner 버퍼 문제 방지


        Tanks bean = tdao.searchInfo(wid);  // DAO에서 객체 반환
        if (bean == null) {
            System.out.println("해당 전차 ID가 존재하지 않습니다.");
        } else {
            // 필요한 정보만 출력
            System.out.println("ID\t이름\t생산국\t가격\t출시일\t구경\t보유수량");
            System.out.println(bean.getWid() + "\t"
                    + bean.getWname() + "\t"
                    + bean.getCp() + "\t"
                    + bean.getPrice() + "\t"
                    + bean.getReleasedate() + "\t"
                    + bean.getCalibers() + "\t"
                    + bean.getAmount());
        }
    }

    public void checkAmount() {
        System.out.println("insert wid : ");
        String wid = scan.next();

        int amount = tdao.checkAmount(wid);

        if (amount == -1) {
            System.out.println("not found");
        } else {
            System.out.println("전차 " + wid + "의 보유 수량 : " + amount);
        }
    }

    public void deleteWInfo() {
        System.out.println("insert wid : ");
        String wid = scan.next();
        int cnt = -1;
        cnt = tdao.deleteWInfo(wid);

        if (cnt == -1) {
            System.out.println("delete failed");
        } else if (cnt == 0) {
            System.out.println("Info not found");
        } else if (cnt == 1) {
            System.out.println("delete success");
        } else {

        }
    }

    public void sellCountry() {
        List<Customers> customers = cdao.sellCountry();
        System.out.println("국가 이름");
        for (Customers bean : customers){
            String country = bean.getCountry();
            String message = country;
            System.out.println(message);
        }
    }

    public void checkSellamount() {
        System.out.println("구매국가(cid) : ");
        String cid = scan.next();

        Customers bean = cdao.checkSellamount(cid);
        if(bean == null){
            System.out.println("국가 확인 불가");
        }else {
            System.out.println("해당 국가 구매량");
            System.out.println(bean.getOrdervolume());
        }
    }

    public void availablePurchase() {
        String[][] orders = {
                {"CAN", "LEO", "800"},   // 캐나다: 레오파드 800대
                {"PL", "K2", "500"},     // 폴란드: K2 500대
                {"RUS", "T14", "200"},   // 러시아: T14 200대
                {"IND", "LCL", "300"},   // 인도: Leclerc 300대
                {"PKS", "TKX", "400"}    // 파키스탄: Type10 MBT 400대
        };

        for (String[] order : orders) {
            String cid = order[0];
            String tankWid = order[1];
            int amount = Integer.parseInt(order[2]);

            System.out.println("국가: " + cid + " / 전차: " + tankWid + " / 요청수량: " + amount);
            cdao.availablePurchase(cid, tankWid, amount);
        }
    }

    public void preferWeapon() {
        System.out.println("국가 코드 입력(cid) : ");
        String cid = scan.next();

        List<String> weapons = cdao.preferWeapon(cid);

        if (weapons.isEmpty()) {
            System.out.println("국가: " + cid + " → 선호 무기 없음");
        } else {
            System.out.println("국가: " + cid + " → 선호 무기:");
            for (String w : weapons) {
                System.out.println("- " + w);
            }
        }
    }
}
