package game.service;

import game.model.question.Question;
import game.model.topic_set.SetExam;
import game.service.impl.ISetExamService;
import game.utils.Config;
import game.utils.FileUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SetExamService implements ISetExamService {
    @Override
    public List<SetExam> getAll() {
        return FileUtils.readFile(Config.PATH_SET_EXAM, SetExam.class);
    }

    @Override
    public SetExam findExamBy(long id) {
        return  getAll().stream().filter(setExam -> setExam.getId() == id).findFirst().orElse(null);
    }
    @Override
    public long getMaxId() {
        return getAll().stream().max(Comparator.comparingLong(SetExam::getId)).orElseThrow().getId();
    }

    @Override
    public void add(SetExam setExamNew) {
        FileUtils.writeFile(Config.PATH_SET_EXAM, setExamNew);
    }

    @Override
    public void removeBy(long id) {
        List<SetExam> setExams = getAll();
        setExams.removeIf(setExam -> setExam.getId() == id);

        FileUtils.writeFile(Config.PATH_SET_EXAM, setExams);
    }

    @Override
    public List<SetExam> findQuestionByContent(String keyWord) {
        return getAll().stream().filter(setExam -> setExam.getName().contains(keyWord)).collect(Collectors.toList());
    }

}
