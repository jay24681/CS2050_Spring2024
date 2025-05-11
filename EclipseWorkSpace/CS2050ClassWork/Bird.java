abstract class Bird {
	private String name;
	private String type;
	
	public Bird(String name, String type) {
		this.name = name; 
		this.name = type;
	}
	
	public abstract String getFact();
	
	public String getName() {
		return name
	}
	
	public String getType() {
		return type
	}
	
	@Override
	public String toString() { 
		return type + " ( " + name + " ): " + getFact();
	}
	
	interface Swimable {
		int getSwimSpeed()
	}
}