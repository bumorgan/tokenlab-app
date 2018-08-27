package tokenlabapp.bm.com.tokenlabapp;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.GameViewHolder>{

    List<Game> games;

    RVAdapter(List<Game> games){
        this.games = games;
    }

    public static class GameViewHolder extends RecyclerView.ViewHolder {
        TextView gameName;
        TextView releaseDate;
        TextView platforms;
        SmartImageView gameImage;

        GameViewHolder(View itemView) {
            super(itemView);
            gameName = itemView.findViewById(R.id.game_name);
            releaseDate = itemView.findViewById(R.id.release_date);
            platforms = itemView.findViewById(R.id.platforms);
            gameImage = itemView.findViewById(R.id.game_image);
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
    public void onBindViewHolder(GameViewHolder gameViewHolder, int i) {
        if (games.get(i).getName() != null) {
            gameViewHolder.gameName.setText(games.get(i).getName());
        }
        if (games.get(i).getRelease_date() != null) {
            gameViewHolder.releaseDate.setText("Release date: " + games.get(i).getRelease_date());
        }
        if (games.get(i).getPlatforms() != null) {
            gameViewHolder.platforms.setText("Platforms: " + games.get(i).getPlatforms());
        }
        if (games.get(i).getImage() != null) {
            gameViewHolder.gameImage.setImageUrl(games.get(i).getImage());
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
