package com.hassan.moneyexchangeapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    TextView result;
    double usamount;
    EditText usdollarEditText;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_main, container, false);

        result = (TextView)fragmentView.findViewById(R.id.result);
        usdollarEditText = (EditText)fragmentView.findViewById(R.id.usamount);


        Button button = (Button) fragmentView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    usamount = Double.parseDouble(usdollarEditText.getText().toString());

                    double franc = 0;
                    franc = usamount * 439.36;
                    DecimalFormat format = new DecimalFormat("#.00");
                    result.setText(usamount + " US Dollar equal " + Double.valueOf(format.format(franc)) + " Comorian Francs");
                }catch (NumberFormatException e){
                    result.setText("Please check your input");
                }
            }
        });

        return fragmentView;
    }
}
