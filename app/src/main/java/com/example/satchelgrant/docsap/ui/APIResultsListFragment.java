package com.example.satchelgrant.docsap.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.satchelgrant.docsap.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link APIResultsListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link APIResultsListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class APIResultsListFragment extends Fragment {


    public APIResultsListFragment() {
        // Required empty public constructor
    }

    public static APIResultsListFragment newInstance() {
        APIResultsListFragment fragment = new APIResultsListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_apiresults_list2, container, false);
    }

}
