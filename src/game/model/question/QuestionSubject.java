package game.model.question;

import game.model.impl.IParser;

public class QuestionSubject implements IParser {
    private long id;
    private long idQuestion; //Mã câu hỏi
    private long idSubject; //Mã chủ đề
    public QuestionSubject() {}
    public QuestionSubject(long id, long idQuestion, long idSubject) {
        this.id = id;
        this.idQuestion = idQuestion;
        this.idSubject = idSubject;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(long idQuestion) {
        this.idQuestion = idQuestion;
    }

    public long getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(long idSubject) {
        this.idSubject = idSubject;
    }

    @Override
    public String toString() {
        return String.format("%s|%s|%s", this.id, this.idQuestion, this.idSubject);
    }

    @Override
    public void parse(String line) {
        //1|1|1
        String[] items = line.split("\\|");
        this.id = Long.parseLong(items[0]);
        this.idQuestion = Long.parseLong(items[1]);
        this.idSubject = Long.parseLong(items[2]);

    }
}