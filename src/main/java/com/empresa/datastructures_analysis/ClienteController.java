package com.empresa.datastructures_analysis;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import org.bson.Document;

public class ClienteController {
    @FXML
    private TextField idField;
    @FXML
    private TextField nombreField;
    @FXML
    private TextField correoElectronicoField;

    @FXML
    private TableView<Cliente> tablaClientes;
    @FXML
    private TableColumn<Cliente, String> columnaNombre;
    @FXML
    private TableColumn<Cliente, String> columnaApellido;

    private MongoDBApp mongoDBApp;

    public ClienteController() {
        mongoDBApp = new MongoDBApp();
    }

    @FXML
    public void initialize() {
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaApellido.setCellValueFactory(new PropertyValueFactory<>("correoElectronico"));

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> actualizarTabla()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void actualizarTabla() {
        ObservableList<Cliente> data = FXCollections.observableArrayList();
        // Aquí debes obtener los datos de la base de datos y agregarlos a 'data'
        // Por ejemplo: data.addAll(mongoDBApp.getAllClientes());
        tablaClientes.setItems(data);
    }

    @FXML
    protected void onCrearButtonClick() {
        mongoDBApp.createCliente(idField.getText(), nombreField.getText(), correoElectronicoField.getText());
        actualizarTabla();
    }

    @FXML
    protected void onLeerButtonClick() {
        Document doc = mongoDBApp.readCliente(idField.getText());
        Cliente cliente = Cliente.fromDocument(doc);
        nombreField.setText(cliente.getNombre());
        correoElectronicoField.setText(cliente.getCorreoElectronico());
    }

    @FXML
    protected void onActualizarButtonClick() {
        mongoDBApp.updateCliente(idField.getText(), nombreField.getText(), correoElectronicoField.getText());
        actualizarTabla();
    }

    @FXML
    protected void onEliminarButtonClick() {
        mongoDBApp.deleteCliente(idField.getText());
        actualizarTabla();
    }
}