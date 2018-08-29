package tokenlabapp.bm.com.tokenlabapp.model;

import java.util.List;

import tokenlabapp.bm.com.tokenlabapp.model.Game;

public class GameList {
    private List<Game> games;

    public GameList(List<Game> games) {
        this.games = games;
    }

    public List<Game> getGames() {
        return games;
    }
}
