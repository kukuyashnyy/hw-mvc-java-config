package org.example.domain.entity;

import lombok.*;



public class Post {

    private String postAuthor;
    private String publicationDate;
    private String postName;
    private String postTheme;
    private String postBody;
    private String draft;
    private int id;

    public Post(String postAuthor, String publicationDate, String postName, String postTheme, String postBody, String draft) {
        this.postAuthor = postAuthor;
        this.publicationDate = publicationDate;
        this.postName = postName;
        this.postTheme = postTheme;
        this.postBody = postBody;
        this.draft = draft;
    }

    public String getPostAuthor() {
        return postAuthor;
    }

    public void setPostAuthor(String postAuthor) {
        this.postAuthor = postAuthor;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getPostTheme() {
        return postTheme;
    }

    public void setPostTheme(String postTheme) {
        this.postTheme = postTheme;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public String getDraft() {
        return draft;
    }

    public void setDraft(String draft) {
        this.draft = draft;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postAuthor='" + postAuthor + '\'' +
                ", publicationDate='" + publicationDate + '\'' +
                ", postName='" + postName + '\'' +
                ", postTheme='" + postTheme + '\'' +
                ", postBody='" + postBody + '\'' +
                ", draft='" + draft + '\'' +
                ", id=" + id +
                '}';
    }
}
