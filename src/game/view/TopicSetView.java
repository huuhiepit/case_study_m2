package game.view;

import game.model.question.ELevel;
import game.model.question.ESubject;
import game.model.question.Question;
import game.model.topic_set.SetExam;
import game.model.topic_set.SetExamLevelSubject;
import game.service.SetExamLevelSubjectService;
import game.service.SetExamService;
import game.service.impl.ISetExamLevelSubjectService;
import game.service.impl.ISetExamService;
import game.utils.DisplayUtils;
import game.utils.InputUtils;
import game.utils.ValidateUtils;
import game.view.impl.ITopicSetView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TopicSetView extends BaseView implements ITopicSetView {
    private ISetExamService setExamService;
    private ISetExamLevelSubjectService setExamLevelSubjectService;
    public TopicSetView() {
        this.setExamService = new SetExamService();
        this.setExamLevelSubjectService = new SetExamLevelSubjectService();
    }
    @Override
    public void launcher() {
        DisplayUtils.drawDashSquare(50);
        System.out.println("* * * * * * * * * * * * * * * * * * QUẢN LÝ BỘ ĐỀ CÂU HỎI!!! * * * * * * * * * * * * * * * * *");
        System.out.println("* * Nhập 1. Hiển thị tất cả bộ đề");
        System.out.println("* * Nhập 2. Hiển thị chi tiết bộ đề");
        System.out.println("* * Nhập 3. Tạo mới bộ đề");
        System.out.println("* * Nhập 4. Sửa bộ đề");
        System.out.println("* * Nhập 5. Xóa một bộ đề");
        System.out.println("* * Nhập 6. Để tìm kiếm bộ đề");
        DisplayUtils.drawDashSquare(50);
        int choice = getNumberMinMax("Nhập lựa chọn: ", 1, 6);
        switch (choice) {
            case 1: {
                showList();
                break;
            }
            case 2: {
                showDetails();
                break;
            }
            case 3: {
                add();
                break;
            }
            case 5: {
                showList();
                delete();
                showList();
                break;
            }
            case 6: {
                search();
                break;
            }
        }

    }

    @Override
    public void showList() {
        System.out.printf("* * * * * * * * * * * * * * * * * * * * * * * %-20s * * * * * * * * * * * * * * * * * * * * * * *\n", "DANH SÁCH CÁC BỘ ĐỀ");
        DisplayUtils.drawDashSquare(50);
        List<SetExam> setExams = setExamService.getAll();
        System.out.printf("%10s | %-25s | %20s | %-20s\n", "ID", "TÊN BỘ ĐỀ", "TỔNG CÂU HỎi", "NGÀY TẠO");
        setExams.forEach(this::show);
    }

    @Override
    public void show(SetExam setExam) {
        DisplayUtils.drawDashSquare(50);
        System.out.printf("%10s | %-25s | %20s | %-20s\n", setExam.getId(), setExam.getName(), setExam.getQuantity(), setExam.getCreateAt());
    }

    @Override
    public void showDetails() {
        showList();
        long idSetExam = getNumberMinMax("Nhập ID câu hỏi bạn muốn sửa: ", 1, (int) setExamService.getMaxId());
        SetExam setExam = setExamService.findExamBy(idSetExam);
        if (setExam == null) {
            System.err.println("Mã ID bộ đề không tồn tại. Vui lòng nhập lại mã ID!!!");
            showDetails();
            return;
        }

        List<SetExamLevelSubject> setExamLevelSubjects = setExamLevelSubjectService.findSetExamBy(idSetExam);
        show(setExam);
        DisplayUtils.drawDashSquare(50);

        System.out.printf("* * * * * * * * * * * * * * %s * * * * * * * * * * * * * *\n", "BỘ ĐỀ: " + setExam.getName().toUpperCase());
        System.out.printf("%10s | %-15s | %-20s | %s\n", "ID", "MỨC ĐỘ", "CHỦ ĐỀ", "SỐ LƯỢNG CÂU HỎI");
        setExamLevelSubjects.forEach(this::showSetExamLevelSubject);
    }

    public void showSetExamLevelSubject(SetExamLevelSubject setExamLevelSubject) {
        DisplayUtils.drawDashSquare(30);
        System.out.printf("%10s | %-15s | %-20s | %s\n",
                setExamLevelSubject.getId(), setExamLevelSubject.geteLevel().getLevel(), setExamLevelSubject.geteSubject().getTopic(), setExamLevelSubject.getQuantity());
    }

    @Override
    public void add() {
        System.out.println("* * * * * * * * * * * * * * * THÊM BỘ ĐỀ MỚI !!! * * * * * * * * * * * * * *");
        String content = InputUtils.inputString("Nhập tên bộ đề: ", ValidateUtils.ANSWER_REGEX, "Không được bỏ trống!!");
        DisplayUtils.drawDashSquare(40);
        System.out.print("Nhập số lượng câu hỏi: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        int idMaxExam = (int) setExamService.getMaxId();
        SetExam setExamNew = new SetExam(++idMaxExam, content, quantity, LocalDate.now());
        List<SetExamLevelSubject> setExamLevelSubjects = addLevelSubjectExam(setExamNew.getId(), quantity);

        setExamService.add(setExamNew);
        setExamLevelSubjectService.add(setExamLevelSubjects);

    }

    @Override
    public List<SetExamLevelSubject> addLevelSubjectExam(long idSetExam, int quantityMaximum) {
        List<SetExamLevelSubject> setExamLevelSubjects = new ArrayList<>();
        int totalQuantity = 0;
        long idLevelSubjectExam = setExamLevelSubjectService.getMaxId();
        int choice = 0;
        while (true) {
            totalQuantity = getTotalQuantityQuestion(setExamLevelSubjects);
            if( totalQuantity >= quantityMaximum) {
                break;
            }
            System.out.printf("* * * * Tổng %s trên %s * * * *\n", totalQuantity, quantityMaximum);
            System.out.println("* * Nhập 1. Để thêm mức độ và chủ đề cho bộ đề");
            System.out.println("* * Nhập 0. Để dừng");
            choice = getNumberMinMax("Nhập lựa chọn: ", 0, 1);

            if (choice == 0) {
                break;
            }

            DisplayUtils.drawDashSquare(40);
            for (ESubject subject : ESubject.values()) {
                System.out.printf("* * Nhập %s: Để chọn chủ đề %s\n", subject.getId(), subject.getTopic());
            }
            int selectSubject = getNumberMinMax("Nhập chủ đề: ", 1, ESubject.getMaxId());

            DisplayUtils.drawDashSquare(40);
            for (ELevel level : ELevel.values()) {
                System.out.printf("* * Nhập %s: Để chọn mức độ %s\n", level.getId(), level.name());
            }
            int selectLevel = getNumberMinMax("Nhập mức độ: ", 1, ELevel.getMaxId());

            ESubject subject = ESubject.getBy(selectSubject);
            ELevel level = ELevel.getBy(selectLevel);

//            ELevel level;
//            if(totalQuantity <= 5) {
//                level = ELevel.EASY;
//            } else if(totalQuantity > 10) {
//                level = ELevel.HARD;
//            } else if(totalQuantity > 5 && totalQuantity <= 10) {
//                level = ELevel.MEDIUM;
//            }

            if (checkLevelSubjectExits(selectSubject, selectLevel, setExamLevelSubjects)) {
                System.err.println("Bộ đề đã có chủ đề và mức độ này rồi, Xin vui lòng nhập lại!!!");
            } else {
                int quantity = getNumberMinMax("Nhập số lượng câu hỏi: ", String.format("Chỉ được nhập thêm %s câu hỏi!!!", quantityMaximum - totalQuantity),1, quantityMaximum - totalQuantity);
                setExamLevelSubjects.add(new SetExamLevelSubject(++idLevelSubjectExam, idSetExam, level, subject, quantity));
            }
        }

        return setExamLevelSubjects;
    }

    @Override
    public boolean checkLevelSubjectExits(long idSubject, long idLevel,List<SetExamLevelSubject> levelSubjects) {
        for (SetExamLevelSubject exam: levelSubjects) {
            if(exam.geteSubject().getId() == idSubject && exam.geteLevel().getId() == idLevel) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getTotalQuantityQuestion(List<SetExamLevelSubject> setExamLevelSubjects) {
        int total = 0;
        for(SetExamLevelSubject setExamLevelSubject: setExamLevelSubjects) {
            total += setExamLevelSubject.getQuantity();
        }
        return total;
    }

    @Override
    public void edit() {

    }

    @Override
    public void delete() {
        System.out.println("* * * * * * * * * * * * * * * XÓA BỘ ĐỀ CÂU HỎI? !!! * * * * * * * * * * * * * *");
        long id = getNumberMinMax("Nhập ID bộ đề bạn muốn xóa: ", 1, (int) setExamLevelSubjectService.getMaxId());
        System.err.print("Bạn có muốn xóa bộ đề ID có mã " + id + " này!!!. Nhập lựa chọn: ");

        int actionDelete = getNumberMinMax("* * 1. Có | 0. Không: \n", 0, 1);
        if (actionDelete == 0) {
            return;
        }
        if (setExamService.findExamBy(id) == null) {
            System.err.println("Mã ID câu hỏi không tồn tại. Vui lòng nhập lại mã ID!!!");
            delete();
            return;
        }

        setExamService.removeBy(id);
        setExamLevelSubjectService.removeLevelSubjectBy(id);
    }

    @Override
    public void search() {
        System.out.print("Nhập từ khóa tìm kiếm: ");
        String keyWord = scanner.nextLine();
        if (keyWord == null) {
            showList();
            return;
        }

        System.out.printf("* * * * * * * * * * * * * * * %s * * * * * * * * * * * * * * *", "DANH SÁCH CÁC BỘ ĐỀ TÌM KIẾM\n");
        List<SetExam> setExamsSearch = setExamService.findQuestionByContent(keyWord);
        if (setExamsSearch.isEmpty()) {
            System.err.println("Không tìm thấy câu hỏi nào!!!");
            return;
        }
        for (SetExam setExam : setExamsSearch) {
            show(setExam);
        }
    }
}
