package game.service.impl;

import game.model.question.Question;

import java.util.List;

public interface IQuestionService {
    List<Question> getAll();
    void add(Question question);
    long getMaxId();
    void update(Question question);
    void removeBy(long idQuestion);
    List<Question> findQuestionByContent(String keyWord);
    List<Question> findQuestionBy(String level);
    Question getBy(long idQuestion);

}
