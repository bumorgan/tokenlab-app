package tokenlabapp.bm.com.tokenlabapp.service;

import retrofit2.Call;
import retrofit2.http.GET;
import tokenlabapp.bm.com.tokenlabapp.model.User;

public interface UserService {
    String BASE_USER_URL = "https://dl.dropboxusercontent.com/s/fiqendqz4l1xk61/";

    @GET("userinfo")
    Call<User> getUser();
}
