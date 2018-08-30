package tokenlabapp.bm.com.tokenlabapp.model;

import java.util.List;

public class Game {
    private String id;
    private String name;
    private String image;
    private String release_date;
    private String trailer;
    private List<String> platforms;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getTrailer() {
        return trailer;
    }

    public List<String> getPlatforms() {
        return platforms;
    }
}