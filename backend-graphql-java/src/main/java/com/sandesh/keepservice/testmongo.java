package com.sandesh.keepservice;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

@WebServlet(urlPatterns = "/testmongo")
public class testmongo extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            String mongoconnection="hello";
        try{
            MongoDatabase mongo = new MongoClient().getDatabase("test");
            MongoCollection<Document>testcollection = mongo.getCollection("notes");
            Document doc = new Document();
            doc.append("name", "sandesh");
            testcollection.insertOne(doc);
            mongoconnection = "yes successful";
        }
        catch(Exception e){
            mongoconnection = "not successful";
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>" + "Mongodb test "+ mongoconnection  + "</h1>");
   }

    public void destroy() {
        // do nothing.
    }
}