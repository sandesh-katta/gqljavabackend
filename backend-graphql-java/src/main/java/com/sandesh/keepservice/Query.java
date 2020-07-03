package com.sandesh.keepservice;

import java.util.List;

import com.coxautodev.graphql.tools.GraphQLRootResolver;

public class Query implements GraphQLRootResolver {
    private final NoteRepository noteRepository;

    public Query(NoteRepository noteRepository){
        this.noteRepository=noteRepository;
    }

    public List<Note> allNotes(){
        return noteRepository.getAllNotes();
    }
    
}