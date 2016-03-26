package nl.bhogerheijde.example.rxmvp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Flickr app built with RxJava and MVP pattern, file created on 26/03/16.
 *
 * @author Boyd Hogerheijde
 */
public class Photos {

    @SerializedName(value = "photo")
    private List<Photo> photos;

    public List<Photo> getPhotoList() {
        return photos;
    }

}
