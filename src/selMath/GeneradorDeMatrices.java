package selMath;

import java.io.FileWriter;
import java.io.PrintWriter;

public class GeneradorDeMatrices {
	
	public static MatrizMath generarFatiga(){
		double[][] mat = new double[1000][1000];
		MatrizMath matriz = new MatrizMath(1000,1000);
		for(int i=0;i<1000;i++){
			for(int j=0;j<1000;j++){
				if(i!=j)
					mat[i][j] = 2;
				else
					mat[i][j] = 1;
			}
		}
		matriz.setMatriz(mat);
		return matriz;
	}

	public static void aArchivoDePrueba(){
		FileWriter fichero = null;
		PrintWriter pw = null;
		try {
			fichero = new FileWriter("F:\\UNLaM\\Programación Avanzada\\GitKraken - Workspace\\SEL\\Preparación de Prueba\\Lote de Prueba\\IN\\10_casoFatiga.in");
			pw = new PrintWriter(fichero);

			MatrizMath matriz = generarFatiga();
			pw.println(matriz.fila);
			for(int i=0;i<1000;i++){
				for(int j=0;j<1000;j++){
					pw.println(i+" "+j+" "+matriz.getMatriz()[i][j]);
				}
			}
			for(int k=0;k<1000;k++){
				pw.println("1");
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			try {
				if (null != fichero)
					fichero.close();
			} 
			catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args){
		aArchivoDePrueba();
	}
}
