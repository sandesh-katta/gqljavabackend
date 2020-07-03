package com.sandesh.keepservice;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.coxautodev.graphql.tools.GraphQLRootResolver;

import graphql.GraphQLException;
import graphql.schema.DataFetchingEnvironment;

public class Mutation implements GraphQLRootResolver {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    public Mutation(NoteRepository noteRepository,UserRepository userRepository){
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    public Note updateNote(String title,String content,List<String>tags,DataFetchingEnvironment env){
        AuthContext context = env.getContext();
        
        try {
            FileWriter myWriter = new FileWriter("/Users/sandesh/Desktop/harnessdemo/hackernews-graphql-java/file.txt");
            myWriter.write("inside update note\n");
            myWriter.write(context.getUser().getId());
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Note newnote = new Note(title, content, tags,context.getUser().getId());
        noteRepository.saveNote(newnote);
        return newnote;
    }

    public User createUser(String name, AuthData auth) {
        User newUser = new User(name, auth.getEmail(), auth.getPassword());
        return userRepository.saveUser(newUser);
    }

    public SigninPayload signinUser(AuthData auth) throws IllegalAccessException {
        User user = userRepository.findByEmail(auth.getEmail());
        if (user.getPassword().equals(auth.getPassword())) {
            return new SigninPayload(user.getId(), user);
        }
        throw new GraphQLException("Invalid credentials");
    }
    
}