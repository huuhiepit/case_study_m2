package game.view;

import game.model.question.ELevel;
import game.model.question.ESubject;
import game.model.question.Question;
import game.model.question.QuestionSubject;
import game.service.QuestionService;
import game.service.QuestionSubjectService;
import game.service.impl.IQuestionService;
import game.service.impl.IQuestionSubjectService;
import game.utils.DisplayUtils;
import game.utils.InputUtils;
import game.utils.ValidateUtils;
import game.view.impl.IQuestionView;

import java.util.ArrayList;
import java.util.List;

public class QuestionView extends BaseView implements IQuestionView {
    private IQuestionService questionService;
    private IQuestionSubjectService questionSubjectService;

    public QuestionView() {
        questionService = new QuestionService();
        questionSubjectService = new QuestionSubjectService();
    }

    @Override
    public void launcher() {
        int choice = 0;
        do {

            DisplayUtils.drawDashSquare(50);
            System.out.println("                                       ╔═════════════════════════════════════════════════════╗");
            System.out.println("                                       ║                 QUẢN LÝ CÁC CÂU HỎI                 ║");
            System.out.println("                                       ╠═════════════════════════════════════════════════════╣");
            System.out.println("                                       ║ Tùy chọn:                                           ║");
            System.out.println("                                       ║ :-▶  1. Xem danh sách câu hỏi                       ║");
            System.out.println("                                       ║ :-▶  2. Thêm mới câu hỏi                            ║");
            System.out.println("                                       ║ :-▶  3. Cập nhật câu hỏi                            ║");
            System.out.println("                                       ║ :-▶  4. Xóa câu hỏi                                 ║");
            System.out.println("                                       ║ :-▶  5. Tìm kiếm câu hỏi                            ║");
            System.out.println("                                       ║ :-▶  0. Trở về                                      ║");
            System.out.println("                                       ╚═════════════════════════════════════════════════════╝");

            choice = getNumberMinMax("Nhập lựa chọn: ", 0, 5);
            switch (choice) {
                case 1: {
                    showList();
                    break;
                }
                case 2: {
                    add();
                    break;
                }
                case 3: {
                    edit();
                    break;
                }
                case 4: {
                    showList();
                    delete();
                    showList();
                    break;
                }
                case 5: {
                    searchByChoice();
                    break;
                }
            }
        } while (choice != 0);
    }

    private void searchByChoice() {
        int choice;
        do {
            System.out.println("                                       ╔═════════════════════════════════════════════════════╗");
            System.out.println("                                       ║                 QUẢN LÝ CÁC CÂU HỎI                 ║");
            System.out.println("                                       ╠═════════════════════════════════════════════════════╣");
            System.out.println("                                       ║ Tùy chọn:                                           ║");
            System.out.println("                                       ║ :-▶  1. Tìm kiếm theo nội dung                      ║");
            System.out.println("                                       ║ :-▶  2. Tìm kiếm theo chủ đề                        ║");
            System.out.println("                                       ║ :-▶  0. Trở về                                      ║");
            System.out.println("                                       ╚═════════════════════════════════════════════════════╝");
            choice = getNumberMinMax("Nhập lựa chọn: ", 0, 2);
            switch (choice) {
                case 1:
                    searchByContent();
                    break;
                case 2:
                    searchBySubject();
                    break;
            }
        } while (choice != 0);
    }

    @Override
    public void show(Question question) {
        DisplayUtils.drawDashSquare(60);
        String str = questionSubjectService.getStringListSubjectBy(question.getId());
        System.out.printf("\t%-20s ||  %-50s\n", "ID: " + question.getId(), "Câu hỏi: " + question.getContent());
        System.out.printf("\t%-20s || %-25s || %-25s || %-25s || %-25s \n", "Answer Correct: " + convertNumberToCharCorrect(question.getCorrectOptionIndex()), "A. " + question.getOptions()[0], "B. " + question.getOptions()[1],
                "C. " + question.getOptions()[2], "D. " + question.getOptions()[3]);
        System.out.printf("\t%-20s || %s\n", "Level: " + question.geteLevel().getLevel(), "Chủ đề: " + str);
    }

