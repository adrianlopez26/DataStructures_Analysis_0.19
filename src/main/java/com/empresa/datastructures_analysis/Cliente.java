package com.empresa.datastructures_analysis;

import lombok.*;
import org.bson.Document;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    private String id;
    private String nombre;
    private String correoElectronico;

    public static Cliente fromDocument(Document doc) {
        return new Cliente(doc.getString("id"), doc.getString("nombre"), doc.getString("correoElectronico"));
    }
}