package game.service;

import game.service.impl.IQuestionService;
import game.utils.FileUtils;
import game.model.question.Question;
import game.utils.Config;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionService implements IQuestionService {
    @Override
    public List<Question> getAll() {
        return FileUtils.readFile(Config.PATH_QUESTION, Question.class);
    }
    @Override
    public void add(Question question) {
        FileUtils.writeFile(Config.PATH_QUESTION, question);
    }
    @Override
    public long getMaxId() {
        if(getAll().isEmpty()) {
            return 0;
        }
        return getAll().stream().max(Comparator.comparingLong(Question::getId)).orElseThrow().getId();
    }
    @Override
    public void removeBy(long id) {
        List<Question> questions = getAll();
        questions.removeIf(question -> question.getId() == id);

        FileUtils.writeFile(Config.PATH_QUESTION, questions);
    }
    @Override
    public List<Question> findQuestionByContent(String keyWord) {
        return getAll().stream().filter(question -> question.getContent().contains(keyWord)).collect(Collectors.toList());
    }

    @Override
    public List<Question> findQuestionBy(String level) {
        return getAll().stream().filter(question -> question.geteLevel().getLevel().equals(level)).collect(Collectors.toList());
    }

    @Override
    public Question getBy(long idQuestion) {
        return getAll().stream().filter(question -> question.getId() == idQuestion).findFirst().orElse(null);
    }

    @Override
    public void update(Question questionNew) {
        List<Question> questions = getAll();
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).getId() == questionNew.getId()) {
                questions.set(i, questionNew);
            }
        }
        FileUtils.writeFile(Config.PATH_QUESTION, questions);
        System.out.println("Chỉnh sửa thành công!!!");
    }
}