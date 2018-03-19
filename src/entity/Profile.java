package entity;

import java.awt.Image;
import java.util.ArrayList;

public abstract class Profile {

	private String name;
	private int age;
	private Image image;
	private String status;
	
	public Profile(String name, int age, String status) {
		// TODO Auto-generated constructor stub
		setName(name);
		setAge(age);
		setStatus(status);
	}
	
	public abstract void print2Screen();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
