package tokenlabapp.bm.com.tokenlabapp;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {

    private Retrofit retrofit;
    private Api api;
    private Call<User> call;

    private ImageView userImage;
    private TextView userName;
    private TextView userEmail;
    private TextView userBirthday;
    private TextView userAddress;
    private TextView userCity;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        userImage = view.findViewById(R.id.userImage);
        userName = view.findViewById(R.id.userName);
        userEmail = view.findViewById(R.id.userEmail);
        userBirthday = view.findViewById(R.id.userBirthday);
        userAddress = view.findViewById(R.id.userAddress);
        userCity = view.findViewById(R.id.userCity);

        retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_USER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(Api.class);

        call = api.getUser();

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                if (user.getAvatar() != null)
                    Picasso.get()
                            .load(user.getAvatar())
                            .error(R.drawable.imagenotfound)
                            .into(userImage);
                if (user.getName() != null) {
                    userName.setText(user.getName() + " " + user.getLastname());
                }
                if (user.getEmail() != null) {
                    userEmail.setText("E-mail: " + user.getEmail());
                }
                if (user.getBirthday() != null) {
                    userBirthday.setText("Birthday: " + user.getBirthday());
                }
                if (user.getAddress() != null) {
                    userAddress.setText("Address: " + user.getAddress());
                }
                if (user.getCity() != null) {
                    userCity.setText("City/Country: " + user.getCity() + "/" + user.getCountry());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

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

}