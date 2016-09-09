package selMath;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SistemasDeEcuacionesLineales {
	private MatrizMath matriz;
	private VectorMath resultado;
	private VectorMath resultadoIncognitas;
	private int cantidadDeEcuaciones;
	private double error;
	
	public double getError() {
		return error;
	}

	public void setError(double error) {
		this.error = error;
	}

	public SistemasDeEcuacionesLineales( int cantidadDeEcuaciones){
		matriz = new MatrizMath(cantidadDeEcuaciones, cantidadDeEcuaciones);
		resultado = new VectorMath(cantidadDeEcuaciones);
		
	}
	
	public MatrizMath getMatriz() {
		return matriz;
	}

	public void setMatriz(MatrizMath matriz) {
		this.matriz = matriz;
	}

	public VectorMath getResultado() {
		return resultado;
	}

	public void setResultado(VectorMath resultado) {
		this.resultado = resultado;
	}

	

	public int getCantidadDeEcuaciones() {
		return cantidadDeEcuaciones;
	}

	public void setCantidadDeEcuaciones(int cantidadDeEcuaciones) {
		this.cantidadDeEcuaciones = cantidadDeEcuaciones;
	}

	public VectorMath getResultadoIncognitas() {
		return resultadoIncognitas;
	}

	public void setResultadoIncognitas(VectorMath resultadoIncognitas) {
		this.resultadoIncognitas = resultadoIncognitas;
	}

	public SistemasDeEcuacionesLineales(MatrizMath matriz, VectorMath resultado){
		this.matriz = matriz.clone();
		this.resultado = resultado.clone();
		
	}
	
	public SistemasDeEcuacionesLineales(String path) throws FileNotFoundException{
		Scanner sc = new Scanner(new File(path));
		int longitud = sc.nextInt();

		cantidadDeEcuaciones=longitud;
		
		matriz = new MatrizMath(longitud, longitud);
		
		double[][] matrizCargadora = new double[matriz.getFila()][matriz.getColumna()];

		for(int i=0; i<matriz.getFila()*matriz.getColumna();i++){
			matrizCargadora[sc.nextInt()][sc.nextInt()] = sc.nextDouble();
		}
		
		 matriz.setMatriz(matrizCargadora);
		 
		 
		 double[] vector = new double[cantidadDeEcuaciones];
		 resultado = new VectorMath(vector.length);
		 for(int i=0;i<vector.length;i++){
		 vector[i] = sc.nextDouble();
		 }
		 resultado.setVector(vector);
		 sc.close();
	}

	public void guardarResultadoEnArchivo(String path) throws IOException{
		PrintWriter salida = new PrintWriter(new FileWriter(path));
		salida.println(cantidadDeEcuaciones);
		for(int i=0; i<cantidadDeEcuaciones;i++){
			salida.println(resultadoIncognitas.getVector()[i]);
		}
		salida.println("");
		salida.println(error);
		salida.close();
	}
	
	public void mostrarResultado(){
		System.out.println(resultadoIncognitas);
	}
	
	public void resolver() throws InversibleException{
		
		resultadoIncognitas=matriz.inversa().productoPorVector(resultado);
		
		error=calcularError();

		
	}

	private double calcularError() {

		return resultado.resta(matriz.productoPorVector(resultadoIncognitas)).normaDos();
	}
	
	
}
