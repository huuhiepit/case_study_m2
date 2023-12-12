package game.service;

import game.model.topic_set.SetExamPlayer;
import game.service.impl.ISetExamPlayerService;
import game.utils.Config;
import game.utils.FileUtils;


import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SetExmPlayerService implements ISetExamPlayerService {

    @Override
    public List<SetExamPlayer> getAll() {
        return FileUtils.readFile(Config.PATH_SET_EXAM_PLAYER, SetExamPlayer.class);
    }

    @Override
    public List<SetExamPlayer> getHistoryPlayGame(long idPlayer) {
        return getAll().stream().filter(setExamPlayer -> setExamPlayer.getIdPlayer() == idPlayer).collect(Collectors.toList());
    }

    @Override
    public void addPlayGame(SetExamPlayer examPlayer) {
        FileUtils.writeFile(Config.PATH_SET_EXAM_PLAYER, examPlayer);
    }
    @Override
    public long getMaxId() {
        return getAll().stream().max(Comparator.comparingLong(SetExamPlayer::getId)).orElseThrow().getId();
    }
}
