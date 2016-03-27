package nl.bhogerheijde.example.rxmvp.activity;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import nl.bhogerheijde.example.rxmvp.R;
import nl.bhogerheijde.example.rxmvp.model.Photo;
import nl.bhogerheijde.example.rxmvp.presenter.FlickrPresenter;
import nl.bhogerheijde.example.rxmvp.presenter.FlickrPresenterImpl;
import nl.bhogerheijde.example.rxmvp.view.FlickrView;

/**
 * Flickr app built with RxJava and MVP pattern, file created on 26/03/16.
 *
 * @author Boyd Hogerheijde
 */
public class FlickrActivity extends AppCompatActivity implements FlickrView {

    @Bind(R.id.progress_bar)
    ProgressBar progressBar;

    @Bind(R.id.photo_recycler)
    RecyclerView photoRecycler;

    private PhotoAdapter photoAdapter;
    private FlickrPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        ButterKnife.bind(this);

        presenter = new FlickrPresenterImpl(this);

        photoAdapter = new PhotoAdapter();

        photoRecycler.setLayoutManager(new GridLayoutManager(this, 3));
        photoRecycler.setAdapter(photoAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void setPhotos(List<Photo> photos) {
        photoAdapter.setPhotos(photos);
        photoAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress() {
        photoRecycler.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        photoRecycler.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showError(Throwable e) {
        new AlertDialog.Builder(this)
                .setTitle("Error!")
                .setMessage("An error occurred while trying to fetch photos of of Flickr.")
                .setPositiveButton(android.R.string.ok, null)
                .create()
                .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    class PhotoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.photo_image_view)
        ImageView photoView;

        private Photo photo;

        public PhotoHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindPhoto(Photo photo) {
            this.photo = photo;
            Picasso.with(getApplicationContext())
                    .load(photo.getUrlSmall())
                    .into(photoView);
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
