package nl.bhogerheijde.example.rxmvp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Flickr app built with RxJava and MVP pattern, file created on 26/03/16.
 *
 * @author Boyd Hogerheijde
 */
public class Flickr {

    @SerializedName(value = "photos")
    private Photos photos;

    public Photos getPhotosObject() {
        return photos;
    }

}
