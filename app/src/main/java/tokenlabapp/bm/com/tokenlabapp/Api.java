package tokenlabapp.bm.com.tokenlabapp;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "https://dl.dropboxusercontent.com/s/1b7jlwii7jfvuh0/";

    @GET("games")
    Call<GameList> getGames();
}
