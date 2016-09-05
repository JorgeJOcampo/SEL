package complejo;

import java.util.Arrays;

public class Complejo implements Comparable<Complejo> {

	private float real;
	private float imaginario;

	public Complejo(float real, float imaginario) {
		setReal(real);
		setImaginario(imaginario);
	}

	public Complejo() {
		setReal(0);
		setImaginario(0);
	}

	public String toString() {
		return "(" + getReal() + ";" + getImaginario() + "i)";
	}

	public float getReal() {
		return real;
	}

	public void setReal(float real) {
		this.real = real;
	}

	public float getImaginario() {
		return imaginario;
	}

	public void setImaginario(float imaginario) {
		this.imaginario = imaginario;
	}

	public boolean equals(Complejo complejo1) {
		if (getReal() == complejo1.getReal()
				&& getImaginario() == this.getImaginario())
			return true;
		return false;
	}

	public Complejo sumar(Complejo c2) {
		return new Complejo(this.getReal() + c2.getReal(), this.getImaginario()
				+ c2.getImaginario());
	}

	public Complejo sumar(int num) {
		return new Complejo(this.getReal() + num, this.getImaginario());
	}

	public Complejo sumar(float num) {
		return new Complejo(this.getReal() + num, this.getImaginario());
	}

	public Complejo restar(Complejo c2) {
		return new Complejo(this.getReal() - c2.getReal(), this.getImaginario()
				- c2.getImaginario());
	}

	public Complejo restar(int num) {
		return new Complejo(this.getReal() - num, this.getImaginario());
	}

	public Complejo restar(float num) {
		return new Complejo(this.getReal() - num, this.getImaginario());
	}

	public Complejo multiplicar(int num) {
		return new Complejo(this.getReal() * num, this.getImaginario() * num);
	}

	public Complejo multiplicar(float num) {
		return new Complejo(this.getReal() * num, this.getImaginario() * num);
	}

	public Complejo multiplicar(Complejo c2) {
		return new Complejo(this.getReal() * c2.getReal()
				- this.getImaginario() * c2.getImaginario(),
				this.getImaginario() * c2.getReal() + this.getReal()
						* c2.getImaginario());
	}

	public void clone(Complejo c2) {
		setReal(c2.getReal());
		setImaginario(c2.getImaginario());
	}

	public float modulo() {
		return (float) Math.sqrt(Math.pow(getReal(), 2) + Math.pow(getImaginario(), 2));
	}

	@Override
	public int compareTo(Complejo c) {
		if (this.modulo() > c.modulo())
			return 1;
		if (this.modulo() < c.modulo())
			return -1;
		return 0;
	}

	public static void main(String[] args) {

		/*
		 * Complejo cp1= new Complejo(1,2); Complejo cp2=new Complejo(2,1);
		 * 
		 * System.out.println("Modulo del cp1: " + cp1.modulo() );
		 * System.out.println("suma" + cp1.sumar(cp2));
		 * System.out.println("resta " +cp1.restar(cp2));
		 * System.out.println("multi " +cp1.multiplicar(cp2)); cp2.clone(cp1);
		 * System.out.println("CP1 sumado a un real "+ cp1.sumar(4));
		 * System.out.println("CP2 clonado " + cp2 ); if(cp1.equals(cp2))
		 * System.out.println("son iguales"); else
		 * System.out.println("son distintos");
		 */

		Complejo comp[] = new Complejo[5];

		for (int i = 0; i < 5; i++) {
			comp[i] = new Complejo(i, 1 - i);
		}

		for (int i = 0; i < 5; i++) {
			System.out.println(comp[i]);
		}
		Arrays.sort(comp);
		System.out.println("Ordenado:");
		for (int i = 0; i < 5; i++) {
			System.out.println(comp[i]);
		}
	}
}
