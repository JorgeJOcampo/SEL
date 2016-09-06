package selMath;

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
	
	public boolean test(){
		return cantidadDeIncognitas == cantidadDeEcuaciones;
	}
	
	public void mostrarResultado(){
		System.out.println(resultado);
	}
}
