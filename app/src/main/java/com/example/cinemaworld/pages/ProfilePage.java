package com.example.cinemaworld.pages;

import static android.content.ContentValues.TAG;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cinemaworld.R;
import com.example.cinemaworld.network.ApiHandler;
import com.example.cinemaworld.network.profile.models.GetProfileResponse;
import com.example.cinemaworld.network.profile.service.GetProfileService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfilePage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfilePage extends Fragment {
    GetProfileService service = ApiHandler.getInstance().getProfileService();

    TextView txtName, txtEmail;
    ImageView imgAvatar;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfilePage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfilePage.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfilePage newInstance(String param1, String param2) {
        ProfilePage fragment = new ProfilePage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_profile_page, container, false);
        txtName = view.findViewById(R.id.txt_name);
        txtEmail = view.findViewById(R.id.txt_email);
        AsyncTask.execute(() -> {
            service.getData("Bearer 834304").enqueue(new Callback<List<GetProfileResponse>>() {
                @Override
                public void onResponse(Call<List<GetProfileResponse>> call, Response<List<GetProfileResponse>> response) {
                    Log.d(TAG, "onResponse: " + response.body().get(0).getFirstName());
                    txtEmail.setText(response.body().get(0).getEmail());
                    String name = response.body().get(0).getFirstName() + " " + response.body().get(0).getLastName();
                    txtName.setText(name);
                }

                @Override
                public void onFailure(Call<List<GetProfileResponse>> call, Throwable t) {
                    Log.d(TAG, "Не крут ");
                }
            });
        });

        return view;
    }
}