    @Override
    public void showList() {
        List<Question> questions = questionService.getAll();

        System.out.printf("* * * * * * * * * * * * * * * * * * * * * * * %-20s * * * * * * * * * * * * * * * * * * * * * * *\n", "DANH SÁCH CÁC CÂU HỎI");

        for (Question question : questions) {
            show(question);
        }
    }
    public void edit() {
        showList();
        System.out.println("* * * * * * * * * * * * * * * CHỈNH SỬA CÂU HỎI!!! * * * * * * * * * * * * * *");
        long id = getNumberMinMax("Nhập ID câu hỏi bạn muốn sửa: ", 1, (int) questionService.getMaxId());
        Question questionNew = questionService.getBy(id);
        if (questionNew == null) {
            System.err.println("Mã ID câu hỏi không tồn tại. Vui lòng nhập lại mã ID!!!");
            edit();
            return;
        }
        DisplayUtils.drawDashSquare(60);
        show(questionNew);
        DisplayUtils.drawDashSquare(60);
        int choiceMenu;
        do {
            System.out.println("                                       ╔═════════════════════════════════════════════════════╗");
            System.out.println("                                       ║                 CẬP NHẬT CÂU HỎI                    ║");
            System.out.println("                                       ╠═════════════════════════════════════════════════════╣");
            System.out.println("                                       ║ Tùy chọn:                                           ║");
            System.out.println("                                       ║ :-▶  1. Sửa nội dung câu hỏi                        ║");
            System.out.println("                                       ║ :-▶  2. Sửa các câu trả lời                         ║");
            System.out.println("                                       ║ :-▶  3. Sửa câu trả lời đúng                        ║");
            System.out.println("                                       ║ :-▶  4. Sửa độ khó của câu hỏi                      ║");
//            System.out.println("                                       ║ :-▶  5. Sửa chủ đề câu hỏi                          ║");
            System.out.println("                                       ║ :-▶  0. Trở về                                      ║");
            System.out.println("                                       ╚═════════════════════════════════════════════════════╝");
            choiceMenu = getNumberMinMax("Nhập lựa chọn: ", 0, 4);
            switch (choiceMenu) {
                case 1: {
                    editContent(questionNew);
                    break;
                }
                case 2: {
                    editAnswer(questionNew);
                    break;
                }
                case 3: {
                    editAnswerCorrect(questionNew);
                    break;
                }
                case 4: {
                    editLevel(questionNew);
                    break;
                }
                case 5: {
                    editSubject(questionNew);
                    break;
                }

            }
            questionService.update(questionNew);
            show(questionNew);
        } while (choiceMenu != 0);
    }
    @Override
    public void editSubject(Question questionNew) {

    }

    @Override
    public void editContent(Question questionNew) {
        String contentNew = InputUtils.inputString("Nhập câu hỏi mới: ", ValidateUtils.CONTENT_REGEX, "Lỗi, câu hỏi phải có 3 từ trở lên. Vui lòng nhập lại");
        questionNew.setContent(contentNew);
    }

