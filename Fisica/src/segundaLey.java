/**
 * @author Cesar Augusto Garcia Perez
 *
 */

public class segundaLey {
	vectorNode[] vectores;
	int counter = 0;
	int size = 0;
	double sumFx=0, sumFy=0;
	
	public segundaLey(int numVectores){
		vectores = new vectorNode[numVectores];
	}
	
	public void createAdd(int value, double angule){
		vectorNode vector =  new vectorNode(value, angule);
		vectores[counter] = vector;
		counter++;
		size++;
	}
	
	public double opera(){
		for(int i=0;size>0;i++){
			sumFx = sumFx + vectores[i].fx;
			sumFy = sumFy + vectores[i].fy;
			size--;
		}
		return Math.sqrt((Math.pow(sumFx, 2))+(Math.pow(sumFy, 2)));
	}
	
	public double getAngule(){ 
		return (Math.atan(sumFy/sumFx)*180)/Math.PI;
	}
	
	private class vectorNode{
		int value;
		double fx;
		double fy;
		double angule;
		
		public vectorNode(int value, double angule){
			this.value=value;
			this.angule=angule;
			this.fx = getFxValue(this.value, this.angule);
			this.fy = getFyValue(this.value, this.angule);
		}
		
		private double getFxValue(int value, double angule){
			if ((angule>90 && angule<270)) {
				if(angule<180){
					return -(value*Math.cos(Math.toRadians(180-angule)));
				}else{
					return -(value*Math.cos(Math.toRadians(angule-180)));
				}
			}else if(angule>270 && angule<360){
				return (value*Math.cos(Math.toRadians(360-angule)));
			}
			return (value*Math.cos(Math.toRadians(angule)));
		}
		
		private double getFyValue(int value, double angule){
			if ((angule>180 && angule<360)) {
				if(angule<270){
					return -(value*Math.sin(Math.toRadians(angule-180)));
				}else{
					return -(value*Math.sin(Math.toRadians(angule-270)));
				}
			}else if(angule>90 && angule<180){
				return (value*Math.sin(Math.toRadians(180-angule)));
			}else{
				return (value*Math.sin(Math.toRadians(angule)));
			}
		}
	}
	
	public static void main(String[] args) {
		segundaLey sl = new segundaLey(3);
		sl.createAdd(15, 30);
		sl.createAdd(12, 75);
		sl.createAdd(18, 225);
		System.out.println("F: " + sl.opera());
		System.out.println("Angule is: " + sl.getAngule());
	}
}
