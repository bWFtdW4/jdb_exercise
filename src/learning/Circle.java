package learning;

public class Circle {

	private double radius = 1.0;


	public Circle (double radius) {
		this.radius = radius;
	}


	public double getRadius () {
		return radius;
	}


	public void setRadius (double radius) {
		this.radius = radius;
	}


	public double getArea () {
		return this.radius * this.radius * Math.PI;
	}


	public double getCircumference () {
		return this.radius * Math.PI;
	}
	
	@Override
	public String toString() {
		String stringerString = "Circle radius =" + this.radius;
		return stringerString;
		
	}


	public static void main (String[] args) {
		Circle myCircle = new Circle(2);
		System.out.println(myCircle.getArea());
		System.out.println(myCircle.getCircumference());
		System.out.println(myCircle.toString());
	}

}
