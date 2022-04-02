package com.example.cinemaworld.pages.collections;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cinemaworld.R;
import com.example.cinemaworld.adapters.CollectionAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CollectionsPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CollectionsPage extends Fragment {
    ArrayList<Collection> collections;
    private RecyclerView recyclerView;
    private CollectionAdapter collectionAdapter;
    private LinearLayoutManager layoutManager;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CollectionsPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CollectionsPage.
     */
    // TODO: Rename and change types and number of parameters
    public static CollectionsPage newInstance(String param1, String param2) {
        CollectionsPage fragment = new CollectionsPage();
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
        View view = inflater.inflate(R.layout.fragment_collections_page, container, false);
        collections = new ArrayList<>();
        view.findViewById(R.id.im_plus).setOnClickListener(view1 -> {
            startActivity(new Intent(getActivity(), BuildingCollection.class));
        });
        collections.add(new Collection(R.drawable.favorite, "Избранное"));
        collections.add(new Collection(R.drawable.music, "Мюзиклы"));

        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView = view.findViewById(R.id.rv_collections);

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        collectionAdapter = new CollectionAdapter(collections, getContext());
        recyclerView.setAdapter(collectionAdapter);
        recyclerView.setLayoutManager(layoutManager);
        collectionAdapter.notifyDataSetChanged();
        return view;
    }
}