    @Override
    public void editAnswer(Question questionNew) {
        int choiceMenu;
        do {
            System.out.println("                                       ╔═════════════════════════════════════════════════════╗");
            System.out.println("                                       ║                 CẬP NHẬT CÂU TRẢ LỜI                ║");
            System.out.println("                                       ╠═════════════════════════════════════════════════════╣");
            System.out.println("                                       ║ Tùy chọn:                                           ║");
            System.out.println("                                       ║ :-▶  1. Sửa câu A                                   ║");
            System.out.println("                                       ║ :-▶  2. Sửa câu B                                   ║");
            System.out.println("                                       ║ :-▶  3. Sửa câu C                                   ║");
            System.out.println("                                       ║ :-▶  4. Sửa câu D                                   ║");
            System.out.println("                                       ║ :-▶  0. Trở về                                      ║");
            System.out.println("                                       ╚═════════════════════════════════════════════════════╝");
            choiceMenu = getNumberMinMax("Nhập lựa chọn: ", 0, 6);
            switch (choiceMenu) {
                case 1: {
                    String answer1 = InputUtils.inputString("Nhập câu trả lời 1 mới: ", ValidateUtils.ANSWER_REGEX, "Lỗi, không hợp lê. Vui lòng nhập lại");
                    questionNew.setAnswer1(answer1);
                    break;
                }
                case 2: {
                    String answer2 = InputUtils.inputString("Nhập câu trả lời 2 mới: ", ValidateUtils.ANSWER_REGEX, "Lỗi, không hợp lê. Vui lòng nhập lại");
                    questionNew.setAnswer2(answer2);
                    break;
                }
                case 3: {
                    String answer3 = InputUtils.inputString("Nhập câu trả lời 3 mới: ", ValidateUtils.ANSWER_REGEX, "Lỗi, không hợp lê. Vui lòng nhập lại");
                    questionNew.setAnswer3(answer3);
                    break;
                }
                case 4: {
                    String answer4 = InputUtils.inputString("Nhập câu trả lời 4 mới: ", ValidateUtils.ANSWER_REGEX, "Lỗi, không hợp lê. Vui lòng nhập lại");
                    questionNew.setAnswer4(answer4);
                    break;
                }
            }
        } while (choiceMenu != 0);
    }

    @Override
    public void editAnswerCorrect(Question questionNew) {
        int indexCorrectNew = getNumberMinMax("Nhập câu trả lời đúng (theo số): ", 1, 4);
        questionNew.setCorrectOptionIndex(indexCorrectNew - 1);
    }

    @Override
    public void editLevel(Question questionNew) {
        for (ELevel eLevel : ELevel.values()) {
            System.out.printf("* * Nhập %s để chọn mức độ %s\n", eLevel.getId(), eLevel.getLevel());
        }
        ELevel eLevel = ELevel.getBy(getNumberMinMax("Vui lòng nhập mức độ câu hỏi: ", 1, ELevel.values().length));
        questionNew.seteLevel(eLevel);
    }

    @Override
    public void delete() {
        System.out.println("* * * * * * * * * * * * * * * XÓA CÂU HỎI? !!! * * * * * * * * * * * * * *");
        long id = getNumberMinMax("Nhập ID câu hỏi bạn muốn xóa: ", 1, (int) questionService.getMaxId());
        System.err.print("Bạn có muốn xóa câu hỏi có ID có mã " + id + " này!!!, nhập lựa chọn: ");
        int actionDelete = getNumberMinMax("* * 1. Có | 0. Không: \n", 0, 1);
        if (actionDelete == 0) {
            return;
        }
        if (questionService.getBy(id) == null) {
            System.err.println("Mã ID câu hỏi không tồn tại. Vui lòng nhập lại mã ID!!!");
            delete();
            return;
        }
        questionService.removeBy(id);
        questionSubjectService.removeSubject(id);
    }

    @Override
    public void searchByContent() {
        System.out.print("Nhập từ khóa tìm kiếm: ");
        String keyWord = scanner.nextLine();
        if (keyWord == null) {
            showList();
            return;
        }

        System.out.printf("%75s", "DANH SÁCH CÁC CÂU HỎI TÌM KIẾM\n");
        List<Question> questionsSearch = questionService.findQuestionByContent(keyWord);
        if (questionsSearch.isEmpty()) {
            System.err.println("Không tìm thấy câu hỏi nào!!!");
            return;
        }
        for (Question question : questionsSearch) {
            show(question);
        }
    }

