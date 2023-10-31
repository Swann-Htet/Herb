package com.zeroone.herb;

public class FeaturedMovie {
    private String videoUrl;
    private String fDesName;
    private String fCoverName;
    private String fThumbName;
    private String releaseDate;
    private String link;
    private String title;
    private String fDesUrl; // Add URL properties for images
    private String fCoverUrl;
    private String fThumbUrl;
    private int fDownloadCount;

    // Default constructor (required for Firebase)
    public FeaturedMovie() {}

    // Constructor
    public FeaturedMovie(String videoUrl, String fDesName, String fCoverName, String fThumbName, String releaseDate, String link, String title, String fDesUrl, String fCoverUrl, String fThumbUrl, int fDownloadCount) {
        this.videoUrl = videoUrl;
        this.fDesName = fDesName;
        this.fCoverName = fCoverName;
        this.fThumbName = fThumbName;
        this.releaseDate = releaseDate;
        this.link = link;
        this.title = title;
        this.fDesUrl = fDesUrl;
        this.fCoverUrl = fCoverUrl;
        this.fThumbUrl = fThumbUrl;
        this.fDownloadCount = fDownloadCount;
    }

    // Getters and setters (required for Firebase)
    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getfDesName() {
        return fDesName;
    }

    public void setfDesName(String fDesName) {
        this.fDesName = fDesName;
    }

    public String getfCoverName() {
        return fCoverName;
    }

    public void setfCoverName(String fCoverName) {
        this.fCoverName = fCoverName;
    }

    public String getfThumbName() {
        return fThumbName;
    }

    public void setfThumbName(String fThumbName) {
        this.fThumbName = fThumbName;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getfDesUrl() {
        return fDesUrl;
    }

    public void setfDesUrl(String fDesUrl) {
        this.fDesUrl = fDesUrl;
    }

    public String getfCoverUrl() {
        return fCoverUrl;
    }

    public void setfCoverUrl(String fCoverUrl) {
        this.fCoverUrl = fCoverUrl;
    }

    public String getfThumbUrl() {
        return fThumbUrl;
    }

    public void setfThumbUrl(String fThumbUrl) {
        this.fThumbUrl = fThumbUrl;
    }

    public int getfDownloadCount() {
        return fDownloadCount;
    }

    public void setfDownloadCount(int fDownloadCount) {
        this.fDownloadCount = fDownloadCount;
    }
}
