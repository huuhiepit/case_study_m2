package game.service.impl;

import game.model.topic_set.SetExamLevelSubject;

import java.util.List;

public interface ISetExamLevelSubjectService {
    List<SetExamLevelSubject> getAll();
    List<SetExamLevelSubject> findSetExamBy(long idSetExam);

    List<SetExamLevelSubject> findSetExamLevelSubject(long idSetExam, long level);

    void add(List<SetExamLevelSubject> setExamLevelSubjects);

    long getMaxId();

    void removeLevelSubjectBy(long idSetExam);
}
