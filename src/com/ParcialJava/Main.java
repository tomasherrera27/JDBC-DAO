package com.ParcialJava;

import com.ParcialJava.Dao.impl.OdontologoIDaoH2;
import com.ParcialJava.Entities.Odontologo;
import com.ParcialJava.Services.OdontologoService;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        //esto lo hice para probar antes de hacer los test pero ya directamente corriendo los test hace lo mismo que aca.
        System.out.println("agregando odontologos");
        Odontologo odontologo = new Odontologo(222,"peter", "Bauman");
        Odontologo odontologo1 = new Odontologo(223,"Marcos", "Alonso");
        OdontologoService odontologoService = new OdontologoService(new OdontologoIDaoH2());
        odontologoService.guardar(odontologo);
        odontologoService.guardar(odontologo1);

        List<Odontologo> odontologos = odontologoService.buscarTodos();
        for (Odontologo i : odontologos){
            System.out.println("id" + i.getId() + "matricula" + i.getNumeroMatricula()+ "nombre " + i.getNombre() + "apellido" + i.getApellido());
        }

    }
}
