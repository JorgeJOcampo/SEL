package complejo;

public class OrdenadoraDeComplejos {
	
	public static void ordenar(Complejo c1[],int num) {

		switch(num) {
		case 0:

			for(int i=0; i<c1.length;i++) {
			
				for(int j=0;j<c1.length-i-1;j++) {
				
					if((c1[j].compareTo(c1[j+1])) == 1) {
					
						Complejo aux;
						aux=c1[j];
						c1[j]=c1[j+1];
						c1[j+1]=aux;
					}
				}

			}
			break;
		case 1:
			for(int i=0; i<c1.length;i++) {
			
				for(int j=0;j<c1.length-i-1;j++) {
				
					if(c1[j].getReal()>c1[j+1].getReal()) {
					
						Complejo aux;
						aux=c1[j];
						c1[j]=c1[j+1];
						c1[j+1]=aux;
					}
				}

			}
			break;
		case 2:
			
			for(int i=0; i<(c1.length);i++){
			
				for(int j=0;j<(c1.length-i-1);j++){
				
					if(c1[j].getImaginario()>c1[j+1].getImaginario()){
					
						Complejo aux;
						aux=c1[j];
						c1[j]=c1[j+1];
						c1[j+1]=aux;
					}
				}

			}
			break;

		}
		
	}

}
