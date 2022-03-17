package com.ParcialJava.Test;
import com.ParcialJava.Dao.impl.OdontologoIDaoH2;
import com.ParcialJava.Entities.Odontologo;
import com.ParcialJava.Services.OdontologoService;
import org.junit.*;
import java.util.List;

public class OdontologoServiceTest {
    private static OdontologoService odontologoService = new OdontologoService(new OdontologoIDaoH2());
    @Before
    public void cargarOdontologo(){
        Odontologo odontologo1 = new Odontologo(111,"tomas","herrera");
        Odontologo odontologo2 = new Odontologo(112, "juan", "di pietro");
        odontologoService.guardar(odontologo1);
        odontologoService.guardar(odontologo2);
    }
    @Test
    public void traerOdontologos(){
        List<Odontologo> odontologos = odontologoService.buscarTodos();
        Assert.assertTrue(!odontologos.isEmpty());
        Assert.assertTrue(odontologos.size()>0);
        System.out.println(odontologos);
    }


}
