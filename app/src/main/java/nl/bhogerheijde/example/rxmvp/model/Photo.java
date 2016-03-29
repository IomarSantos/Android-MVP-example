package nl.bhogerheijde.example.rxmvp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Flickr app built with RxJava, Dagger and MVP pattern.
 *
 * @author Boyd Hogerheijde
 */
public class Photo implements Serializable {

    @SerializedName(value = "id")
    private String id;

    @SerializedName(value = "title")
    private String title;

    @SerializedName(value = "url_s")
    private String urlSmall;

    @SerializedName(value = "url_k")
    private String urlLarge;

    public String getTitle() {
        return title;
    }

    public String getUrlSmall() {
        return urlSmall;
    }

    public String getUrlLarge() {
        return urlLarge;
    }

}
