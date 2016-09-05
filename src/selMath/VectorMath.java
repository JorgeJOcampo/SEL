package selMath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class VectorMath {
	private double[] vector;
	
	public VectorMath(String path) throws FileNotFoundException{
		Scanner sc = new Scanner(new File(path));
		vector = new double[sc.nextInt()];
		for(int i=0;i<vector.length;i++){
			vector[i] = sc.nextDouble();
		}
		sc.close();
	}
	
	public VectorMath(double[] v){
		vector = v.clone();
	}
	
	@Override
	public String toString() {
		return "VectorMath [vector=" + Arrays.toString(vector) + "]";
	}

	public double[] getVector() {
		return vector;
	}

	public void setVector(double[] vector) {
		this.vector = vector;
	}
	
	public VectorMath suma(VectorMath v) throws DisDimException{
		if(v.vector.length != vector.length)
			throw new DisDimException("Dimensiones diferentes");
		double[] suma = new double[vector.length];
		for(int i=0;i<vector.length;i++){
			suma[i] = vector[i] + v.vector[i];
		}
		return new VectorMath(suma);
	}
	
	public VectorMath resta(VectorMath vectorMath) throws DisDimException{
		if(vectorMath.vector.length != vector.length)
			throw new DisDimException("Dimensiones diferentes");
		double[] suma = new double[vector.length];
		for(int i=0;i<vector.length;i++){
			suma[i] = vector[i] - vectorMath.vector[i];
		}
		return new VectorMath(suma);
	}
	
	public double producto(VectorMath vectorMath) throws DisDimException{
		if(vectorMath.vector.length != vector.length)
			throw new DisDimException("Dimensiones diferentes");
		double producto = 0;
		for(int i=0;i<vector.length;i++){
			producto += vector[i] * vectorMath.vector[i];
		}
		return producto;
	}
	
	public VectorMath productoPorReal(double escalar){
		double[] producto = new double[vector.length];
		for(int i=0;i<vector.length;i++){
			producto[i] = vector[i] * escalar;
		}
		return new VectorMath(producto);
	}
	
	public boolean equals(VectorMath vectorMath){
		if(vectorMath.vector.length != vector.length)
			return false;
		for(int i=0;i<vector.length;i++){
			if(vector[i] != vectorMath.vector[i])
				return false;
		}
		return true;
	}
	
	public VectorMath clone(){
		return new VectorMath(vector);
	}
	
	public double normaUno(){
		double norma=0;
		for(int i=0;i<vector.length;i++){
			norma += Math.abs(vector[i]);
		}
		return norma;
	}
	
	public double normaDos(){
		double norma=0;
		for(int i=0;i<vector.length;i++){
			norma += Math.pow(vector[i],2);
		}
		return Math.sqrt(norma);
	}
	
	public double normaInfinito(){
		double norma=0;
		for(int i=0;i<vector.length;i++){
			if(norma<Math.abs(vector[i]))
				norma = Math.abs(vector[i]);
		}
		return norma;
	}
	
	public VectorMath productoPorMatriz(MatrizMath matriz){
		if (vector.length != matriz.getF())
			throw new DisDimException("Diferentes dimensiones");

		VectorMath resultado = new VectorMath(vector.clone());
		double[] producto = new double[matriz.getC()];
		for (int i = 0; i < matriz.getC(); i++) {
			producto[i] = 0;
			for (int j = 0; j < matriz.getF(); j++) {
				producto[i] += vector[j] * matriz.getMatriz()[i][j];
			}
		}
		resultado.setVector(producto);
		return resultado;
	}
}
