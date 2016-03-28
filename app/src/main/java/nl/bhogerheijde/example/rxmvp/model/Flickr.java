package nl.bhogerheijde.example.rxmvp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
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
