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
	
	public VectorMath resta(VectorMath v) throws DisDimException{
		if(v.vector.length != vector.length)
			throw new DisDimException("Dimensiones diferentes");
		double[] suma = new double[vector.length];
		for(int i=0;i<vector.length;i++){
			suma[i] = vector[i] - v.vector[i];
		}
		return new VectorMath(suma);
	}
	
	public double producto(VectorMath v) throws DisDimException{
		if(v.vector.length != vector.length)
			throw new DisDimException("Dimensiones diferentes");
		double producto = 0;
		for(int i=0;i<vector.length;i++){
			producto += vector[i] * v.vector[i];
		}
		return producto;
	}
	
	public VectorMath productoPorReal(double r){
		double[] producto = new double[vector.length];
		for(int i=0;i<vector.length;i++){
			producto[i] = vector[i] * r;
		}
		return new VectorMath(producto);
	}
	
	public boolean equals(VectorMath v){
		if(v.vector.length != vector.length)
			return false;
		for(int i=0;i<vector.length;i++){
			if(vector[i] != v.vector[i])
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

		VectorMath res = new VectorMath(vector.clone());
		double[] prod = new double[matriz.getC()];
		for (int i = 0; i < matriz.getC(); i++) {
			prod[i] = 0;
			for (int j = 0; j < matriz.getF(); j++) {
				prod[i] += vector[j] * matriz.getMatriz()[i][j];
			}
		}
		res.setVector(prod);
		return res;
	}
	
	public static void main(String[] args){
		Locale.setDefault(new Locale("en","us"));
		VectorMath v1;
		double v3;
		try {
			v1 = new VectorMath("E:/UNLaM/Programación Avanzada/Workspace/TP 1/vectorMath prueba.txt");
			v3 = v1.normaInfinito();
			System.out.println(v3);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
