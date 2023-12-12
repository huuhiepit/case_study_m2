package game.service.impl;

import game.model.topic_set.SetExam;

import java.util.List;

public interface ISetExamService {
    List<SetExam> getAll();
    SetExam findExamBy(long id);
    long getMaxId();

    void add(SetExam setExamNew);

    void removeBy(long id);

    List<SetExam> findQuestionByContent(String keyWord);
}