    @Override
    public void searchBySubject() {
        System.out.println("* * * * * * * * * * * * * * * TÌM KIẾM CÂU HỎI THEO CHỦ ĐỀ!!! * * * * * * * * * * * * * *");
        for (ESubject subject : ESubject.values()) {
            System.out.printf("|| ||\t Nhập %s để tìm kiếm theo chủ đề: %s\n", subject.getId(), subject.getTopic());
        }
        int selectSubject = getNumberMinMax("Nhập lựa chọn: ", 1, ESubject.getMaxId());
        ESubject subject = ESubject.getBy(selectSubject);
        System.out.printf("%75s", "DANH SÁCH CÁC CÂU HỎI TÌM KIẾM\n");
        List<QuestionSubject> questionsSearch = questionSubjectService.findQuestionBySubject(selectSubject);
        if (questionsSearch.isEmpty()) {
            System.err.println("Không tìm thấy câu hỏi nào!!!");
            return;
        }
        Question question;
        for (QuestionSubject questionSubject : questionsSearch) {
            question = questionService.getBy(questionSubject.getIdQuestion());
            show(question);
        }

    }

    public void add() {
        System.out.println("* * * * * * * * * * * * * * * THÊM CÂU HỎI? !!! * * * * * * * * * * * * * *");
        String content = InputUtils.inputString("Nhập câu hỏi: ", ValidateUtils.CONTENT_REGEX, "Không được bỏ trống hoặc câu hỏi phải có 3 từ trở lên!!");
        DisplayUtils.drawDashSquare(40);

        String[] answers = new String[4];
        for (int i = 0; i < 4; i++) {
            answers[i] = InputUtils.inputString(String.format("Nhập câu trả lời %s: ", i + 1) , ValidateUtils.ANSWER_REGEX, "Không được bỏ trống!!!");
        }
        DisplayUtils.drawDashSquare(40);

        System.out.print("Câu trả lời đúng là: ");
        int correctAnswer = Integer.parseInt(scanner.nextLine()) - 1;
        DisplayUtils.drawDashSquare(40);

        for (ELevel eLevel : ELevel.values()) {
            System.out.printf("* * Nhập %s để chọn mức độ %s\n", eLevel.getId(), eLevel.getLevel());
        }
        ELevel eLevel = ELevel.getBy(getNumberMinMax("Vui lòng nhập mức độ câu hỏi: ", 1, ELevel.values().length));
        long id = questionService.getMaxId();
        Question question = new Question(++id, content, answers, correctAnswer, eLevel);
        DisplayUtils.drawDashSquare(40);
        List<ESubject> eSubject = addSubjectGiveQuestion();
        DisplayUtils.drawDashSquare(40);

        questionService.add(question);
        questionSubjectService.addSubjectQuestion(id, eSubject);
        showList();
    }

    @Override
    public List<ESubject> addSubjectGiveQuestion() {
        List<ESubject> subjects = new ArrayList<>();

        while (true) {
            System.out.println("* * Nhập 1. Để thêm một chủ đề cho câu hỏi.");
            System.out.println("* * Nhập 0. Để dừng");

            int choice = getNumberMinMax("Nhập lựa chọn: ", 0, 1);

            if (choice == 0) {
                break;
            }
            DisplayUtils.drawDashSquare(40);
            for (ESubject eSubject : ESubject.values()) {
                System.out.printf("* * Nhập %s để thêm chủ đề %s\n", eSubject.getId(), eSubject.getTopic());
            }
            DisplayUtils.drawDashSquare(40);
            int actionSubject = getNumberMinMax("Nhập chủ đề cần thêm: ", 1, ESubject.getMaxId());
            ESubject subject = ESubject.getBy(actionSubject);
            if (subjects.contains(subject)) {
                System.err.println("Bạn đã thêm chủ đề này vào rồi!!!");
            } else {
                subjects.add(subject);
            }

        }

        return subjects;
    }

}
