package tokenlabapp.bm.com.tokenlabapp;

public class Game {
    private String id;
    private String name;
    private String image;
    private String release_date;
    private String trailer;
    private String[] platforms;

    public Game(String id, String name, String image, String release_date, String trailer, String[] platforms) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.release_date = release_date;
        this.trailer = trailer;
        this.platforms = platforms;
    }

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

    public String[] getPlatforms() {
        return platforms;
    }
}