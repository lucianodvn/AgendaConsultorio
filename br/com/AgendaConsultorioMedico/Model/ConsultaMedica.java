package br.com.AgendaConsultorioMedico.Model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;

@Entity
public class ConsultaMedica {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String nomePaciente;
	private String EspecialidadeMed;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@ForeignKey(name="medico_fk")
	private Medico nomeMedico;
	
	private String crm;

	
	//@Temporal(TemporalType.TIMESTAMP)
	//private Date data = Calendar.getInstance().getTime();
	
	private String data;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@ForeignKey(name="consultorio_fk")
	private Consultorio numeroConsultorio;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public String getEspecialidadeMed() {
		return EspecialidadeMed;
	}

	public void setEspecialidadeMed(String especialidadeMed) {
		EspecialidadeMed = especialidadeMed;
	}

	public Medico getNomeMedico() {
		return nomeMedico;
	}

	public void setNomeMedico(Medico nomeMedico) {
		this.nomeMedico = nomeMedico;
	}
	
	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Consultorio getNumeroConsultorio() {
		return numeroConsultorio;
	}

	public void setNumeroConsultorio(Consultorio numeroConsultorio) {
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
		ConsultaMedica other = (ConsultaMedica) obj;
		if (id != other.id)
			return false;
		return true;
	}
		
}
