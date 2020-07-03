package com.sandesh.keepservice;

import com.coxautodev.graphql.tools.SchemaParser;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import java.io.FileWriter;
import java.io.IOException;
// import java.util.List;
import java.util.Optional;
// import java.util.stream.Collectors;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// import graphql.ExceptionWhileDataFetching;
// import graphql.GraphQLError;
import graphql.schema.GraphQLSchema;
import graphql.servlet.GraphQLContext;
import graphql.servlet.SimpleGraphQLServlet;


@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static final NoteRepository noteRepository;
    private static final UserRepository userRepository;
    static{
        MongoDatabase mongo = new MongoClient().getDatabase("hnews");
        noteRepository = new NoteRepository(mongo.getCollection("notes"));
        userRepository = new UserRepository(mongo.getCollection("users"));
    }

    public GraphQLEndpoint() {
        super(buildSchema());
        try {
            FileWriter myWriter = new FileWriter("/Users/sandesh/Desktop/harnessdemo/hackernews-graphql-java/file.txt",true);
            myWriter.write("inside constructor\n");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static GraphQLSchema buildSchema() {
        return SchemaParser.newParser()
                .file("schema.graphqls")
                .resolvers(
                    new Query(noteRepository),
                    new Mutation(noteRepository,userRepository),
                    new SigninResolver(),
                    new NoteResolver(userRepository))
                .build()
                .makeExecutableSchema();
    }

    @Override
    protected GraphQLContext createContext(Optional<HttpServletRequest> request, Optional<HttpServletResponse> response) {
        try {
            FileWriter myWriter = new FileWriter("/Users/sandesh/Desktop/harnessdemo/hackernews-graphql-java/file.txt",true);
            myWriter.write("inside create context\n");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // User user = request
        //     .map(req -> req.getHeader("Authorization"))
        //     .filter(id -> !id.isEmpty())
        //     .map(id -> id.replace("Bearer ", ""))
        //     .map(userRepository::findById)
        //     .orElse(null);
        User user = userRepository.findById("5efdbad2095b1e0d281f26c0");

        if(user == null){
            try {
                FileWriter myWriter = new FileWriter("/Users/sandesh/Desktop/harnessdemo/hackernews-graphql-java/file.txt",true);
                myWriter.write("could not find user\n");
                myWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                FileWriter myWriter = new FileWriter("/Users/sandesh/Desktop/harnessdemo/hackernews-graphql-java/file.txt",true);
                myWriter.write(user.getName() + " \n");
                myWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }    

        
        return new AuthContext(user, request, response);
    }

}