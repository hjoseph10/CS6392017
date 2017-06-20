package com.hassan.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    ImageView imageView = null;
    TextView errorMsg = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        imageView = (ImageView) findViewById(R.id.imageView);
        errorMsg = (TextView) findViewById(R.id.errormsg);
        new LoadImage().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class LoadImage extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bmpa =null;
            try{

                URL url = new URL("https://raw.githubusercontent.com/hjoseph10/CS6392017/master/miragoaneHaiti.jpg");
                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                if(con.getResponseCode() != 200){
                    throw new Exception("Failled to connect");
                }

                InputStream is = con.getInputStream();
                bmpa = BitmapFactory.decodeStream(is);
                is.close();
            }catch (Exception e){
                Log.e("Image", "Failed to load image", e);
                Log.e("error", e.getMessage());
            }
            return bmpa;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            System.out.println("aaaaaa"+result);
            if(result != null)
                imageView.setImageBitmap(result);
            else
                errorMsg.setText("Unknown error occurred. Please try again later");
        }
    }
}
