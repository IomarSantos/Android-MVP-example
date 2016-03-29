package nl.bhogerheijde.example.rxmvp.ui.photogallery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import nl.bhogerheijde.example.rxmvp.R;
import nl.bhogerheijde.example.rxmvp.model.Photo;
import nl.bhogerheijde.example.rxmvp.ui.BaseActivity;
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
    Picasso picasso;

    private PhotoAdapter photoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_gallery);
        ButterKnife.bind(this);

        photoAdapter = new PhotoAdapter();

        photoRecycler.setLayoutManager(new GridLayoutManager(this, 3));
        photoRecycler.setAdapter(photoAdapter);

        presenter.loadImages();
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
        photoAdapter.setPhotos(photos);
        photoAdapter.notifyDataSetChanged();
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

    class PhotoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.photo_image_view)
        ImageView photoView;

        private Photo photo;

        public PhotoHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void bindPhoto(Photo photo) {
            this.photo = photo;
            picasso.load(photo.getUrlSmall()).into(photoView);
        }

        @Override
        public void onClick(View v) {
            presenter.onPhotoClicked(photo);
        }
    }

    class PhotoAdapter extends RecyclerView.Adapter<PhotoHolder> {

        private List<Photo> photos;

        public PhotoAdapter() {
            photos = new ArrayList<>();
        }

        public void setPhotos(List<Photo> photos) {
            this.photos = photos;
        }

        @Override
        public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View photoItemView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.grid_item_photo, parent, false);
            return new PhotoHolder(photoItemView);
        }

        @Override
        public void onBindViewHolder(PhotoHolder holder, int position) {
            holder.bindPhoto(photos.get(position));
        }

        @Override
        public int getItemCount() {
            return photos.size();
        }

    }
}
