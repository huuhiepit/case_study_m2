package game.service;

import game.model.player.Player;
import game.service.impl.IPlayerService;
import game.utils.Config;
import game.utils.FileUtils;

import java.util.List;

public class PlayerService implements IPlayerService {
    @Override
    public List<Player> getAll() {
        return FileUtils.readFile(Config.PATH_PLAYER, Player.class);
    }
}
