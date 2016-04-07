package nl.bhogerheijde.example.rxmvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import nl.bhogerheijde.example.rxmvp.FlickrApp;
import nl.bhogerheijde.example.rxmvp.R;
import nl.bhogerheijde.example.rxmvp.adapter.PhotoAdapter;
import nl.bhogerheijde.example.rxmvp.model.Photo;
import nl.bhogerheijde.example.rxmvp.presenter.PhotoGalleryPresenter;
import nl.bhogerheijde.example.rxmvp.view.PhotoGalleryView;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
public class PhotoGalleryActivity extends AppCompatActivity
        implements PhotoGalleryView, PhotoAdapter.OnPhotoClickListener {

    @Bind(R.id.progress_bar)
    ProgressBar progressBar;

    @Bind(R.id.photo_recycler)
    RecyclerView photoRecycler;

    @Inject
    PhotoGalleryPresenter presenter;

    @Inject
    PhotoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_gallery);
        ButterKnife.bind(this);

        ((FlickrApp) getApplication()).createPhotoGalleryComponent(this).inject(this);

        adapter.setListener(this);

        photoRecycler.setLayoutManager(new GridLayoutManager(this, 3));
        photoRecycler.setAdapter(adapter);

        presenter.loadImages();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_photo_gallery, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                presenter.loadImages();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showProgress() {
        photoRecycler.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void setPhotos(List<Photo> photos) {
        adapter.setPhotos(photos);
    }

    @Override
    public void showPhoto(Photo photo) {
        Intent photoIntent = PhotoActivity.newIntent(this, photo);
        startActivity(photoIntent);
    }

    @Override
    public void showError(String errorMessage) {
        new AlertDialog.Builder(this)
                .setTitle("Error!")
                .setMessage(errorMessage)
                .setPositiveButton(android.R.string.ok, null)
                .create()
                .show();
    }

    @Override
    public void hideProgress() {
        photoRecycler.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onPhotoClicked(Photo photo) {
        presenter.onPhotoClicked(photo);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.finish();
        ((FlickrApp) getApplication()).releasePhotoGalleryComponent();
    }

}
