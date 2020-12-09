package week.one;

public class Triangle implements Shape {

	private double base, height;
	
	public Triangle(double base, double height) {
		this.base = base;
		this.height = height;
	}
	
	@Override
	public double calculateArea() {
		return 0.5 * base * height;
	}

	@Override
	public void display() {
		System.out.println(this);
	}
	
	@Override
	public String toString() {
		return "Triangle[ basse: " + base + ", height: " + height + " ]";
	}
	
	public static void main(String[] args) {
		Shape triangle = new Triangle(3.5, 4.0);
		triangle.display();
		System.out.println("Area: " + triangle.calculateArea());
	}
}
