package com.empresa.datastructures_analysis;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

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

    public List<Cliente> getAllClientes() {
        List<Cliente> clientes = new ArrayList<>();
        MongoCollection<Document> collection = database.getCollection("analisis"); // Asegúrate de que este es el nombre correcto de la colección
        FindIterable<Document> docs = collection.find();
        for (Document doc : docs) {
            clientes.add(Cliente.fromDocument(doc));
        }
        return clientes;
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