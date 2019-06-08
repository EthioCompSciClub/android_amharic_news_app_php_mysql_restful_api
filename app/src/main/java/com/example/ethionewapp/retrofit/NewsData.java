package com.example.ethionewapp.retrofit;

public class NewsData {
    private String id;
    private String title;
    private String href;
    private String image;
    private String logo;
    private String logo_link;
    private String title_date;

    public NewsData(String id, String title, String href, String image, String logo, String logo_link, String title_date) {
        this.id = id;
        this.title = title;
        this.href = href;
        this.image = image;
        this.logo = logo;
        this.logo_link = logo_link;
        this.title_date = title_date;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getHref() {
        return href;
    }

    public String getImage() {
        return image;
    }

    public String getLogo() {
        return logo;
    }

    public String getLogo_link() {
        return logo_link;
    }

    public String getTitle_date() {
        return title_date;
    }
}
