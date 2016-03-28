package nl.bhogerheijde.example.rxmvp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
public class Photo {

    @SerializedName(value = "id")
    private String id;

    @SerializedName(value = "title")
    private String title;

    @SerializedName(value = "url_s")
    private String urlSmall;

    public String getUrlSmall() {
        return urlSmall;
    }

}
