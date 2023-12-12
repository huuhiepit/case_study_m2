package game.service;

import game.service.impl.IQuestionSubjectService;
import game.utils.FileUtils;
import game.model.question.ESubject;
import game.model.question.QuestionSubject;
import game.utils.Config;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionSubjectService implements IQuestionSubjectService {
    private ESubject eSubject;

    //Get list Question Subject in File question_subject.txt
    public List<QuestionSubject> getAll() {
        return FileUtils.readFile(Config.PATH_QUESTION_SUBJECT, QuestionSubject.class);
    }
    //Get list Subject by id question
    public List<ESubject> getSubjectsBy(long idQuestion) {
        List<QuestionSubject> questionSubjects = getAll();
        List<ESubject> eSubjects = new ArrayList<>();

        for(QuestionSubject questionSubject: questionSubjects) {
            if(questionSubject.getIdQuestion() == idQuestion) {
                eSubjects.add(ESubject.getBy(questionSubject.getIdSubject()));
            }
        }

        return eSubjects;
    }
    //Get String all subject by id question
    public String getStringListSubjectBy(Long idQuestion) {
        List<ESubject> eSubjects = getSubjectsBy(idQuestion);

        if (eSubjects.isEmpty()) {
            return "Chưa có chủ đề.";
        }

        return eSubjects.stream()
                .map(ESubject::getTopic)
                .collect(Collectors.joining(", ")) + ".";
    }
    public long getMaxId() {
        return getAll().stream().max(Comparator.comparing(QuestionSubject::getId)).orElseThrow().getId();
    }
    public void addSubjectQuestion(long id, List<ESubject> eSubject) {
        if (!eSubject.isEmpty()) {
            long idQS = getMaxId();
            for (ESubject subject: eSubject) {
                QuestionSubject questionSubject = new QuestionSubject(++idQS, id, subject.getId());
                FileUtils.writeFile(Config.PATH_QUESTION_SUBJECT, questionSubject);
            }
        }
    }

    public void removeSubject(long idQuestion) {
        List<QuestionSubject> questionSubjects = getAll();
        questionSubjects.removeIf(questionSubject -> questionSubject.getIdQuestion() == idQuestion);
        FileUtils.writeFile(Config.PATH_QUESTION_SUBJECT, questionSubjects);
    }

    public List<QuestionSubject> findQuestionBySubject(long idSubject) {
        return getAll().stream().filter(questionSubject -> questionSubject.getIdSubject() == idSubject).collect(Collectors.toList());
    }
}