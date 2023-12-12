package game.model.question;

import game.model.impl.IParser;

public class Question implements IParser {
    private long id;
    private String content; //Nội dung câu hỏi
    private String[] options; //Chứa mảng các câu hỏi
    private int correctOptionIndex; //Chứa vị trí câu hỏi đúng
    private ELevel eLevel; //Cấp độ khó của câu hỏi: Easy, Medium, Hard.

    //Constructor
    public Question() {
    }

    public Question(long id, String content, String[] options, int correctOptionIndex, ELevel eLevel) {
        this.id = id;
        this.content = content;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
        this.eLevel = eLevel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String[] getOptions() {
        return options;
    }
    public void setOptions(String[] options) {
        this.options = options;
    }
    public void setAnswer1(String answer1) {
        this.options[0] = answer1;
    }
    public void setAnswer2(String answer2) {
        this.options[1] = answer2;
    }
    public void setAnswer3(String answer3) {
        this.options[0] = answer3;
    }
    public void setAnswer4(String answer4) {
        this.options[0] = answer4;
    }
    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }

    public void setCorrectOptionIndex(int correctOptionIndex) {
        this.correctOptionIndex = correctOptionIndex;
    }

    public ELevel geteLevel() {
        return eLevel;
    }

    public void seteLevel(ELevel eLevel) {
        this.eLevel = eLevel;
    }

    @Override
    public void parse(String line) {
        String[] items = line.split("\\|");

        this.id = Long.parseLong(items[0]);
        this.content = items[1];
        this.options = new String[]{items[2], items[3], items[4], items[5]};
        this.correctOptionIndex = Integer.parseInt(items[6]);
        this.eLevel = ELevel.getBy(items[7]);
    }

    @Override
    public String toString() {
        return String.format("%s|%s|%s|%s|%s|%s|%s|%s",
                this.id, this.content, this.options[0], this.options[1], this.options[2], this.options[3], this.correctOptionIndex, this.eLevel.getLevel());
    }
}
