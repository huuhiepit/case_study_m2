package game.service.impl;

import game.model.question.ESubject;
import game.model.question.QuestionSubject;

import java.util.List;

public interface IQuestionSubjectService {
    List<QuestionSubject> getAll();
    List<ESubject> getSubjectsBy(long idQuestion);
    String getStringListSubjectBy(Long idQuestion);
    long getMaxId();
    void addSubjectQuestion(long id, List<ESubject> eSubject);
    void removeSubject(long idQuestion);
    List<QuestionSubject> findQuestionBySubject(long selectSubject);
}
