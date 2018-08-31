package tokenlabapp.bm.com.tokenlabapp.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tokenlabapp.bm.com.tokenlabapp.R;
import tokenlabapp.bm.com.tokenlabapp.model.User;
import tokenlabapp.bm.com.tokenlabapp.service.UserService;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {

    private ProgressBar progressBar;

    private ImageView userImage;
    private TextView userName;
    private TextView userEmail;
    private TextView userBirthday;
    private TextView userAddress;
    private TextView userCity;

    private Retrofit retrofit;
    private UserService userService;
    private Call<User> call;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initProgressBar(view);

        initUserImage(view);
        initUserName(view);
        initUserEmail(view);
        initUserBirthday(view);
        initUserAdress(view);
        initUserCity(view);

        initRetrofit();

        initCall();
    }

    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(UserService.BASE_USER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        userService = retrofit.create(UserService.class);
    }

    private void initProgressBar(View view) {
        progressBar = view.findViewById(R.id.user_progress_bar);
    }

    private void showProgressBar(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void initUserImage(View view) {
        userImage = view.findViewById(R.id.user_image);
    }

    private void initUserName(View view) {
        userName = view.findViewById(R.id.user_name);
    }

    private void initUserEmail (View view) {
        userEmail = view.findViewById(R.id.user_email);
    }

    private void initUserBirthday(View view) {
        userBirthday = view.findViewById(R.id.user_birthday);
    }

    private void initUserAdress(View view) {
        userAddress = view.findViewById(R.id.user_address);
    }

    private void initUserCity(View view) {
        userCity = view.findViewById(R.id.user_city);
    }

    private void initCall() {
        call = userService.getUser();
        showProgressBar(true);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                if (user.getAvatar() != null)
                    Picasso.get()
                            .load(user.getAvatar())
                            .placeholder(R.drawable.loading)
                            .error(R.drawable.imagenotfound)
                            .into(userImage);
                if (user.getName() != null)
                    userName.setText(user.getName() + " " + user.getLastname());

                if (user.getEmail() != null)
                    userEmail.setText("E-mail: " + user.getEmail());

                if (user.getBirthday() != null) {
                    userBirthday.setText("Birthday: " + user.getBirthday().split("T")[0].replace("-", "/"));
                }

                if (user.getAddress() != null)
                    userAddress.setText("Address: " + user.getAddress());

                if (user.getCity() != null)
                    userCity.setText("City/Country: " + user.getCity() + "/" + user.getCountry());

                showProgressBar(false);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getContext(), "User data request failed", Toast.LENGTH_LONG).show();
            }
        });
    }

}