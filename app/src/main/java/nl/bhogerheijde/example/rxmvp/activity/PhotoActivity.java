package nl.bhogerheijde.example.rxmvp.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import nl.bhogerheijde.example.rxmvp.FlickrApp;
import nl.bhogerheijde.example.rxmvp.R;
import nl.bhogerheijde.example.rxmvp.di.module.PhotoModule;
import nl.bhogerheijde.example.rxmvp.model.Photo;
import nl.bhogerheijde.example.rxmvp.presenter.PhotoPresenter;
import nl.bhogerheijde.example.rxmvp.view.PhotoView;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
public class PhotoActivity extends AppCompatActivity implements PhotoView {

    private static final String EXTRA_PHOTO = "nl.bhogerheijde.example.rxmvp.extra_photo";

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.photo_image_view)
    ImageView photoImageView;

    @Bind(R.id.progress_bar)
    ProgressBar progressBar;

    @Inject
    PhotoPresenter presenter;

    public static Intent newIntent(Context context, Photo photo) {
        Intent intent = new Intent(context, PhotoActivity.class);
        intent.putExtra(EXTRA_PHOTO, photo);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        ButterKnife.bind(this);

        Photo photo = (Photo) getIntent().getSerializableExtra(EXTRA_PHOTO);

        ((FlickrApp) getApplication()).getApplicationComponent()
                .plus(new PhotoModule(photo.getUrlLarge()))
                .inject(this);

        setActionBar();

        presenter.setView(this);
        presenter.loadImage(photo);
    }

    private void setActionBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        photoImageView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setPhoto(Bitmap photoBitmap) {
        photoImageView.setImageBitmap(photoBitmap);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
        photoImageView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.finish();
    }
}
