package tokenlabapp.bm.com.tokenlabapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<GameList> call = api.getGames();

        call.enqueue(new Callback<GameList>() {
            @Override
            public void onResponse(Call<GameList> call, Response<GameList> response) {
                GameList games = response.body();
                for(Game g: games.getGames()) {
                    Log.d("id", g.getId());
                    Log.d("name", g.getName());
                    Log.d("image", g.getImage());
                    Log.d("release_date", g.getRelease_date());
                    Log.d("trailer", g.getTrailer());
                    Log.d("platforms", g.getPlatforms().toString());
                }
            }

            @Override
            public void onFailure(Call<GameList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
