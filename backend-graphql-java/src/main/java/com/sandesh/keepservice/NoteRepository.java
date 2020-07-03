package com.sandesh.keepservice;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.client.MongoCollection;

import org.bson.Document;
import org.bson.types.ObjectId;
import static com.mongodb.client.model.Filters.eq;

public class NoteRepository {

    private final MongoCollection<Document> notes;
    //private final List<Note> notes;

    public NoteRepository(MongoCollection<Document> notes){
        this.notes = notes;
    }

    public Note findbyID(String id){
        Document doc = notes.find(eq("_id", new ObjectId(id))).first();
        return note(doc);
    }

    public List<Note> getAllNotes(){
        List<Note> allNotes = new ArrayList<Note>();
        for(Document doc : notes.find()){
            allNotes.add(note(doc));
        }
        return allNotes;
    }

    public void saveNote(Note note){
        Document doc = new Document();
        doc.append("title", note.getTitle());
        doc.append("content", note.getContent());
        doc.append("ownedBy", note.getUserId());
        notes.insertOne(doc);
    }

    // generates doc to Note object
    private Note note(Document doc){
        List<String> tags= new ArrayList<String>();
        return new Note(
            doc.get("_id").toString(), 
            doc.getString("title"), 
            doc.getString("content"), 
            tags,
            doc.getString("ownedBy"));
    }
    
}