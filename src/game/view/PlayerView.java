package game.view;

import game.model.player.Player;
import game.model.question.Question;
import game.model.question.QuestionSubject;
import game.model.topic_set.SetExam;
import game.model.topic_set.SetExamLevelSubject;
import game.model.topic_set.SetExamPlayer;
import game.service.*;
import game.service.impl.*;
import game.utils.DisplayUtils;
import game.utils.InputUtils;
import game.utils.ValidateUtils;
import game.view.impl.IPlayerView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PlayerView extends BaseView implements IPlayerView {


    public PlayerView() {
        playerService = new PlayerService();
        questionService = new QuestionService();
        setExamService = new SetExamService();
        setExamPlayerService = new SetExmPlayerService();
        setExamLevelSubjectService = new SetExamLevelSubjectService();
        questionSubjectService = new QuestionSubjectService();
    }

    @Override
    public void launcher() {
        Player phu = new Player(2, 3, "Phu");
        int choice;
        do {
            System.out.println("                                       ╔═════════════════════════════════════════════════════╗");
            System.out.println("                                       ║                 GAME AI LÀ TRIỆU PHÚ                ║");
            System.out.println("                                       ╠═════════════════════════════════════════════════════╣");
            System.out.println("                                       ║ Tùy chọn:                                           ║");
            System.out.println("                                       ║ :-▶  1. Chơi game                                   ║");
            System.out.println("                                       ║ :-▶  2. Xem lịch sử                                 ║");
            System.out.println("                                       ║ :-▶  0. Đăng xuất                                   ║");
            System.out.println("                                       ╚═════════════════════════════════════════════════════╝");
            choice = getNumberMinMax("Nhập lựa chọn: ", 0, 3);
            switch (choice) {
                case 1: {
                    playGame(phu);
                    break;
                }
                case 2: {
                    showListHistory(phu);
                    break;
                }
            }
        } while (choice != 0);
    }

    @Override
    public void playGame(Player player) {
        List<SetExam> setExams = setExamService.getAll();
        SetExam randomSetExam;
        if (!setExams.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(setExams.size());

            randomSetExam = setExams.get(randomIndex);
            System.out.println("Phần tử ngẫu nhiên: " + randomSetExam);
        } else {
            return;
        }

        List<Question> questionList = questionListGame(randomSetExam);
        int score = 0;
        boolean flag;
        for (int i = 0; i < questionList.size(); i++) {
            flag = showGameQuestion(i + 1, questionList.get(i));

            if (flag) {
                score += 1;
            } else {
                break;
            }
        }
        System.out.println("Điểm của bạn là: " + score);

        long id = setExamPlayerService.getMaxId();

        SetExamPlayer examPlayer = new SetExamPlayer(++id, 1, player.getId(), score, LocalDate.now());

        setExamPlayerService.addPlayGame(examPlayer);
    }
    @Override
    public List<Question> questionListGame(SetExam setExam) {
        List<Question> questions = new ArrayList<>();
        List<QuestionSubject> questionSubjectsList;
        List<Question> questionByLevelList;
        int quantity = 0;
        for(int i = 1; i <= 3; i++) {
            List<SetExamLevelSubject> setLevelExamSubjects = setExamLevelSubjectService.findSetExamLevelSubject(setExam.getId(), i);

            for(SetExamLevelSubject examLevelSubject: setLevelExamSubjects) {
                questionSubjectsList = questionSubjectService.findQuestionBySubject(examLevelSubject.geteSubject().getId());
                Collections.shuffle(questionSubjectsList);
                quantity = examLevelSubject.getQuantity();

                for (QuestionSubject questionSubject: questionSubjectsList) {
                    Question question = questionService.getBy(questionSubject.getIdQuestion());
                    if(quantity > 0 && question.geteLevel().getId() == i) {
                        questions.add(question);
                        quantity--;
                    }
                }
            }
        }

        return questions;
    }
    @Override
    public boolean showGameQuestion(int stt, Question question) {
        DisplayUtils.drawDashSquare(50);
        System.out.printf("Câu hỏi %s: ", stt);
        System.out.printf("\t\t\t%s\n", question.getContent());
        System.out.printf("\tA. %-25s ║ \tC. %-25s\n", question.getOptions()[0], question.getOptions()[2]);
        System.out.printf("\tB. %-25s ║ \tD. %-25s\n", question.getOptions()[1], question.getOptions()[3]);
        DisplayUtils.drawDashSquare(50);

        String result = InputUtils.inputString("Nhập câu trả lời: ", ValidateUtils.ANSWER_PLAYER_REGEX, "Chỉ được nhập các từ A,B,C,D");

        if (question.getCorrectOptionIndex() == convertCharToNumberCorrect((result.trim().toCharArray()[0]))) {
            return true;
        }
        return false;
    }

    @Override
    public void showListHistory(Player player) {
        List<SetExamPlayer> setExamPlayers = setExamPlayerService.getHistoryPlayGame(player.getId());

        System.out.printf("* * * * * * * * * * * %-20s * * * * * * * * * * * \n", "LỊCH SỬ CHƠI GAME");
        System.out.printf("%10s | %-25s | %-25s\n", "ID", "ĐIỂM", "Ngày chơi");
        for (SetExamPlayer examPlayer: setExamPlayers) {
            showHistory(examPlayer);
        }
    }
    @Override
    public void showHistory(SetExamPlayer setExamPlayer) {
        DisplayUtils.drawDashSquare(60);
        System.out.printf("%10s | %-25s | %-25s\n", setExamPlayer.getIdSetExam(), setExamPlayer.getScore(), setExamPlayer.getPlayDate());
    }
}
