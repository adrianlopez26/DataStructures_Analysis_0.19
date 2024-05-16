package com.empresa.datastructures_analysis;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBApp {
    private MongoClient client;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public MongoDBApp() {
        MongoClientURI uri = new MongoClientURI("mongodb+srv://admin:admin@javaadri.bydxigr.mongodb.net/");
        client = new MongoClient(uri);
        database = client.getDatabase("javaadri");
        collection = database.getCollection("analisis");
    }

    public void createCliente(String id, String nombre, String correoElectronico) {
        Document doc = new Document("id", id)
                .append("nombre", nombre)
                .append("correoElectronico", correoElectronico);
        collection.insertOne(doc);
    }

    public Document readCliente(String id) {
        return collection.find(new Document("id", id)).first();
    }

    public void updateCliente(String id, String nombre, String correoElectronico) {
        Document newDoc = new Document("id", id)
                .append("nombre", nombre)
                .append("correoElectronico", correoElectronico);
        collection.replaceOne(new Document("id", id), newDoc);
    }

    public void deleteCliente(String id) {
        collection.deleteOne(new Document("id", id));
    }
}