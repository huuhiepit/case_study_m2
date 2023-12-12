package game;

import game.service.QuestionService;
import game.view.PlayerView;
import game.view.QuestionView;
import game.view.TopicSetView;

public class GameApplication {
    protected QuestionView questionView;
    protected QuestionService questionService;
    protected TopicSetView topicSetView;
    protected PlayerView playerView;
    public GameApplication() {
        questionView = new QuestionView();
        topicSetView = new TopicSetView();
        playerView = new PlayerView();
    }
    public void run() {
//        questionView.launcher();
//        topicSetView.launcher();
        playerView.launcher();
    }

    public static void main(String[] args) {
        GameApplication game = new GameApplication();
        game.run();
    }
}
