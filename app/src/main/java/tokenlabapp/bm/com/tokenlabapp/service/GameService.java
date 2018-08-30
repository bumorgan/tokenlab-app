package tokenlabapp.bm.com.tokenlabapp.service;

import retrofit2.Call;
import retrofit2.http.GET;
import tokenlabapp.bm.com.tokenlabapp.model.GameList;

public interface GameService {
    String BASE_GAMES_URL = "https://dl.dropboxusercontent.com/s/1b7jlwii7jfvuh0/";

    @GET("games")
    Call<GameList> getGames();
}