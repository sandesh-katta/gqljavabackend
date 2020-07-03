package com.sandesh.keepservice;

import java.util.List;

public class Note {
    
    private final String id ;
    private final String title;
    private final String content;
    private final List<String> tags;
    private final String userId;
    


    public Note(String title, String content, List<String> tags,String userId){
        this(null,title,content,tags,userId);
    }
    public Note(String id ,String title, String content, List<String> tags,String userId){
        this.id = id;
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getUserId() {
        return userId;
    }
}