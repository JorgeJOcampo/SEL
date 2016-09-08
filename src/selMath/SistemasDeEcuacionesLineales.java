package selMath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Scanner;

public class SistemasDeEcuacionesLineales {
	private MatrizMath matriz;
	private VectorMath resultado;
	private int cantidadDeIncognitas, cantidadDeEcuaciones;
	
	public SistemasDeEcuacionesLineales(int cantidadDeIncognitas, int cantidadDeEcuaciones){
		matriz = new MatrizMath(cantidadDeEcuaciones, cantidadDeIncognitas);
		resultado = new VectorMath(cantidadDeEcuaciones);
	}
	
	public SistemasDeEcuacionesLineales(MatrizMath matriz, VectorMath resultado){
		this.matriz = matriz.clone();
		this.resultado = resultado.clone();
	}
	
	public SistemasDeEcuacionesLineales(String path) throws FileNotFoundException{
		Scanner sc = new Scanner(new File(path));
		matriz.setFila(sc.nextInt());
		matriz.setColumna(sc.nextInt());
		matriz = new MatrizMath(matriz.getFila(),matriz.getColumna());
		
		double[][] matrizCargadora = new double[matriz.getFila()][matriz.getColumna()];

		for(int i=0; i<matriz.getFila()*matriz.getColumna();i++){
			matrizCargadora[sc.nextInt()][sc.nextInt()] = sc.nextDouble();
		}
		
		 matriz.setMatriz(matrizCargadora);
		 
		 
		 double[] vector = new double[sc.nextInt()];
			for(int i=0;i<vector.length;i++){
				vector[i] = sc.nextDouble();
			}
			resultado.setVector(vector);
		 sc.close();
	}
	

	
	public boolean verificarIncognitasEcuaciones(){
		return cantidadDeIncognitas < cantidadDeEcuaciones;
	}
	
	public void mostrarResultado(){
		System.out.println(resultado);
	}
	
	public VectorMath resolver() throws DisDimException{
		if(!verificarIncognitasEcuaciones()){
			throw new DisDimException("la cantidad de incognitas es mayor a la cantidad de ecuaciones. Indeterminado");
		}

		return resultado;
	}
}
