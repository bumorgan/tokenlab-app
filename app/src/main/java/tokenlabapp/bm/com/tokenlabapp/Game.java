package tokenlabapp.bm.com.tokenlabapp;

public class Game {
    private int id;
    private String name;
    private String image;
    private String releaseDate;
    private String trailer;
    private String[] platforms;

    public Game(int id, String name, String image, String releaseDate, String trailer, String[] platforms) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.releaseDate = releaseDate;
        this.trailer = trailer;
        this.platforms = platforms;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String[] getPlatforms() {
        return platforms;
    }

    public void setPlatforms(String[] platforms) {
        this.platforms = platforms;
    }
}