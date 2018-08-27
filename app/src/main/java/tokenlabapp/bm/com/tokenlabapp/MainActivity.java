package tokenlabapp.bm.com.tokenlabapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);

//        LinearLayoutManager llm = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(llm);

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
                RVAdapter adapter = new RVAdapter(games.getGames());
                recyclerView.setAdapter(adapter);
//                for(Game g: games.getGames()) {
//                    Log.d("id", g.getId());
//                    Log.d("name", g.getName());
//                    Log.d("image", g.getImage());
//                    Log.d("release_date", g.getRelease_date());
//                    Log.d("trailer", g.getTrailer());
//                    Log.d("platforms", g.getPlatforms().toString());
//                }
            }

            @Override
            public void onFailure(Call<GameList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
