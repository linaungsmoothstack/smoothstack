package week.one;

public class Rectangle implements Shape {
	
	private double length, width;
	
	public Rectangle(double length, double width) {
		this.length = length;
		this.width = width;
	}

	@Override
	public double calculateArea() {
		return length * width;
	}

	@Override
	public void display() {
		System.out.println(this);
	}
	
	@Override
	public String toString() {
		return "Rectangle[ length: " + length + ", width: " + width + " ]";
	}
	
	public static void main(String[] args) {
		Shape rect = new Rectangle(3, 6);
		rect.display();
		System.out.println("Area: " + rect.calculateArea());
	}

}
