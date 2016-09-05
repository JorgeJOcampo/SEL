package selMath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class MatrizMath {
	private double[][] matriz;
	int f, c;

	public MatrizMath(String path) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(path));
		f = sc.nextInt();
		c = sc.nextInt();
		matriz = new double[f][c];
		while (sc.hasNext()) {
			matriz[sc.nextInt()][sc.nextInt()] = sc.nextDouble();
		}
		sc.close();
	}

	public MatrizMath(int f, int c) {
		this.f = f;
		this.c = c;
		matriz = new double[f][c];
	}

	public MatrizMath clone() {
		MatrizMath m = new MatrizMath(f, c);
		for (int i = 0; i < f; i++) {
			for (int j = 0; j < c; j++) {
				m.matriz[i][j] = matriz[i][j];
			}
		}
		return m;
	}

	public double[][] getMatriz() {
		return matriz;
	}

	public void setMatriz(double[][] matriz) {
		this.matriz = matriz;
	}

	public int getF() {
		return f;
	}

	public void setF(int f) {
		this.f = f;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

	@Override
	public String toString() {
		return "MatrizMath [matriz=" + Arrays.toString(matriz) + ", f=" + f
				+ ", c=" + c + "]";
	}

	public MatrizMath suma(MatrizMath m) throws DisDimException {
		if (this.c != m.c || this.f != m.f)
			throw new DisDimException("Dimensiones diferentes");
		MatrizMath suma = new MatrizMath(this.c, this.f);
		for (int i = 0; i < this.c; i++) {
			for (int j = 0; j < this.f; j++) {
				suma.matriz[i][j] = this.matriz[i][j] + m.matriz[i][j];
			}
		}
		return suma;
	}

	public MatrizMath resta(MatrizMath m) throws DisDimException {
		if (this.c != m.c || this.f != m.f)
			throw new DisDimException("Dimensiones diferentes");
		MatrizMath resta = new MatrizMath(this.c, this.f);
		for (int i = 0; i < this.c; i++) {
			for (int j = 0; j < this.f; j++) {
				resta.matriz[i][j] = this.matriz[i][j] - m.matriz[i][j];
			}
		}
		return resta;
	}

	public MatrizMath productoDeMatrices(MatrizMath m) throws DisDimException {
		if (this.c != m.f)
			throw new DisDimException(
					"Columa del llamador distinta a fila de parametro");
		MatrizMath res = new MatrizMath(this.f, m.c);
		for (int i = 0; i < this.f; i++) {
			for (int j = 0; j < m.c; j++) {
				res.matriz[i][j] = 0;
				for (int k = 0; k < this.c; k++) {
					res.matriz[i][j] += matriz[i][k] * m.matriz[k][j];
				}
			}
		}
		return res;
	}

	public void MostrarMatriz() {
		for (int i = 0; i < f; i++) {
			for (int j = 0; j < c; j++) {
				System.out.print(matriz[i][j] + " ");
			}
			System.out.println("");
		}
	}

	public VectorMath productoPorVector(VectorMath v) throws DisDimException {
		if (v.getVector().length != this.c)
			throw new DisDimException("Diferentes dimensiones");

		VectorMath res = new VectorMath(v.getVector());
		double[] prod = new double[v.getVector().length];
		for (int i = 0; i < this.f; i++) {
			prod[i] = 0;
			for (int j = 0; j < this.c; j++) {
				prod[i] += matriz[i][j] * v.getVector()[j];
			}
		}
		res.setVector(prod);
		return res;
	}

	public MatrizMath productoPorReal(double val) {
		MatrizMath m = new MatrizMath(f, c);
		for (int i = 0; i < f; i++) {
			for (int j = 0; j < c; j++) {
				m.matriz[i][j] = matriz[i][j] * val;
			}
		}
		return m;
	}

	public boolean equals(MatrizMath m) {
		if (this.c != m.c || this.f != m.f)
			return false;

		for (int i = 0; i < m.f; i++) {
			for (int j = 0; j < m.c; j++) {
				if (matriz[i][j] != m.matriz[i][j])
					return false;
			}
		}
		return true;

	}

	public double normaUno() {
		double norma = 0;
		for (int i = 0; i < f; i++) {
			for (int j = 0; j < c; j++)
				norma += Math.abs(matriz[i][j]);
		}
		return norma;
	}

	public double normaDos() {
		double norma = 0;
		for (int i = 0; i < f; i++) {
			for (int j = 0; j < c; j++)
				norma += Math.abs(matriz[i][j]);
		}
		return Math.sqrt(norma);
	}

	public double normaInfinito() {
		double norma = 0;
		for (int i = 0; i < f; i++) {
			for (int j = 0; j < c; j++) {
				if (norma < Math.abs(matriz[i][j]))
					norma = Math.abs(matriz[i][j]);
			}
		}
		return norma;
	}

	public double determinante() throws DisDimException{
		if(f!=c)
			throw new DisDimException("Dimensiones incorrectas");
		if(f==2)
			return (matriz[0][0] * matriz[1][1]) - (matriz[0][1] * matriz[1][0]);
		double escalar=0, columna=0, suma=0;
		int fila=0, col=0;
		MatrizMath m = new MatrizMath(f-1, c-1);
		for(int i=0; i<f; i++){
			for(int j=0; j<c; j++){
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
						if(col==c-2){
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
	/*
	public MatrizMath inversa(){
		return this.gaussJordan();
	}
	
	public MatrizMath gaussJordan(){
		MatrizMath resultado = new MatrizMath(f,c);
		resultado.crearIdentidad();
		MatrizMath original = new MatrizMath(f,c);
		original = this.clone();
		for(int i=0; i<f; i++){
			for(int j=0; j<c; j++){
				if(i==j){
					double aux = original.matriz[i][j];
					for(int k=0; k<c-1; k++){
						resultado.matriz[i][k] /= aux;
						original.matriz[i][k] /= aux;
					}
					for(int l=i+1;l<f;l++){
						double aux2 = original.matriz[l][j];
						for(int m=0;m<c;m++){
							original.matriz[l][m] -= (aux2 * original.matriz[i][m]);
							resultado.matriz[l][m] -= (aux2 * resultado.matriz[i][m]);
						}
					}
				}
			}
		}
		for(int l=f-2;l>0;l--){
			double aux = original.matriz[l][c-1];
			for(int m=c-1;m>0;m--){
				original.matriz[l][m] -= (aux * original.matriz[f-1][m]);
				resultado.matriz[l][m] -= (aux * resultado.matriz[f-1][m]);
			}
		}
		return resultado;
	}
	
	public void crearIdentidad(){
		for(int i=0;i<f;i++){
			for(int j=0;j<c;j++){
				if(i==j)
					matriz[i][j] = 1;
				else
					matriz[i][j] = 0;
			}
		}
	}
	*/
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
		return null;
		
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