package br.com.AgendaConsultorioMedico.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.javafx.beans.IDProperty;

@Entity
public class Consultorio {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private long numeroConsultorio;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getNumeroConsultorio() {
		return numeroConsultorio;
	}
	public void setNumeroConsultorio(long numeroConsultorio) {
		this.numeroConsultorio = numeroConsultorio;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Consultorio other = (Consultorio) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}

