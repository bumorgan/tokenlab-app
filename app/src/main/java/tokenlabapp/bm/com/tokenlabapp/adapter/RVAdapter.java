package tokenlabapp.bm.com.tokenlabapp.adapter;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.YouTubePlayerInitListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import tokenlabapp.bm.com.tokenlabapp.model.Game;
import tokenlabapp.bm.com.tokenlabapp.R;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.GameViewHolder>{

    private List<Game> games;

    public RVAdapter(List<Game> games){
        this.games = games;
    }

    public static class GameViewHolder extends RecyclerView.ViewHolder {
        private TextView gameName;
        private TextView releaseDate;
        private TextView platforms;
        private ImageView gameImage;
        private Button trailerButton;

        GameViewHolder(final View itemView) {
            super(itemView);
            gameName = itemView.findViewById(R.id.game_name);
            releaseDate = itemView.findViewById(R.id.release_date);
            platforms = itemView.findViewById(R.id.platforms);
            gameImage = itemView.findViewById(R.id.game_image);
            trailerButton = itemView.findViewById(R.id.trailer_button);
        }
        public TextView getGameName() {
            return gameName;
        }
        public TextView getReleaseDate() {
            return releaseDate;
        }
        public TextView getPlatforms() {
            return platforms;
        }
        public ImageView getGameImage() {
            return gameImage;
        }
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    @Override
    public GameViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_cardview, viewGroup, false);
        GameViewHolder gameViewHolder = new GameViewHolder(v);
        return gameViewHolder;
    }

    @Override
    public void onBindViewHolder(final GameViewHolder gameViewHolder, final int i) {
        if (games.get(i).getName() != null)
            gameViewHolder.getGameName().setText(games.get(i).getName());
        if (games.get(i).getRelease_date() != null)
            gameViewHolder.getReleaseDate().setText("Release date: " + games.get(i).getRelease_date());
        if (games.get(i).getPlatforms() != null)
            gameViewHolder.getPlatforms().setText("Platforms: " + TextUtils.join(", ", games.get(i).getPlatforms()));
        if (games.get(i).getImage() != null)
            Picasso.get()
                    .load(games.get(i).getImage())
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.imagenotfound)
                    .into(gameViewHolder.getGameImage());
        if (games.get(i).getTrailer() != null) {
            gameViewHolder.trailerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog dialog = new Dialog(v.getContext());
                    dialog.setContentView(R.layout.layout_trailer);
                    dialog.show();
                    com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayerView youTubePlayerView = dialog.findViewById(R.id.youtube_player_view);
                    youTubePlayerView.initialize(new YouTubePlayerInitListener() {
                        @Override
                        public void onInitSuccess(@NonNull final com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayer youTubePlayer) {
                            youTubePlayer.addListener(new AbstractYouTubePlayerListener() {
                                @Override
                                public void onReady() {
                                    youTubePlayer.loadVideo(games.get(i).getTrailer().split("=")[1],0);
                                }
                            });
                        }
                    }, true);
                }
            });
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}