package tokenlabapp.bm.com.tokenlabapp;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_GAMES_URL = "https://dl.dropboxusercontent.com/s/1b7jlwii7jfvuh0/";
    String BASE_USER_URL = "https://dl.dropboxusercontent.com/s/fiqendqz4l1xk61/";

    @GET("games")
    Call<GameList> getGames();

    @GET("userinfo")
    Call<User> getUser();
}