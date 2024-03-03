package org.example.assignment1;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Video {
    private final SimpleStringProperty title;
    private final SimpleStringProperty uploader;
    private final SimpleDoubleProperty dislikes;
    private final SimpleDoubleProperty dislikesPercentage;

    public Video(String title, String uploader, double dislikes, double dislikesPercentage) {
        this.title = new SimpleStringProperty(title);
        this.uploader = new SimpleStringProperty(uploader);
        this.dislikes = new SimpleDoubleProperty(dislikes);
        this.dislikesPercentage = new SimpleDoubleProperty(dislikesPercentage);
    }

    public SimpleStringProperty getTitle() {
        return title;
    }

    public SimpleStringProperty getUploader() {
        return uploader;
    }

    public SimpleDoubleProperty getDislikes() {
        return dislikes;
    }

    public SimpleDoubleProperty getDislikesPercentage() {
        return dislikesPercentage;
    }
}
