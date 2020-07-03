package com.sandesh.keepservice;

import com.coxautodev.graphql.tools.GraphQLResolver;

public class NoteResolver implements GraphQLResolver<Note> {
    
    private final UserRepository userRepository;

    public NoteResolver(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User ownedBy(Note note) {
        if (note.getUserId() == null) {
            return null;
        }
        return userRepository.findById(note.getUserId());
    }
}