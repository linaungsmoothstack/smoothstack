package week.one;

public class Circle implements Shape {

	private double radius;
	
	public Circle(double radius) {
		this.radius = radius;
	}

	@Override
	public double calculateArea() {
		return Math.PI + Math.pow(radius, 2);
	}

	@Override
	public void display() {
		System.out.println(this);
	}
	
	@Override
	public String toString() {
		return "Circle[ radius: " + radius + " ]";
	}
	
	public static void main(String[] args) {
		Shape circle = new Circle(3.5);
		circle.display();
		System.out.println("Area: " + circle.calculateArea());
	}

}
