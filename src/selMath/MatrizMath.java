package selMath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class MatrizMath {
	private double[][] matriz;
	int fila, columna;
/*
	@Deprecated
	public MatrizMath(String path) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(path));
		fila = sc.nextInt();
		columna = sc.nextInt();
		matriz = new double[fila][columna];
  //		while (sc.hasNext()) {
		for(int i=0; i<fila*columna;i++){
			matriz[sc.nextInt()][sc.nextInt()] = sc.nextDouble();
		}
		sc.close();
	}
	*/
	public MatrizMath(String path) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(path));
		fila = sc.nextInt();
		columna = fila;
		matriz = new double[fila][columna];
		for(int i=0; i<fila*columna;i++){
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


	public MatrizMath inversa() throws InversibleException{
		
		MatrizMath identidad = crearIdentidad();
		MatrizMath inversa = this.clone();

		inversa.gaussJordan(identidad);

		return identidad;
	}


	public void gaussJordan(MatrizMath matrizDerecha) throws InversibleException{
		this.triangularSuperior(matrizDerecha);
		this.triangularInferior(matrizDerecha);
	}

	public void triangularSuperior(MatrizMath matrizDerecha) throws InversibleException{ //la parte de abajo
		for(int k=0;k<this.fila-1;k++){
			this.moverFilas(matrizDerecha, k);
            setUnoEnLaFila(matrizDerecha, k);
            for(int i=k+1;i<this.fila;i++){
				double valor = this.matriz[i][k];
				for(int j=0;j<this.columna;j++){
					this.matriz[i][j] -= (this.matriz[k][j] * valor);
					matrizDerecha.matriz[i][j] -= (matrizDerecha.matriz[k][j] * valor);
				}
			}
            if(this.verificarInversa()==false){
    			throw new InversibleException("No se puede obtener Inversa");
            }
		}
	}

    private void moverFilas(MatrizMath matrizDerecha, int pivote) {
        int i = 1;
        while(this.matriz[pivote][pivote] == 0 && i < fila - pivote){
            if(this.matriz[pivote+i][pivote] == 0){
                i++;
            } else{
                intercambiarFilas(matrizDerecha, pivote, i);
            }
        }
    }

    //FIXME Refactorizar esto
    private void intercambiarFilas(MatrizMath matrizDerecha, int pivote, int i) {
        MatrizMath matrizAuxiliar = this.clone();
        MatrizMath matrizDerechaAuxiliar = matrizDerecha.clone();
        for(int j = 0 ; j < columna ; j++){
            matrizAuxiliar.matriz[pivote][j] = this.matriz[pivote+i][j];
            matrizAuxiliar.matriz[pivote+i][j] = this.matriz[pivote][j];
            matrizDerechaAuxiliar.matriz[pivote][j] = matrizDerecha.matriz[pivote+i][j];
            matrizDerechaAuxiliar.matriz[pivote+i][j] = matrizDerecha.matriz[pivote][j];
        }
        this.setMatriz(matrizAuxiliar.matriz);
        matrizDerecha.setMatriz(matrizDerechaAuxiliar.matriz);
    }

    private void setUnoEnLaFila(MatrizMath matrizDerecha, int k) {
        double valorcin = this.matriz[k][k];
        for(int w = 0 ; w < this.columna ;w++){
            this.matriz[k][w] /= valorcin;
            matrizDerecha.matriz[k][w] /= valorcin;
        }
    }

    public void triangularInferior(MatrizMath matrizDerecha){ //la parte de arriba
		for(int k=this.fila-1;k>0;k--){
            setUnoEnLaFila(matrizDerecha, k);
            for(int i=k-1;i>=0;i--){
				double valor = this.matriz[i][k];
				for(int j=this.columna-1;j>=0;j--){
					this.matriz[i][j] -= (this.matriz[k][j] * valor);
					matrizDerecha.matriz[i][j] -= (matrizDerecha.matriz[k][j] * valor);
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
	
	@SuppressWarnings("unused")
	private boolean comprobarMatrizInversa() {
		MatrizMath identidad = crearIdentidad();
		if(identidad.resta(this).normaDos()< Math.pow(10,-6)){		
			return true;
		}
		return false;
	}

    public VectorMath toVectorMath() {
        VectorMath vectorMath = new VectorMath(this.matriz.length);
        double[]vector = new double[this.matriz.length];
        for(int i = 0 ; i < this.matriz.length ; i++){
            vector[i] = this.matriz[i][0];
        }
        vectorMath.setVector(vector);
        return vectorMath;
    }
    
  
   
    public boolean verificarInversa(){

    	for(int i=0;i<this.fila;i++){
    		int j=0;
    		while(j<this.columna&&this.matriz[i][j]==0){
    			j++;
    		}
    		if(j==this.columna)
    			return false;
    	}
    	for(int i=0;i<this.columna;i++){    	
    		int k=0;
    		while(k<this.fila&&this.matriz[k][i]==0){
    			k++;
    		}
    		if(k==this.fila)
    			return false;
    	}

    	return true;
    }

    
}
   