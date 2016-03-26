package nl.bhogerheijde.example.rxmvp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import nl.bhogerheijde.example.rxmvp.R;

/**
 * Flickr app built with RxJava and MVP pattern, file created on 26/03/16.
 *
 * @author Boyd Hogerheijde
 */
public class PhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
    }

}
