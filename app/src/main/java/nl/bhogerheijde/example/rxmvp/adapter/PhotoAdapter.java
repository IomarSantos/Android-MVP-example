package nl.bhogerheijde.example.rxmvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
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
 * Flickr app built with RxJava and MVP pattern, file created on 26/03/16.
 *
 * @author Boyd Hogerheijde
 */
public class PhotoAdapter extends Adapter<PhotoAdapter.PhotoHolder> {

    private Context context;
    private List<Photo> photos;

    public PhotoAdapter(Context context) {
        this.context = context;
        photos = new ArrayList<>();
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    @Override
    public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View photoItemView = LayoutInflater.from(context).inflate(R.layout.grid_item_photo, parent, false);
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

    class PhotoHolder extends ViewHolder {

        @Bind(R.id.photo_image_view) ImageView photoView;

        public PhotoHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindPhoto(Photo photo) {
            Picasso.with(context)
                    .load(photo.getUrlSmall())
                    .into(photoView);
        }

    }

}
