package nl.bhogerheijde.example.rxmvp.ui.photogallery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import nl.bhogerheijde.example.rxmvp.R;
import nl.bhogerheijde.example.rxmvp.model.Photo;
import nl.bhogerheijde.example.rxmvp.ui.common.BaseActivity;
import nl.bhogerheijde.example.rxmvp.ui.photo.PhotoActivity;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
public class PhotoGalleryActivity extends BaseActivity implements PhotoGalleryView {

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
    public List<Object> getModules() {
        return Arrays.asList(new PhotoGalleryModule(this));
    }

    @Override
    public void showProgress() {
        photoRecycler.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void setPhotos(List<Photo> photos) {
        adapter.setPhotos(photos);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void openPhoto(Photo photo) {
        Intent photoIntent = PhotoActivity.newIntent(this, photo);
        startActivity(photoIntent);
    }

    @Override
    public void showError(Throwable e) {
        new AlertDialog.Builder(this)
                .setTitle("Error!")
                .setMessage(e.getMessage())
                .setPositiveButton(android.R.string.ok, null)
                .create()
                .show();
    }

    @Override
    public void hideProgress() {
        photoRecycler.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }

}
