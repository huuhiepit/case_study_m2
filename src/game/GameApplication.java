package game;

import game.service.QuestionService;
import game.view.PlayerView;
import game.view.QuestionView;
import game.view.TopicSetView;

public class GameApplication {
    protected QuestionView questionView;
    protected QuestionService questionService;
    protected TopicSetView topicSetView;
    protected PlayerView playerView;

    public GameApplication() {
        questionView = new QuestionView();
        topicSetView = new TopicSetView();
        playerView = new PlayerView();
    }

    public void run() {
        int choiceMenu;
        do {
            System.out.println("                                       ╔═════════════════════════════════════════════════════╗");
            System.out.println("                                       ║                     GIAO DIỆN                       ║");
            System.out.println("                                       ╠═════════════════════════════════════════════════════╣");
            System.out.println("                                       ║ Tùy chọn:                                           ║");
            System.out.println("                                       ║ :-▶  1. Dành cho Quản lý                            ║");
            System.out.println("                                       ║ :-▶  2. Dành cho Người chơi                         ║");
            System.out.println("                                       ║ :-▶  0. Thoát                                       ║");
            System.out.println("                                       ╚═════════════════════════════════════════════════════╝");
            choiceMenu = playerView.getNumberMinMax("Nhập lựa chọn: ", 0, 2);
            switch (choiceMenu) {
                case 1:
                    showManagerAdmin();
                    break;
                case 2:
                    playerView.launcher();
                    break;
            }
        } while (choiceMenu != 0);
    }

    void showManagerAdmin() {

        System.out.println("                                       ╔═════════════════════════════════════════════════════╗");
        System.out.println("                                       ║                GIAO DIỆN TRÌNH QUẢN LÝ              ║");
        System.out.println("                                       ╠═════════════════════════════════════════════════════╣");
        System.out.println("                                       ║ Tùy chọn:                                           ║");
        System.out.println("                                       ║ :-▶  1. Quản lý câu hỏi                             ║");
        System.out.println("                                       ║ :-▶  2. Quản lý bộ đề                               ║");
        System.out.println("                                       ║ :-▶  0. Thoát chương trình                          ║");
        System.out.println("                                       ╚═════════════════════════════════════════════════════╝");

        int choiceManager = playerView.getNumberMinMax("Nhập lựa chọn: ", 0, 2);

        switch (choiceManager) {
            case 1: {
                questionView.launcher();
                break;
            }
            case 2: {
                topicSetView.launcher();
                break;
            }
            case 0: {
                break;
            }
        }
    }

    public static void main(String[] args) {
        GameApplication game = new GameApplication();
        game.run();
    }
}
