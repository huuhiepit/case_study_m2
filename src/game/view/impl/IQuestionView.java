package game.view.impl;

import game.model.question.ESubject;
import game.model.question.Question;

import java.util.List;

public interface IQuestionView {
    void launcher(); //Khởi chạy giao diện
    void show(Question question); //Hiển thị câu hỏi
    void showList(); //Hiển thị danh sách các câu hỏi
    List<ESubject> addSubjectGiveQuestion(); //Thêm các chủ đề vào câu hỏi
    void add();
    void edit(); //Sửa một câu hỏi

    void editSubject(Question questionNew);

    void editContent(Question questionNew);
    void editAnswer(Question questionNew);
    void editAnswerCorrect(Question questionNew);
    void editLevel(Question questionNew);
//    void editSubject(Question questionNew);
    void delete(); //Xóa một câu hỏi
    void searchByContent(); //Tìm kiếm theo nội dung câu hỏi
    void searchBySubject(); //Tìm kiếm theo chủ đề câu hỏi
}
