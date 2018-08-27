package tokenlabapp.bm.com.tokenlabapp;

import java.util.List;

public class GameList {
    private List<Game> games;

    public GameList(List<Game> games) {
        this.games = games;
    }

    public List<Game> getGames() {
        return games;
    }

    public int getSize() {
        return games.size();
    }
}
