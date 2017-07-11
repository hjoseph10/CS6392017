package com.hassan.stateslistproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    View fragmentView;
//    public static final String LAT = "";

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_main, container, false);
        final ListView listview = (ListView)fragmentView.findViewById(R.id.listview);

        String[][] states = new String[][]{
                {"Alabama",""},
                {"Alaska",""},
                {"Arizona",""},
                {"Arkansas",""},
                {"California",""},
                {"Colorado",""},
                {"Connecticut",""},
                {"Delaware",""},
                {"Florida",""},
                {"Georgia",""},
                {"Hawaii",""},
                {"Idaho",""},
                {"Illinois","39.7638375,-89.6708313"},
                {"Indiana",""},
                {"Iowa",""},
                {"Kansas",""},
                {"Kentucky",""},
                {"Louisiana",""},
                {"Maine",""},
                {"Maryland",""},
                {"Massachusetts",""},
                {"Michigan",""},
                {"Minnesota",""},
                {"Mississippi",""},
                {"Missouri",""},
                {"Montana",""},
                {"Nebraska",""},
                {"Nevada",""},
                {"New Hampshire",""},
                {"New Jersey",""},
                {"New Mexico",""},
                {"New York",""},
                {"North Carolina",""},
                {"North Dakota",""},
                {"Ohio",""},
                {"Oklahoma",""},
                {"Oregon",""},
                {"Pennsylvania",""},
                {"Rhode Island",""},
                {"South Carolina",""},
                {"South Dakota",""},
                {"Tennessee",""},
                {"Texas",""},
                {"Utah",""},
                {"Vermont",""},
                {"Virginia",""},
                {"Washington",""},
                {"West Virginia",""},
                {"Wisconsin",""},
                {"Wyoming", ""}

//                {"Illinois", "39.7638375,-89.6708313"},
        };
//        String[][] states = new String[][]{
//                {"Hawaii", "geo:0,0?q=Honolulu,HI"},
//                {"Idaho", "geo:0,0?q=Boise,ID"},
//                {"Illinois", "geo:0,0?q=Springfield,IL"},
//                {"Indiana", "geo:0,0?q=Indianapolis,IN"},
//                {"Iowa", "geo:0,0?q=Des+Moines,IA"},
//        };

        final ArrayList<State> list = new ArrayList<State>();
        for (int i = 0; i < states.length; ++i) {
            list.add(new State(states[i][0], states[i][1]));
        }
        final MyArrayAdapter adapter = new MyArrayAdapter(this.getContext(), android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                String geolocation = adapter.getGeolocation(position);

                final Intent recipesactivity = new Intent(getActivity(), MapsActivity.class);
                if (geolocation.split(",").length != 2){
                    Toast.makeText(fragmentView.getContext(), "The Capital Location is Only Defined for Illinois", Toast.LENGTH_LONG).show();
                }else {
                    recipesactivity.putExtra("LAT", Double.parseDouble(geolocation.split(",")[0]));
                    recipesactivity.putExtra("LNG", Double.parseDouble(geolocation.split(",")[1]));
                    startActivity(recipesactivity);
                }

//                try{
//                    final Intent map_intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format(geolocation)));
//                    map_intent.setPackage("com.google.android.apps.maps");
//                    startActivity(map_intent);
//                }catch (Exception e){
//                    Toast.makeText(fragmentView.getContext(), "The location was not found. Please try again later", Toast.LENGTH_LONG).show();
//                }
            }
        });

        return fragmentView;
    }

    private class MyArrayAdapter extends ArrayAdapter<State> {
        public MyArrayAdapter(Context context, int textViewResourceId, List<State> objects) {
            super(context, textViewResourceId, objects);
        }

        public String getGeolocation(int position) {
            State state = getItem(position);
            return state.getGeolocation();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = super.getView(position, convertView, parent);
            if(position % 2 == 1) view.setBackgroundColor(Color.rgb(245,245,245));
            else view.setBackgroundColor(Color.WHITE);

            return view;
        }
    }

    private class State {
        private String name;
        private String geolocation_val;

        public State(String state, String geolocation) {
            name = state;
            geolocation_val = geolocation;
        }

        public String toString(){
            return name;
        }

        public String getGeolocation() {
            return geolocation_val;
        }
    }
}
