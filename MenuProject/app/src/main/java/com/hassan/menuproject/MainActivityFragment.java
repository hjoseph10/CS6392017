package com.hassan.menuproject;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_main, container, false);

        final String phone = "3474478049";
//        button to load sms app
        Button sms_button = (Button) fragmentView.findViewById(R.id.sms_id);
        sms_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final Intent sms_intent = new Intent(Intent.ACTION_SENDTO);
                sms_intent.setData(Uri.parse("smsto:" + Uri.encode(phone)));
                sms_intent.putExtra("sms_body:", "Hey Hassan");
                startActivity(sms_intent);
            }
        });

//        button to load phone app
        Button call_button = (Button) fragmentView.findViewById(R.id.phone_id);
        call_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final Intent phone_intent = new Intent(Intent.ACTION_DIAL);
                phone_intent.setData(Uri.parse("tel:" + Uri.encode(phone)));
                startActivity(phone_intent);
            }
        });

//        button to open web browser app
        Button web_button = (Button) fragmentView.findViewById(R.id.web_id);
        web_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final Intent web_intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.hassanjoseph.com/"));
                startActivity(web_intent);
            }
        });

//        button to open map
        Button map_button = (Button) fragmentView.findViewById(R.id.map_id);
        map_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final Intent map_intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("geo:18.445292, -73.089290")));
                startActivity(map_intent);
            }
        });

//        button to open share menu
        Button share_button = (Button) fragmentView.findViewById(R.id.share_id);
        share_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final Intent share_intent = new Intent(Intent.ACTION_SEND);
                startActivity(share_intent.createChooser(share_intent, "Share the Love"));
            }
        });

//        button to open new activity
        Button new_activity_button = (Button) fragmentView.findViewById(R.id.new_activity_id);
        new_activity_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final Intent new_activity_button_intent = new Intent(getActivity(), NewActivity.class);
                startActivity(new_activity_button_intent);
            }
        });

        return fragmentView;
    }
}
