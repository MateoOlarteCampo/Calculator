
public class Model{
	double result;
	double sto; 	//Esta es la memoria
	
	
	public double getSto() {
		return sto;
	}

	public void setSto(double sto) {
		this.sto = sto;
	}

	public double add(double operator1, double operator2) {
		result = operator1 + operator2;
		return result;
	}
	
	public double sub(double operator1, double operator2) {
		result = operator1 - operator2;
		return result;
	}
	
	public double div(double operator1, double operator2) {
		result = operator1 / operator2;
		return result;
	}
	
	public double product(double operator1, double operator2) {
		result = operator1 * operator2;
		return result;
	}
	
	public double squareRoot(double operator1) {
		result = Math.sqrt(operator1);
		return result;
	}
	
	public double squared(double operator1) {
		result = Math.pow(operator1, 2);
		return result;
	}
	
	public void M(double operator1) {
		this.sto = operator1;
	}
	
	public void Mplus() {
		this.sto++;
	}
	
	public void Msub() {
		this.sto--;
	}
	
	public void MC() {
		this.sto = 0;
	}
	
	
	
	
	
       
}