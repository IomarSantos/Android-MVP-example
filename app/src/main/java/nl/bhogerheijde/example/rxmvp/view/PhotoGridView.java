package nl.bhogerheijde.example.rxmvp.view;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import nl.bhogerheijde.example.rxmvp.R;
import nl.bhogerheijde.example.rxmvp.adapter.PhotoAdapter;
import nl.bhogerheijde.example.rxmvp.model.Flickr;
import nl.bhogerheijde.example.rxmvp.presenter.FlickrPresenter;
import nl.bhogerheijde.example.rxmvp.presenter.IPresenter;
import nl.bhogerheijde.example.rxmvp.service.FlickrService;
import nl.bhogerheijde.example.rxmvp.service.FlickrService.FlickrApi;

/**
 * Flickr app built with RxJava and MVP pattern, file created on 26/03/16.
 *
 * @author Boyd Hogerheijde
 */
public class PhotoGridView extends FrameLayout implements IView<Flickr> {

    @Bind(R.id.progress_bar) ProgressBar progressBar;
    @Bind(R.id.photo_recycler) RecyclerView photoRecycler;

    private IPresenter<Flickr> flickrPresenter;
    private PhotoAdapter photoAdapter;

    public PhotoGridView(Context context) {
        this(context, null);
    }

    public PhotoGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView(context);
    }

    private void initializeView(Context context) {
        View photoGridView = inflate(context, R.layout.grid_view_photo, this);
        ButterKnife.bind(this, photoGridView);

        FlickrApi flickrApi = FlickrService.getInstance().getFlickrApi();

        flickrPresenter = new FlickrPresenter(flickrApi);
        flickrPresenter.setView(this);

        photoAdapter = new PhotoAdapter(context);

        photoRecycler.setLayoutManager(new GridLayoutManager(context, 3));
        photoRecycler.setAdapter(photoAdapter);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        flickrPresenter.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        flickrPresenter.finish();
    }

    @Override
    public void setPhotos(Flickr flickr) {
        photoAdapter.setPhotos(flickr.getPhotosObject().getPhotoList());
        photoAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress() {
        photoRecycler.setVisibility(INVISIBLE);
        progressBar.setVisibility(VISIBLE);
    }

    @Override
    public void hideProgress() {
        photoRecycler.setVisibility(VISIBLE);
        progressBar.setVisibility(INVISIBLE);
    }

    @Override
    public void showError(Throwable e) {
        // Handle error.
    }
}
