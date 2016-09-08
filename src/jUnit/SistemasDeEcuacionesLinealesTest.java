package jUnit;

import org.junit.Assert;
import org.junit.Test;
import selMath.SistemasDeEcuacionesLineales;
import selMath.VectorMath;

import java.io.FileNotFoundException;

public class SistemasDeEcuacionesLinealesTest {

    @Test
    public void doStuff() throws FileNotFoundException {
        SistemasDeEcuacionesLineales sistemasDeEcuacionesLineales = new SistemasDeEcuacionesLineales("D:/pablo.in");
        VectorMath resultadoEsperado = new VectorMath(3);
        double vectorResultado[] = {-0.5, -0.75, 0.75};
        resultadoEsperado.setVector(vectorResultado);
        Assert.assertEquals(resultadoEsperado, sistemasDeEcuacionesLineales.resolver());
    }
}