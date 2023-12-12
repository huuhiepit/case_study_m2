package game.model.question;

import java.util.Arrays;

public enum ESubject {
    SUBJECT1(1, "Khoa học"),
    SUBJECT2(2, "Xã hội"),
    SUBJECT3(3, "Toán học"),
    SUBJECT4(4, "Tâm linh"),
    SUBJECT5(5, "Thể thao");

    private long id;
    private String topic;

    ESubject(long id, String topic) {
        this.id = id;
        this.topic = topic;
    }

    public long getId() {
        return id;
    }

    public String getTopic() {
        return topic;
    }

    public static ESubject getBy(long id) {
        for (ESubject eSubject: ESubject.values()) {
            if(eSubject.getId() == id) {
                return eSubject;
            }
        }
        return null;
    }
    public static int getMaxId() {
        return (int) Arrays.stream(ESubject.values()).max((o1, o2) -> Long.compare(o1.getId(), o2.getId())).orElseThrow().getId();
    }
}
