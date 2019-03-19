package core;

public class Review{
    private String _id;
    private String url;
    private String author;
    private String body;
    private String rating;
    private String reviews;
    private Long scrape_ts;
    private long pub_ts;
    private String date;

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }
    /**
     * @param _id the _id to set
     */
    public void set_id(String _id) {
        this._id = _id;
    }
    /**
     * @param rating the rating to set
     */
    public void setRating(String rating) {
        this.rating = rating;
    }
    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }
    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }
    /**
     * @param body the body to set
     */
    public void setBody(String body) {
        this.body = body;
    }
    /**
     * @param pub_ts the pub_ts to set
     */
    public void setPub_ts(long pub_ts) {
        this.pub_ts = pub_ts;
    }
    /**
     * @param scrape_ts the scrape_ts to set
     */
    public void setScrape_ts(Long scrape_ts) {
        this.scrape_ts = scrape_ts;
    }
    /**
     * @param reviews the reviews to set
     */
    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }
    /**
     * @return the _id
     */
    public String get_id() {
        return _id;
    }
    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }
    /**
     * @return the body
     */
    public String getBody() {
        return body;
    }
    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }
    /**
     * @return the pub_ts
     */
    public long getPub_ts() {
        return pub_ts;
    }
    /**
     * @return the rating
     */
    public String getRating() {
        return rating;
    }
    /**
     * @return the reviews
     */
    public String getReviews() {
        return reviews;
    }
    /**
     * @return the scrape_ts
     */
    public Long getScrape_ts() {
        return scrape_ts;
    }


}