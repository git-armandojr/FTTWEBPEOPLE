package br.edu.cefsa.ftt.ec.model;

import java.io.Serializable;

public class Brand implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	long id;
	
	String name;

	public Brand(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Brand(String id, String name) {
		super();
		this.setId(id);
		this.setName(name);
	}

	public Brand() {
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
	
	@Override
	public boolean equals(Object obj) {
		
		if(obj != null && obj instanceof Brand) {

            return (this.getId() == ((Brand) obj).getId());

        }  else {
        	
            return false;

        } //if

	} //equals
	
	@Override
    public String toString() {
        return "Brand ["
                + "  id=" + this.id 
        		+ ", name=" + this.name
                + "]";
    } //toString  

} //Brand
