package jUnit;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

import selMath.MatrizMath;

public class MatrizMathTest {

	@Test
	public void pruebaDeterminante() {
		MatrizMath matriz = new MatrizMath(5, 5);
		llenarMatriz(matriz);
		int resultadoEsperado = 0;
		Assert.assertTrue(resultadoEsperado == matriz.determinante());
	}

	@Test
	public void pruebaDeterminante2() {
		MatrizMath matriz = new MatrizMath(5, 5);
		llenarMatriz2(matriz);
		int resultadoEsperado = 0;
		Assert.assertTrue(resultadoEsperado == matriz.determinante());
	}
	@Test
	public void pruebaDeterminante3() {
		MatrizMath matriz = new MatrizMath(3, 3);
		llenarMatriz3(matriz);
		int resultadoEsperado = 0;
		Assert.assertTrue(resultadoEsperado == matriz.determinante());
	}
	
	private void llenarMatriz(MatrizMath matriz) {
		double matrizPrueba[][] = new double[matriz.getColumna()][matriz.getFila()];
		for(int i = 0 ; i < matriz.getColumna() ; i++){
			for(int j = 0 ; j < matriz.getFila() ; j++){
				matrizPrueba[i][j]=2;
			}
		}
		matriz.setMatriz(matrizPrueba);
	}
	
	private void llenarMatriz2(MatrizMath matriz) {
		double matrizPrueba[][] = new double[matriz.getColumna()][matriz.getFila()];
		for(int i = 0 ; i < matriz.getColumna() ; i++){
			for(int j = 0 ; j < matriz.getFila() ; j++){
				matrizPrueba[i][j]=j;
			}
		}
		matriz.setMatriz(matrizPrueba);
	}

	private void llenarMatriz3(MatrizMath matriz) {
		double m[][] = {{1,2,2},{2,2,2},{2,2,1}};
		matriz.setMatriz(m);
	}

	@Test
	public void pruebaInversa() {
		MatrizMath matrizInicial = new MatrizMath(3, 3);
		MatrizMath matrizResultado = new MatrizMath(3, 3);
		//double matriz[][] = {{10,25,22},{78,56,12},{3,-5,3/2}};
		//double resultadoEsperado[][] = {{-16/1429,295/25722,932/12861},{9/1429,17/4287,-532/4287},{62/1429,-125/12861,1390/12861}};
		double matriz[][] = {{1,2,2},{2,2,2},{2,2,1}};
		double resultadoEsperado[][] = {{-1,1,0},{1,-1.5,1},{0,1,-1}};
        //FIXME Corregir cuando hay un CERO en la Diagonal.
//        double matriz[][] = {{1,2,2},{2,0,2},{2,2,1}};
//		double resultadoEsperado[][] = {{-0.5, 0.25, 0.5},{0.25, -0.375, 0.25},{0.5, 0.25, -0.5}};
		matrizInicial.setMatriz(matriz);
		matrizResultado.setMatriz(resultadoEsperado);
		Assert.assertTrue(matrizResultado.equals(matrizInicial.inversa()));
	}
}
