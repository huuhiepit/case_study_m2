package game.view.impl;

import game.model.topic_set.SetExam;
import game.model.topic_set.SetExamLevelSubject;

import java.util.List;

public interface ITopicSetView {
    void showList();
    void show(SetExam setExam);
    void showDetails();
    void add();
    List<SetExamLevelSubject> addLevelSubjectExam(long idSetExam, int quantityMaximum);

    boolean checkLevelSubjectExits(long idSubject, long idLevel, List<SetExamLevelSubject> levelSubjects);

    int getTotalQuantityQuestion(List<SetExamLevelSubject> setExamLevelSubjects);

    void edit();
    void delete();
    void search();

}
