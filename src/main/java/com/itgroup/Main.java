package com.itgroup;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        DataManager manager = new DataManager();

        while (true) {
            System.out.println("메뉴 선택");
            System.out.println("0:종료, 1:목록 조회, 2:무기 정보 추가, 3:무기 정보 수정, 4:보유량 확인, 5:무기 정보 삭제, 6:무기 정보 확인");
            System.out.println("7:구매국가 확인, 8:국가별 구매량 확인, 9: 국가별 구매 가능량 확인, 10:국가별 선호 무기");
            int menu = scan.nextInt(); // 선택한 메뉴
            switch (menu) {
                case 0:
                    System.out.println("프로그램 종료");
                    System.exit(0); // 운영체제 종료
                case 1:
                    manager.searchMenu();
                    break;
                case 2:
                    manager.insertWMenu();
                    break;
                case 3:
                    manager.updateWMenu();
                    break;
                case 4:
                    manager.checkAmount();
                    break;
                case 5:
                    manager.deleteWInfo();
                    break;
                case 6:
                    manager.searchInfo();
                    break;
                case 7:
                    manager.sellCountry();
                    break;
                case 8:
                    manager.checkSellamount();
                    break;
                case 9:
                    manager.availablePurchase();
                    break;
                case 10:
                    manager.preferWeapon();
                    break;
            }
        }
    }
}