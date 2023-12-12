package game.model.topic_set;

import game.model.impl.IParser;
import game.utils.DateUtils;

import java.time.LocalDate;

public class SetExam implements IParser {
    private long id;
    private String name;
    private int quantity;
    private LocalDate createAt;
    public static long currentId = 0;

    public SetExam() { }

    public SetExam(long id, String name, int quantity, LocalDate createAt) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.createAt = createAt;
    }
    public SetExam(String name, int quantity, LocalDate createAt) {
        this.name = name;
        this.quantity = quantity;
        this.createAt = createAt;
    }

    public static void setCurrentId(long currentId) {
        SetExam.currentId = currentId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return String.format("%s|%s|%s|%s", this.id, this.name, this.quantity, this.createAt);
    }


    @Override
    public void parse(String line) {
        String[] items = line.split("\\|");

        this.id = Long.parseLong(items[0]);
        this.name = items[1];
        this.quantity = Integer.parseInt(items[2]);
        this.createAt = DateUtils.parse(items[3]);
    }
}
