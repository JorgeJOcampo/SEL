package jUnit;

import org.junit.Assert;

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
	public void pruebaInversa() throws Exception {
		MatrizMath matrizInicial = new MatrizMath(3, 3);
		MatrizMath matrizResultado = new MatrizMath(3, 3);
		double matriz[][] = {{1,2,2},{2,2,2},{2,2,1}};
		double resultadoEsperado[][] = {{-1,1,0},{1,-1.5,1},{0,1,-1}};
		matrizInicial.setMatriz(matriz);
		matrizResultado.setMatriz(resultadoEsperado);
		Assert.assertTrue(matrizResultado.equals(matrizInicial.inversa()));
	}

	@Test
	public void siHayUnCeroEnLaPrimeraPosicionDeLaDiagonalDebeIntercambiarFilasYCalculaCorrectamenteLaInversa()throws Exception{
		MatrizMath matrizInicial = new MatrizMath(3, 3);
		MatrizMath matrizResultado = new MatrizMath(3, 3);
        double matriz[][] = {{0,2,2},{2,2,2},{2,2,1}};
		double resultadoEsperado[][] = {{-0.5, 0.5, 0},{0.5, -1, 1},{0, 1, -1}};
		matrizInicial.setMatriz(matriz);
		matrizResultado.setMatriz(resultadoEsperado);
		Assert.assertTrue(matrizResultado.equals(matrizInicial.inversa()));
	}

	@Test
	public void debeIntercambiarFilasParaAbajo()throws Exception{
		MatrizMath matrizInicial = new MatrizMath(3, 3);
		MatrizMath matrizResultado = new MatrizMath(3, 3);
		double matriz[][] = {{2,2,2},{2,0,2},{2,0,1}};
		double resultadoEsperado[][] = {{0, -0.5, 1},{0.5, -0.5, 0},{0, 1, -1}};
		matrizInicial.setMatriz(matriz);
		matrizResultado.setMatriz(resultadoEsperado);
		Assert.assertTrue(matrizResultado.equals(matrizInicial.inversa()));
	}


	@Test
	public void debeIntercambiarFilasParaArriba()throws Exception{
		MatrizMath matrizInicial = new MatrizMath(3, 3);
		MatrizMath matrizResultado = new MatrizMath(3, 3);
		double matriz[][] = {{0,0,1},{0,1,0},{1,0,0}};
		double resultadoEsperado[][] = {{0,0,1},{0,1,0},{1,0,0}};
		matrizInicial.setMatriz(matriz);
		matrizResultado.setMatriz(resultadoEsperado);
		Assert.assertTrue(matrizResultado.equals(matrizInicial.inversa()));
	}
	
	@Test
	public void pruebaDeLaInversaConMatriz4x4() throws Exception{
		MatrizMath matrizInicial = new MatrizMath(4, 4);
		MatrizMath matrizResultado = new MatrizMath(4, 4);
		double matriz[][] = {{1,0,1,0},{1,0,1,1},{0,1,1,1},{1,1,1,1}};
		double resultadoEsperado[][] = {{0,0, -1, 1},{0,-1,0,1},{1,0,1, -1},{-1,1,0,0}};
        matrizInicial.setMatriz(matriz);
		matrizResultado.setMatriz(resultadoEsperado);
		Assert.assertTrue(matrizResultado.equals(matrizInicial.inversa()));
	}
}
