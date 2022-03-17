package com.ParcialJava.Dao.impl;

import com.ParcialJava.Dao.IDao;
import com.ParcialJava.Entities.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoIDaoH2 implements IDao<Odontologo> {
    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    //con la instruccion INIT=RUNSCRIPT cuando se conecta a la base ejecuta el script de sql que esta en dicho archivo
    private final static String DB_URL = "jdbc:h2:~/test;INIT=RUNSCRIPT FROM 'create.sql'";
    private final static String DB_USER ="sa";
    private final static String DB_PASSWORD = "";

    private static final Logger logger = Logger.getLogger(OdontologoIDaoH2.class);
    @Override
    public Odontologo guardar(Odontologo odontologo) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("INSERT INTO odontologos(numeroMatricula,nombre,apellido) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,odontologo.getNumeroMatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());
            preparedStatement.executeUpdate();
            ResultSet keys = preparedStatement.getGeneratedKeys();
            logger.info("logger info en el metodo guardar");
            if(keys.next())
                odontologo.setId(keys.getInt(1));
            logger.info("Se creo el odontologo " + odontologo.getId() + "--" + odontologo.getNumeroMatricula() + "--" + odontologo.getNombre() + "--" + odontologo.getApellido() );
            preparedStatement.close();
        }catch(Exception e){
            logger.error("este es un logger error" + e.getStackTrace());
        }
        return odontologo;
    }

    @Override
    public List<Odontologo> buscarTodos() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Odontologo> odontologos = new ArrayList<>();
        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("SELECT *  FROM odontologos");
            ResultSet result = preparedStatement.executeQuery();
            while(result.next()){
                int idOdontologo = result.getInt("id");
                int numeroMatricula = result.getInt("numeroMatricula");
                String nombre = result.getString("nombre");
                String apellido = result.getString("apellido");
                Odontologo odontologo = new Odontologo(idOdontologo,numeroMatricula,nombre,apellido);
                odontologos.add(odontologo);
                logger.info("* Odontologo * id " +  idOdontologo + ": apellido " + apellido + ", nombre " + nombre + " - numero de matricula " + numeroMatricula);
            }
            preparedStatement.close();

        }catch (Exception e){
            logger.error("este es un logger error en buscar todos" + e.getStackTrace());
        }
        return odontologos;
    }
}
