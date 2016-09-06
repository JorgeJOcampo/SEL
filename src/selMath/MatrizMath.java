package selMath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class MatrizMath {
	private double[][] matriz;
	int fila, columna;

	public MatrizMath(String path) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(path));
		fila = sc.nextInt();
		columna = sc.nextInt();
		matriz = new double[fila][columna];
		while (sc.hasNext()) {
			matriz[sc.nextInt()][sc.nextInt()] = sc.nextDouble();
		}
		sc.close();
	}

	public MatrizMath(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
		matriz = new double[fila][columna];
	}

	public MatrizMath clone() {
		MatrizMath matrizMath = new MatrizMath(fila, columna);
		for (int i = 0; i < fila; i++) {
			for (int j = 0; j < columna; j++) {
				matrizMath.matriz[i][j] = matriz[i][j];
			}
		}
		return matrizMath;
	}

	public double[][] getMatriz() {
		return matriz;
	}

	public void setMatriz(double[][] matriz) {
		this.matriz = matriz;
	}

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public int getColumna() {
		return columna;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

	@Override
	public String toString() {
		return "MatrizMath [matriz=" + Arrays.toString(matriz) + ", fila=" + fila
				+ ", columna=" + columna + "]";
	}

	public MatrizMath suma(MatrizMath matrizMath) throws DisDimException {
		if (this.columna != matrizMath.columna || this.fila != matrizMath.fila)
			throw new DisDimException("Dimensiones diferentes");
		MatrizMath suma = new MatrizMath(this.columna, this.fila);
		for (int i = 0; i < this.columna; i++) {
			for (int j = 0; j < this.fila; j++) {
				suma.matriz[i][j] = this.matriz[i][j] + matrizMath.matriz[i][j];
			}
		}
		return suma;
	}

	public MatrizMath resta(MatrizMath matrizMath) throws DisDimException {
		if (this.columna != matrizMath.columna || this.fila != matrizMath.fila)
			throw new DisDimException("Dimensiones diferentes");
		MatrizMath resta = new MatrizMath(this.columna, this.fila);
		for (int i = 0; i < this.columna; i++) {
			for (int j = 0; j < this.fila; j++) {
				resta.matriz[i][j] = this.matriz[i][j] - matrizMath.matriz[i][j];
			}
		}
		return resta;
	}

	public MatrizMath productoDeMatrices(MatrizMath matrizMath) throws DisDimException {
		if (this.columna != matrizMath.fila)
			throw new DisDimException(
					"Columa del llamador distinta a fila de parametro");
		MatrizMath resultado = new MatrizMath(this.fila, matrizMath.columna);
		for (int i = 0; i < this.fila; i++) {
			for (int j = 0; j < matrizMath.columna; j++) {
				resultado.matriz[i][j] = 0;
				for (int k = 0; k < this.columna; k++) {
					resultado.matriz[i][j] += matriz[i][k] * matrizMath.matriz[k][j];
				}
			}
		}
		return resultado;
	}

	public void MostrarMatriz() {
		for (int i = 0; i < fila; i++) {
			for (int j = 0; j < columna; j++) {
				System.out.print(matriz[i][j] + " ");
			}
			System.out.println("");
		}
	}

	public VectorMath productoPorVector(VectorMath vectorMath) throws DisDimException {
		if (vectorMath.getVector().length != this.columna)
			throw new DisDimException("Diferentes dimensiones");

		VectorMath resultado = new VectorMath(vectorMath.getVector());
		double[] producto = new double[vectorMath.getVector().length];
		for (int i = 0; i < this.fila; i++) {
			producto[i] = 0;
			for (int j = 0; j < this.columna; j++) {
				producto[i] += matriz[i][j] * vectorMath.getVector()[j];
			}
		}
		resultado.setVector(producto);
		return resultado;
	}

	public MatrizMath productoPorReal(double escalor) {
		MatrizMath matrizMath = new MatrizMath(fila, columna);
		for (int i = 0; i < fila; i++) {
			for (int j = 0; j < columna; j++) {
				matrizMath.matriz[i][j] = matriz[i][j] * escalor;
			}
		}
		return matrizMath;
	}

	public boolean equals(MatrizMath matrizMath) {
		if (this.columna != matrizMath.columna || this.fila != matrizMath.fila)
			return false;

		for (int i = 0; i < matrizMath.fila; i++) {
			for (int j = 0; j < matrizMath.columna; j++) {
				if (matriz[i][j] != matrizMath.matriz[i][j])
					return false;
			}
		}
		return true;

	}

	public double normaUno() {
		double norma = 0;
		for (int i = 0; i < fila; i++) {
			for (int j = 0; j < columna; j++)
				norma += Math.abs(matriz[i][j]);
		}
		return norma;
	}

	public double normaDos() {
		double norma = 0;
		for (int i = 0; i < fila; i++) {
			for (int j = 0; j < columna; j++)
				norma += Math.abs(matriz[i][j]);
		}
		return Math.sqrt(norma);
	}

	public double normaInfinito() {
		double norma = 0;
		for (int i = 0; i < fila; i++) {
			for (int j = 0; j < columna; j++) {
				if (norma < Math.abs(matriz[i][j]))
					norma = Math.abs(matriz[i][j]);
			}
		}
		return norma;
	}

	public double determinante() throws DisDimException{
		if(fila != columna)
			throw new DisDimException("Dimensiones incorrectas");
		if(fila ==2)
			return (matriz[0][0] * matriz[1][1]) - (matriz[0][1] * matriz[1][0]);
		double escalar=0, columna=0, suma=0;
		int fila=0, col=0;
		MatrizMath m = new MatrizMath(this.fila -1, this.columna -1);
		for(int i = 0; i< this.fila; i++){
			for(int j = 0; j< this.columna; j++){
				if(i==0){
					columna = j;
					if(j%2==0)
						escalar = matriz[i][j];
					else
						escalar = -matriz[i][j];
				}
				else{
					if(j!=columna){
						m.matriz[fila][col] = matriz[i][j];
						if(col== this.columna -2){
							col=0;
							fila++;
						}
						else
							col++;
					}
				}
			}
		}
		suma += escalar * m.determinante();
		return suma;
	}

//	public MatrizMath inversas(){
//		return this.gaussJordan();
//	}
//
//	public MatrizMath gaussJordan(){
//		MatrizMath resultado = new MatrizMath(fila, columna);
//		resultado.crearIdentidad();
//		MatrizMath original = new MatrizMath(fila, columna);
//		original = this.clone();
//		for(int i = 0; i< fila; i++){
//			for(int j = 0; j< columna; j++){
//				if(i==j){
//					double aux = original.matriz[i][j];
//					for(int k = 0; k< columna -1; k++){
//						resultado.matriz[i][k] /= aux;
//						original.matriz[i][k] /= aux;
//					}
//					for(int l = i+1; l< fila; l++){
//						double aux2 = original.matriz[l][j];
//						for(int m = 0; m< columna; m++){
//							original.matriz[l][m] -= (aux2 * original.matriz[i][m]);
//							resultado.matriz[l][m] -= (aux2 * resultado.matriz[i][m]);
//						}
//					}
//				}
//			}
//		}
//		for(int l = fila -2; l>0; l--){
//			double aux = original.matriz[l][columna -1];
//			for(int m = columna -1; m>0; m--){
//				original.matriz[l][m] -= (aux * original.matriz[fila -1][m]);
//				resultado.matriz[l][m] -= (aux * resultado.matriz[fila -1][m]);
//			}
//		}
//		return resultado;
//	}
//
//

	public static void main(String args[]){
		MatrizMath matrizInicial = new MatrizMath(3, 3);
		MatrizMath matrizResultado = new MatrizMath(3, 3);
		//double matriz[][] = {{10,25,22},{78,56,12},{3,-5,3/2}};
		//double resultadoEsperado[][] = {{-16/1429,295/25722,932/12861},{9/1429,17/4287,-532/4287},{62/1429,-125/12861,1390/12861}};
		double matriz[][] = {{1,2,2},{2,2,2},{2,2,1}};
		double resultadoEsperado[][] = {{-1,1,0},{1,-3/2,1},{0,1,-1}};
		matrizInicial.setMatriz(matriz);
		matrizResultado.setMatriz(resultadoEsperado);
		matrizInicial = matrizInicial.inversa();
		matrizInicial.MostrarMatriz();
	}

	public MatrizMath inversa(){
		
		MatrizMath identidad = crearIdentidad();
		MatrizMath inversa = this.clone();
        inversa.setUnoEnDiagonal(identidad);
		
		inversa.triangular(identidad);
		
		return identidad;
	}


	public void setUnoEnDiagonal(MatrizMath identidad){
		for(int i=0;i<this.fila;i++){
			double diagonal = 1 / this.matriz[i][i];
			for(int j=0;j<this.columna;j++){
				this.matriz[i][j] *= diagonal;
				identidad.matriz[i][j] *= diagonal;
			}
		}
	}
	
	public void triangular(MatrizMath identidad){
		this.triangularSuperior(identidad);
		this.triangularInferior(identidad);
	}

	public void triangularSuperior(MatrizMath identidad){ //la parte de abajo
		for(int k=0;k<this.fila-1;k++){
            double valorcin = this.matriz[k][k];
            for(int w = 0 ; w < this.columna ;w++){
                this.matriz[k][w] /= valorcin;
                identidad.matriz[k][w] /= valorcin;
            }
            for(int i=k+1;i<this.fila;i++){
				double valor = this.matriz[i][k];
				for(int j=0;j<this.columna;j++){
					this.matriz[i][j] -= (this.matriz[k][j] * valor);
					identidad.matriz[i][j] -= (identidad.matriz[k][j] * valor);
				}
			}
		}
	}

	public void triangularInferior(MatrizMath identidad){ //la parte de arriba
		for(int k=this.fila-1;k>0;k--){
            double valorcin = this.matriz[k][k];
            for(int w = 0 ; w < this.columna ;w++){
                this.matriz[k][w] /= valorcin;
                identidad.matriz[k][w] /= valorcin;
            }
            for(int i=k-1;i>=0;i--){
				double valor = this.matriz[i][k];
				for(int j=this.columna-1;j>=0;j--){
					this.matriz[i][j] -= (this.matriz[k][j] * valor);
					identidad.matriz[i][j] -= (identidad.matriz[k][j] * valor);
				}
			}
		}
	}

	public MatrizMath crearIdentidad(){
		MatrizMath matriz = new MatrizMath(this.fila,this.columna);
		for(int i = 0; i< fila; i++){
			for(int j = 0; j< columna; j++){
				if(i==j)
					matriz.matriz[i][j] = 1;
				else
					matriz.matriz[i][j] = 0;
			}
		}
		return matriz;
	}













	
	
	
	
	
	
	

	/*
	 public MatrizMath calcularInversa(){
		acomodarMatriz();
		return 
	}
	
	public double[] restarColumnas(double[] columnaDelPivote, double[] columnaARestar){
		for(int i = 0 ; i < columnaDelPivote.length ; i++){
			columnaARestar[i] -= columnaDelPivote[i]; 
		}
		return columnaARestar;
	}
	
	public void acomodarMatriz(){
		for(int i = 0 ; i < this.getF() ; i++){
			for(int j = 0 ; j < this.getC() ; j++){
				if(this.datos[i][j]!= 0){
					break;
				} else{
					intercambiarFilas(i);
				}
			}
		}
	}
	
	public void intercambiarFilas(int fila){
		for(int i = fila ; i < this.getFilas()-1 ; i++){
			if(datos[i+1] != 0){
				
			}
		}
	}
	
	public void multiplicarColumnas(){
		
		double inverso = 1 / datos[0][0];
		for(int i = 0 ; i < columnas ; i++){
			datos[0][i] *= inverso;
		}
		
		for(int i = 1; i < filas ; i++){
			inverso = 1 / datos[i][0];
			for(int j = 0 ; j < columnas ; j++){
				datos[i][j] *= inverso;
				datos[i][j] -= datos[0][j];
			}
		}
	}
	*/
}