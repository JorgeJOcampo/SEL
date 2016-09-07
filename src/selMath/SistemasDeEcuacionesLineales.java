package selMath;

import java.io.FileNotFoundException;

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
		matriz= new MatrizMath(path);
		resultado=new VectorMath(path);
		//FIXME buscar la forma de que esto ande asi para reutilizar codigo.
		
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
