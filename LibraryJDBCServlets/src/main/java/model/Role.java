package model;

public enum Role {
	ADMIN("admin"), USER("user");
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String name;

	Role(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
