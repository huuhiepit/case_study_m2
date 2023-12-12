package game.service.impl;

import game.model.topic_set.SetExamPlayer;

import java.util.List;

public interface ISetExamPlayerService {
    List<SetExamPlayer> getAll();

    List<SetExamPlayer> getHistoryPlayGame(long idPlayer);

    void addPlayGame(SetExamPlayer examPlayer);

    long getMaxId();
}
