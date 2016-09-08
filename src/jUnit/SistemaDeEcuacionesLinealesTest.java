package jUnit;

import java.io.FileNotFoundException;

import org.junit.Test;

import junit.framework.Assert;
import selMath.MatrizMath;
import selMath.SistemasDeEcuacionesLineales;
import selMath.VectorMath;

@SuppressWarnings("deprecation")
public class SistemaDeEcuacionesLinealesTest {
	@Test
	public void primerPruebaSEL() throws FileNotFoundException{
		SistemasDeEcuacionesLineales sel1=new SistemasDeEcuacionesLineales("D://prueba.in");
		//la matriz es {{1,2,2},{2,2,2},{2,2,1}} y el resultado {2,2,1}
		sel1.resolver();
		VectorMath esperado=new VectorMath(3);
		double[] resultado={0,0,1};
		esperado.setVector(resultado);
		Assert.assertTrue(esperado.equals(sel1.getResultadoIncognitas()));
	
	}
	
	@Test
	public void pruebaConUnSistema4x4() {
		//SistemasDeEcuacionesLineales sel1=new SistemasDeEcuacionesLineales("D://prueba.in");
		//la matriz es {{1,2,2},{2,2,2},{2,2,1}} y el resultado {2,2,1}
		MatrizMath matrizInicial = new MatrizMath(4, 4);
		VectorMath vectorResultado = new VectorMath(4);
		double matriz[][] = {{1,0,1,0},{1,0,1,1},{0,1,1,1},{1,1,1,1}};
		double resultado[] = {1,1,0,1};
		matrizInicial.setMatriz(matriz);
		vectorResultado.setVector(resultado);
		SistemasDeEcuacionesLineales sel1=new SistemasDeEcuacionesLineales(matrizInicial,vectorResultado);
		
		sel1.resolver();
		VectorMath esperado=new VectorMath(4);
		double[] resultadoEsperado={1,0,0,0};
		esperado.setVector(resultadoEsperado);
		
		Assert.assertTrue(esperado.equals(sel1.getResultadoIncognitas()));
	
	}
	
	 /*  @Test
	    public void doStuff() throws FileNotFoundException {
	        SistemasDeEcuacionesLineales sistemasDeEcuacionesLineales = new SistemasDeEcuacionesLineales("D:/pablo.in");
	        VectorMath resultadoEsperado = new VectorMath(3);
	        double vectorResultado[] = {-0.5, -0.75, 0.75};
	        resultadoEsperado.setVector(vectorResultado);
	        Assert.assertEquals(resultadoEsperado, sistemasDeEcuacionesLineales.resolver());
	    }
*/
}
