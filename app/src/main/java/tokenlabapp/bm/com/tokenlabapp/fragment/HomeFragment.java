package tokenlabapp.bm.com.tokenlabapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tokenlabapp.bm.com.tokenlabapp.R;
import tokenlabapp.bm.com.tokenlabapp.adapter.RVAdapter;
import tokenlabapp.bm.com.tokenlabapp.model.GameList;
import tokenlabapp.bm.com.tokenlabapp.service.GameService;

public class HomeFragment extends Fragment {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    private Retrofit retrofit;
    private GameService gameService;
    private Call<GameList> call;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initProgressBar(view);
        initRecyclerView(view);
        initRetrofit();
        initCall();
    }

    private void initProgressBar(View view) {
        progressBar = view.findViewById(R.id.home_progress_bar);
    }

    private void showProgressBar(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void initRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.home_recycler_view);
        recyclerView.setHasFixedSize(true);
    }

    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(GameService.BASE_GAMES_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        gameService = retrofit.create(GameService.class);
    }

    private void initCall() {
        call = gameService.getGames();
        showProgressBar(true);
        call.enqueue(new Callback<GameList>() {
            @Override
            public void onResponse(Call<GameList> call, Response<GameList> response) {
                GameList games = response.body();
                if (games.getGames() != null) {
                    RVAdapter adapter = new RVAdapter(games.getGames());
                    recyclerView.setAdapter(adapter);
                    showProgressBar(false);
                }
            }

            @Override
            public void onFailure(Call<GameList> call, Throwable t) {
                Toast.makeText(getContext(), "Game data request failed", Toast.LENGTH_LONG).show();
                showProgressBar(false);
            }
        });
    }

}