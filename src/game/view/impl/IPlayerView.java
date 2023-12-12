package game.view.impl;

import game.model.player.Player;
import game.model.question.Question;
import game.model.topic_set.SetExam;
import game.model.topic_set.SetExamPlayer;

import java.util.List;

public interface IPlayerView {
    void playGame(Player player); //Chơi game

    List<Question> questionListGame(SetExam setExam);

    boolean showGameQuestion(int stt, Question question);
    void showListHistory(Player player);

    void showHistory(SetExamPlayer setExamPlayer);

}
