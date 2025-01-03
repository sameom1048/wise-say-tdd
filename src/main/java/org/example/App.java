package org.example;


import java.util.Scanner;

public class App {

    private final Scanner sc;
    private final WiseSayingController wiseSayingController;
    private final SystemController systemController;

    public App(Scanner sc) {
        this.sc = sc;
        wiseSayingController = new WiseSayingController(sc);
        systemController = new SystemController();
    }

    public void run() {
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.println("명령 ) ");
            String cmd = sc.nextLine();
            if (cmd.equals("종료")) {
                systemController.exit();
                break;
            } else if(cmd.equals("등록")) {
                wiseSayingController.actionWrite();
            } else if(cmd.equals("목록")) {
                wiseSayingController.actionPrint();
            }
        }
    }
}