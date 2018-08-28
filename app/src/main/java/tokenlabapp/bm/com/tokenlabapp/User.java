package tokenlabapp.bm.com.tokenlabapp;

public class User {
    private String name;
    private String lastname;
    private String avatar;
    private String email;
    private String birthday;
    private String addres;
    private String city;
    private String country;
    private String token;

    public User(String name, String lastname, String avatar, String email, String birthday, String addres, String city, String country, String token) {
        this.name = name;
        this.lastname = lastname;
        this.avatar = avatar;
        this.email = email;
        this.birthday = birthday;
        this.addres = addres;
        this.city = city;
        this.country = country;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getAddres() {
        return addres;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getToken() {
        return token;
    }
}
