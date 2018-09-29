package br.edu.cefsa.ftt.ec.model;

import java.io.Serializable;

public class Model implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	long id;
	
	String name,
		   brand;

	public Model(long id, String name, String brand) {
		super();
		this.id = id;
		this.name = name;
		this.brand = brand;
	}
	
	public Model(String id, String name, String brand) {
		super();
		this.setId(id);;
		this.setName(name);
		this.setBrand(brand);
	}

	public Model() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public void setId(String id) {
		if (id != null)
		   this.id = Long.valueOf(id);
		else
		   this.id = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(obj != null && obj instanceof Model) {

            return (this.getId() == ((Model) obj).getId());

        }  else {
        	
            return false;

        } //if

	} //equals
	
	@Override
    public String toString() {
        return "Brand ["
                + "  id=" + this.id 
        		+ ", name=" + this.name
        		+ ", brand=" + this.brand
                + "]";
    } //toString 
}
