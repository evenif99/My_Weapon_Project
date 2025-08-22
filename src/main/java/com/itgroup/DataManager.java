package com.itgroup;

import com.itgroup.bean.Tanks;
import com.itgroup.dao.TanksDao;

import java.util.List;
import java.util.Scanner;

public class DataManager {
    private TanksDao tdao = null; // 실제 데이터 베이스와 연동하는 다오 클래스(생성자)
    private Scanner scan = null; // 회원 정보 입력 받기 위한 스캐너 장치

    public DataManager(){
        this.tdao = new TanksDao();
        this.scan = new Scanner(System.in);
    }


    public void searchMenu() {
        List<Tanks> tanks = tdao.searchMenu();
        System.out.println("이름\t아이디\t생산국");
        for (Tanks bean : tanks){
            String wname = bean.getWname();
            String wid = bean.getWid();
            String cp = bean.getCp();
            String message = wname + "\t" + wid + "\t" + cp;
            System.out.println(message);
        }
    }

    public void insertWMenu() {
        Tanks bean = new Tanks();
        int cnt = -1;

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

        if (cnt == -1){
            System.out.println("정보 추가 실패");
        }else if(cnt == 1){
            System.out.println("id " + wid + " 추가성공");
        }else{}
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

        if (cnt == -1){
            System.out.println("update failed");
        } else if (cnt == 1) {
            System.out.println("update successed");
        }else {}
    }

    public void searchInfo() {
        String findWid = "M1";
        Tanks obj = tdao.searchInfo(findWid);

        if (obj == null){
            System.out.println("검색 무기가 존재하지 않습니다.");
        }else {
            String wid = obj.getWid();
            String wname = obj.getWname();
            String cp = obj.getCp();
            int price = obj.getPrice();
            String releasedate = obj.getReleasedate();
            String calibers = obj.getCalibers();
            String message = wid + "\t" + wname + "\t" + cp + "\t" + price + "\t" + releasedate + "\t" + calibers;
            System.out.println(message);
        }
    }
}
