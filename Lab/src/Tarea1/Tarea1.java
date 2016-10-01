
public class Tarea1 {

	private void enAscendente(int n){
		if(n>=0){
			enAscendente(n-1);
			System.out.println(n);
		}
	}

	private void enDesendente(int n){
		if(n>=0){
			System.out.println(n);
			enDesendente(n-2);
		}
	}

	private int divisionporResta(int dividendo, int divisor){
		if(divisor>dividendo){
			return 0;
		}else{
			return divisionporResta(dividendo-divisor, divisor)+1;
		}

	}

	private int mcd(int a, int b) {
	        if(b==0){
	            return a;
	        }else{
	            return mcd(b, a % b);
            }
	    }

	private int sumaDigitos(int n){
		if (n == 0){
		    return n;
		}else{
		    return sumaDigitos (n / 10) + (n % 10);
	    }
	}

	public static void main(String[] args) {
		Clase1 actividades = new Clase1();
		System.out.println("Ascendente");
		actividades.enAscendente(10); System.out.println("Decendente");
		actividades.enDesendente(16); System.out.println("Divisi�n por resta");
		System.out.println(actividades.divisionporResta(11,3)); System.out.println("sumaDigitos");
		System.out.println(actividades.sumaDigitos(1234)); System.out.println("invertir");
		System.out.println(actividades.mcd(27,3));

	}

}
