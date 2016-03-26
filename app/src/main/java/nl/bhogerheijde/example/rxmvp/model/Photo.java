package nl.bhogerheijde.example.rxmvp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Flickr app built with RxJava and MVP pattern, file created on 26/03/16.
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
