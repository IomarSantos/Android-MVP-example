package nl.bhogerheijde.example.rxmvp.ui.photogallery;

import android.app.Application;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import nl.bhogerheijde.example.rxmvp.R;
import nl.bhogerheijde.example.rxmvp.model.Photo;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoHolder> {

    private Picasso picasso;
    private List<Photo> photos;
    private Application application;
    private PhotoGalleryPresenter presenter;

    public PhotoAdapter(Picasso picasso, PhotoGalleryPresenter presenter, Application application) {
        this.picasso = picasso;
        this.photos = new ArrayList<>();
        this.presenter = presenter;
        this.application = application;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    @Override
    public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View photoItemView = LayoutInflater.from(application).inflate(R.layout.grid_item_photo, parent, false);
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

}
