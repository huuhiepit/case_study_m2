package game.service;

import game.model.topic_set.SetExamLevelSubject;
import game.service.impl.ISetExamLevelSubjectService;
import game.utils.Config;
import game.utils.FileUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SetExamLevelSubjectService implements ISetExamLevelSubjectService {

    @Override
    public List<SetExamLevelSubject> getAll() {
        return FileUtils.readFile(Config.PATH_SET_EXAM_LEVEL_SUBJECT, SetExamLevelSubject.class);
    }
    @Override
    public List<SetExamLevelSubject> findSetExamBy(long idSetExam) {
        return getAll().stream().filter(setExamLevelSubject -> setExamLevelSubject.getIdSetExam() == idSetExam).collect(Collectors.toList());
    }

    @Override
    public List<SetExamLevelSubject> findSetExamLevelSubject(long idSetExam, long level) {
        return findSetExamBy(idSetExam).stream().filter(setExamLevelSubject -> setExamLevelSubject.geteLevel().getId() == level).collect(Collectors.toList());
    }

    @Override
    public void add(List<SetExamLevelSubject> setExamLevelSubjects) {
        for (SetExamLevelSubject setExamLevelSubject : setExamLevelSubjects) {
            FileUtils.writeFile(Config.PATH_SET_EXAM_LEVEL_SUBJECT, setExamLevelSubject);
        }
    }

    @Override
    public long getMaxId() {
        return getAll().stream().max(Comparator.comparingLong(SetExamLevelSubject::getId)).orElseThrow().getId();
    }

    @Override
    public void removeLevelSubjectBy(long idSetExam) {
        List<SetExamLevelSubject> setExamLevelSubjects = getAll();
        setExamLevelSubjects.removeIf(setExamLevelSubject -> setExamLevelSubject.getIdSetExam() == idSetExam);
        FileUtils.writeFile(Config.PATH_SET_EXAM_LEVEL_SUBJECT, setExamLevelSubjects);
    }
}