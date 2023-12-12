package game.model.topic_set;

import game.model.impl.IParser;
import game.model.question.ELevel;
import game.model.question.ESubject;
import game.service.SetExamService;

public class SetExamLevelSubject implements IParser {
    private long id;
    private long idSetExam;
    private ELevel eLevel;
    private ESubject eSubject;
    private int quantity;
    public static long currentId = 0;

    public SetExamLevelSubject() {

    }
    public SetExamLevelSubject(long id, long idSetExam, ELevel eLevel, ESubject eSubject, int quantity) {
        this.id = id;
        this.idSetExam = idSetExam;
        this.eLevel = eLevel;
        this.eSubject = eSubject;
        this.quantity = quantity;
    }
    public SetExamLevelSubject(long idSetExam, ELevel eLevel, ESubject eSubject, int quantity) {
        this.idSetExam = idSetExam;
        this.eLevel = eLevel;
        this.eSubject = eSubject;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdSetExam() {
        return idSetExam;
    }

    public void setIdSetExam(long idSetExam) {
        this.idSetExam = idSetExam;
    }

    public ELevel geteLevel() {
        return eLevel;
    }

    public void seteLevel(ELevel eLevel) {
        this.eLevel = eLevel;
    }

    public ESubject geteSubject() {
        return eSubject;
    }

    public void seteSubject(ESubject eSubject) {
        this.eSubject = eSubject;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static long getCurrentId() {
        return currentId;
    }

    public static void setCurrentId(long currentId) {
        SetExamLevelSubject.currentId = currentId;
    }

    @Override
    public String toString() {
        //1|1|1|2|5
        return String.format("%s|%s|%s|%s|%s",
                this.getId(), this.getIdSetExam(), this.geteLevel().getId(), this.geteSubject().getId(), this.getQuantity());
    }

    @Override
    public void parse(String line) {
        String[] items = line.split("\\|");

        this.id = Long.parseLong(items[0]);
        this.idSetExam = Long.parseLong(items[1]);
        this.eLevel = ELevel.getBy(Long.parseLong(items[2]));
        this.eSubject = ESubject.getBy(Long.parseLong(items[3]));
        this.quantity = Integer.parseInt(items[4]);
    }
}
