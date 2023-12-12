package game.model.topic_set;

import game.model.impl.IParser;
import game.model.player.Player;
import game.utils.DateUtils;

import java.time.LocalDate;

public class SetExamPlayer implements IParser {
    private long id;
    private long idSetExam;
    private long idPlayer;
    private long score;
    private LocalDate playDate;
    public static long currentId = 0;

    public SetExamPlayer() {}
    public SetExamPlayer(long id, long idSetExam, long idPlayer, long score, LocalDate playDate) {
        this.id = id;
        this.idSetExam = idSetExam;
        this.idPlayer = idPlayer;
        this.score = score;
        this.playDate = playDate;
    }
    public SetExamPlayer(long idSetExam, long idPlayer, long score, LocalDate playDate) {
        this.idSetExam = idSetExam;
        this.idPlayer = idPlayer;
        this.score = score;
        this.playDate = playDate;
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

    public long getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(long idPlayer) {
        this.idPlayer = idPlayer;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public LocalDate getPlayDate() {
        return playDate;
    }

    public void setPlayDate(LocalDate playDate) {
        this.playDate = playDate;
    }

    @Override
    public String toString() {
        return String.format("%s|%s|%s|%s|%s",
                this.id, this.idSetExam, this.idPlayer, this.score, this.playDate);
    }

    @Override
    public void parse(String line) {
        String[] items = line.split("\\|");

        this.id = Long.parseLong(items[0]);
        this.idSetExam = Long.parseLong(items[1]);
        this.idPlayer = Long.parseLong(items[2]);
        this.score = Integer.parseInt(items[3]);
        this.playDate = DateUtils.parse(items[4]);
    }
}
