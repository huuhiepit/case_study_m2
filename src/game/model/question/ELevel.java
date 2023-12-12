package game.model.question;

import java.util.Arrays;

public enum ELevel {
    EASY(1, "Dễ"), MEDIUM(2, "Trung bình"), HARD(3, "Khó");
    private long id;
    private String level;

    ELevel(long id, String level) {
        this.id = id;
        this.level = level;
    }

    public static int getMaxId() {
        return (int) Arrays.stream(ELevel.values()).max((o1, o2) -> Long.compare(o1.getId(), o2.getId())).orElseThrow().getId();
    }

    public String getLevel() {
        return level;
    }

    public long getId() {
        return id;
    }

    public static ELevel getBy(String level) {
        for (ELevel eLevel : ELevel.values()) {
            if (eLevel.getLevel().equals(level)) {
                return eLevel;
            }
        }
        return null;
    }

    public static ELevel getBy(long id) {
        for (ELevel eLevel : ELevel.values()) {
            if (eLevel.getId() == id) {
                return eLevel;
            }
        }
        return null;
    }
}