package com.zeroone.herb;

public class Manga {
    private String mDesName;
    private String mCoverName;
    private String mThumbName;
    private String meleaseDate;
    private String mlink;
    private String mtitle;
    private String mDesUrl;
    private String mCoverUrl;
    private String mThumbUrl;

    private int mDownloadCount;

    public Manga() {
    }

    public Manga(String mDesName, String mCoverName, String mThumbName, String meleaseDate, String mlink, String mtitle, String mDesUrl, String mCoverUrl, String mThumbUrl, int mDownloadCount) {
        this.mDesName = mDesName;
        this.mCoverName = mCoverName;
        this.mThumbName = mThumbName;
        this.meleaseDate = meleaseDate;
        this.mlink = mlink;
        this.mtitle = mtitle;
        this.mDesUrl = mDesUrl;
        this.mCoverUrl = mCoverUrl;
        this.mThumbUrl = mThumbUrl;
        this.mDownloadCount = mDownloadCount;
    }

    public String getmDesName() {
        return mDesName;
    }

    public void setmDesName(String mDesName) {
        this.mDesName = mDesName;
    }

    public String getmCoverName() {
        return mCoverName;
    }

    public void setmCoverName(String mCoverName) {
        this.mCoverName = mCoverName;
    }

    public String getmThumbName() {
        return mThumbName;
    }

    public void setmThumbName(String mThumbName) {
        this.mThumbName = mThumbName;
    }

    public String getMeleaseDate() {
        return meleaseDate;
    }

    public void setMeleaseDate(String meleaseDate) {
        this.meleaseDate = meleaseDate;
    }

    public String getMlink() {
        return mlink;
    }

    public void setMlink(String mlink) {
        this.mlink = mlink;
    }

    public String getMtitle() {
        return mtitle;
    }

    public void setMtitle(String mtitle) {
        this.mtitle = mtitle;
    }

    public String getmDesUrl() {
        return mDesUrl;
    }

    public void setmDesUrl(String mDesUrl) {
        this.mDesUrl = mDesUrl;
    }

    public String getmCoverUrl() {
        return mCoverUrl;
    }

    public void setmCoverUrl(String mCoverUrl) {
        this.mCoverUrl = mCoverUrl;
    }

    public String getmThumbUrl() {
        return mThumbUrl;
    }

    public void setmThumbUrl(String mThumbUrl) {
        this.mThumbUrl = mThumbUrl;
    }

    public int getmDownloadCount() {
        return mDownloadCount;
    }

    public void setmDownloadCount(int mDownloadCount) {
        this.mDownloadCount = mDownloadCount;
    }
}
