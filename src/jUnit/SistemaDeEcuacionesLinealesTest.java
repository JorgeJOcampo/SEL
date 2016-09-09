package jUnit;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import junit.framework.Assert;
import selMath.InversibleException;
import selMath.MatrizMath;
import selMath.SistemasDeEcuacionesLineales;
import selMath.VectorMath;

@SuppressWarnings("deprecation")
public class SistemaDeEcuacionesLinealesTest {

	@Test
	public void pruebaConUnSistema4x4() throws InversibleException {
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
	
	@Test 
	public void pruebaMatrizSinInversa() {
		
		MatrizMath matrizInicial = new MatrizMath(4, 4);
		VectorMath vectorResultado = new VectorMath(4);
		double matriz[][] = {{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1}};
		double resultado[] = {1,1,0,1};
		matrizInicial.setMatriz(matriz);
		vectorResultado.setVector(resultado);
		SistemasDeEcuacionesLineales sel1=new SistemasDeEcuacionesLineales(matrizInicial,vectorResultado);
		boolean exception=false;
		try {
			sel1.resolver();
		} catch (InversibleException e) {
		
			exception=true;
		}
		Assert.assertTrue(exception);
		
	
	
	}
	
	
	@Test 
	public void pruebaMatrizSinInversa2() {
		
		MatrizMath matrizInicial = new MatrizMath(4, 4);
		VectorMath vectorResultado = new VectorMath(4);
		double matriz[][] = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
		double resultado[] = {1,1,0,1};
		matrizInicial.setMatriz(matriz);
		vectorResultado.setVector(resultado);
		SistemasDeEcuacionesLineales sel1=new SistemasDeEcuacionesLineales(matrizInicial,vectorResultado);
		boolean exception=false;
		try {
			sel1.resolver();
		} catch (InversibleException e) {
		
			exception=true;
		}
		Assert.assertTrue(exception);
		
	
	
	}
	
	@Test 
	public void pruebaMatrizSinInversa3() throws Exception {
		
		MatrizMath matrizInicial = new MatrizMath(3, 3);
		VectorMath vectorResultado = new VectorMath(3);
		double matriz[][] = {{1,2,3},{5,6,7},{9,10,11}};
		double resultado[] = {1,1,0,1};
		matrizInicial.setMatriz(matriz);
		vectorResultado.setVector(resultado);
		SistemasDeEcuacionesLineales sel1=new SistemasDeEcuacionesLineales(matrizInicial,vectorResultado);
		boolean exception=false;
		try {
			sel1.resolver();
		} 
		catch (InversibleException e) {
		
			exception=true;
		}
		Assert.assertTrue(exception);
	}

	@Test
	public void PruebaSEL1() throws InversibleException, FileNotFoundException{
		SistemasDeEcuacionesLineales sel1=new SistemasDeEcuacionesLineales("F:\\UNLaM\\Programación Avanzada\\GitKraken - Workspace\\SEL\\Preparación de Prueba\\Lote de Prueba\\IN\\01_caso2x2simple.in");
		//la matriz es {{1,1},{1.1,0.9}} y el resultado {-8.5,11.5}.
		sel1.resolver();
		System.out.println(sel1.getError());
		VectorMath esperado=new VectorMath(2);
		double[] resultado={-17.9090909091,20.9090909091};
		esperado.setVector(resultado);
		Assert.assertTrue(sel1.getError()<Math.pow(10, -6));
	}
	
	@Test
	public void PruebaSEL2() throws InversibleException, FileNotFoundException{
		SistemasDeEcuacionesLineales sel1=new SistemasDeEcuacionesLineales("F:\\UNLaM\\Programación Avanzada\\GitKraken - Workspace\\SEL\\Preparación de Prueba\\Lote de Prueba\\IN\\02_caso01_levementePeturbado.in");
		//la matriz es {{1.01,1},{1,0.99}} y el resultado {-19700,19900}.
		sel1.resolver();
		System.out.println(sel1.getError());
		VectorMath esperado=new VectorMath(2);
		double[] resultado={-19700,19900};
		esperado.setVector(resultado);
		Assert.assertTrue(sel1.getError()<Math.pow(10, -6));
	}
	
	@Test
	public void PruebaSEL3() throws InversibleException, FileNotFoundException{
		SistemasDeEcuacionesLineales sel1=new SistemasDeEcuacionesLineales("F:\\UNLaM\\Programación Avanzada\\GitKraken - Workspace\\SEL\\Preparación de Prueba\\Lote de Prueba\\IN\\03_4x4_Normal.in");
		sel1.resolver();
		VectorMath esperado=new VectorMath(4);
		double[] resultado={-7,3,2,2};
		esperado.setVector(resultado);
		Assert.assertTrue(esperado.equals(sel1.getResultadoIncognitas()));
	}
	
	@Test
	public void PruebaSEL4() throws InversibleException, FileNotFoundException{
		SistemasDeEcuacionesLineales sel1=new SistemasDeEcuacionesLineales("F:\\UNLaM\\Programación Avanzada\\GitKraken - Workspace\\SEL\\Preparación de Prueba\\Lote de Prueba\\IN\\04_caso2x2cCasiLDsimple.in");
		sel1.resolver();
		System.out.println("caso 4: " + sel1.getError());
		VectorMath esperado=new VectorMath(2);
		double[] resultado={-1.8066862e-18,903348072e9};
		esperado.setVector(resultado);
		Assert.assertTrue(sel1.getError()<Math.pow(10, -6));
	}
	
	@Test
	public void PruebaSEL5() throws InversibleException, IOException{
		SistemasDeEcuacionesLineales sel1=new SistemasDeEcuacionesLineales("F:\\UNLaM\\Programación Avanzada\\GitKraken - Workspace\\SEL\\Preparación de Prueba\\Lote de Prueba\\IN\\05_caso1x1.in");
		//la matriz es {{1}} y el resultado {1}.
		sel1.resolver();
		VectorMath esperado=new VectorMath(1);
		double[] resultado={1};
		esperado.setVector(resultado);
		Assert.assertTrue(esperado.equals(sel1.getResultadoIncognitas()));
		sel1.guardarResultadoEnArchivo("F:\\UNLaM\\Programación Avanzada\\GitKraken - Workspace\\SEL\\Preparación de Prueba\\Lote de Prueba\\OUT\\05_caso1x1.out");
	}
	
	@Test
	public void PruebaSEL6() throws InversibleException, IOException{
		SistemasDeEcuacionesLineales sel1=new SistemasDeEcuacionesLineales("F:\\UNLaM\\Programación Avanzada\\GitKraken - Workspace\\SEL\\Preparación de Prueba\\Lote de Prueba\\IN\\06_caso3x3_3_dependientes.in");
		//la matriz es {{1,2,3},{2,4,6},{3,8,9}} y el resultado exception.
		boolean exception=false;
		try {
			sel1.resolver();
		} 
		catch (InversibleException e) {
			exception=true;
		}
		Assert.assertTrue(exception);
	}
	
	@Test
	public void PruebaSEL7() throws InversibleException, IOException{
		SistemasDeEcuacionesLineales sel1=new SistemasDeEcuacionesLineales("F:\\UNLaM\\Programación Avanzada\\GitKraken - Workspace\\SEL\\Preparación de Prueba\\Lote de Prueba\\IN\\07_caso3x3_fila_todos_ceros.in");
		//la matriz es {{1,2,3},{9,6,7},{0,0,0}} y el resultado exception.
		boolean exception=false;
		try {
			sel1.resolver();
		}
		catch (InversibleException e) {		
			exception=true;
		}
		Assert.assertTrue(exception);
	}
	
	@Test
	public void PruebaSEL8() throws InversibleException, IOException{
		SistemasDeEcuacionesLineales sel1=new SistemasDeEcuacionesLineales("F:\\UNLaM\\Programación Avanzada\\GitKraken - Workspace\\SEL\\Preparación de Prueba\\Lote de Prueba\\IN\\08_caso3x3_matrizIdentidad.in");
		//la matriz es identidad y el resultado {1,1,1}.
		sel1.resolver();
		VectorMath esperado=new VectorMath(3);
		double[] resultado={1,1,1};
		esperado.setVector(resultado);
		Assert.assertTrue(esperado.equals(sel1.getResultadoIncognitas()));
		sel1.guardarResultadoEnArchivo("F:\\UNLaM\\Programación Avanzada\\GitKraken - Workspace\\SEL\\Preparación de Prueba\\Lote de Prueba\\OUT\\08_caso3x3_matrizIdentidad.out");
	}
	
	@Test
	public void PruebaSEL9() throws InversibleException, IOException{
		SistemasDeEcuacionesLineales sel1=new SistemasDeEcuacionesLineales("F:\\UNLaM\\Programación Avanzada\\GitKraken - Workspace\\SEL\\Preparación de Prueba\\Lote de Prueba\\IN\\09_caso3x3_normal.in");
		sel1.resolver();
		VectorMath esperado=new VectorMath(3);
		double[] resultado={-1,1.5,0};
		esperado.setVector(resultado);
		Assert.assertTrue(esperado.equals(sel1.getResultadoIncognitas()));
		sel1.guardarResultadoEnArchivo("F:\\UNLaM\\Programación Avanzada\\GitKraken - Workspace\\SEL\\Preparación de Prueba\\Lote de Prueba\\OUT\\09_caso3x3_normal.out");
	}

	@Test
	public void PruebaSEL10Fatiga() throws Exception{
		String miPath = "F:\\UNLaM\\Programación Avanzada\\GitKraken - Workspace\\SEL\\Preparación de Prueba\\Lote de Prueba\\IN\\10_casoFatiga.in";
		SistemasDeEcuacionesLineales sel1= new SistemasDeEcuacionesLineales(miPath);
		sel1.resolver();
		System.out.println("caso fatiga: " + sel1.getError());
		Assert.assertTrue(sel1.getError()<Math.pow(10, -6));	
		sel1.guardarResultadoEnArchivo("F:\\UNLaM\\Programación Avanzada\\GitKraken - Workspace\\SEL\\Preparación de Prueba\\Lote de Prueba\\OUT\\10_casoFatiga.out");
	}
	
	@Test
	public void PruebaSEL11NumerosChicos() throws Exception{
		String miPath = "C:/Users/Pablo/Workspace/SEL/Preparación de Prueba/Lote de Prueba/IN/11_ValoresChicos.in";
		SistemasDeEcuacionesLineales sel1= new SistemasDeEcuacionesLineales(miPath);
		sel1.resolver();
		System.out.println("caso 11: " + sel1.getError());
		Assert.assertTrue(sel1.getError()<Math.pow(10, -6));	
		sel1.guardarResultadoEnArchivo("C:/Users/Pablo/Workspace/SEL/Preparación de Prueba/Lote de Prueba/OUT/11_ValoresChicos.out");
	}
}
