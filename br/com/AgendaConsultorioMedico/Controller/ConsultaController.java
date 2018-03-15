package br.com.AgendaConsultorioMedico.Controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import br.com.AgendaConsultorioMedico.Dao.DaoImplementacao;
import br.com.AgendaConsultorioMedico.Dao.DaoInterface;
import br.com.AgendaConsultorioMedico.Model.ConsultaMedica;
import br.com.AgendaConsultorioMedico.Model.Medico;

@Controller
@RequestMapping(value="/consulta")
public class ConsultaController extends DaoImplementacao<ConsultaMedica> implements DaoInterface<ConsultaMedica>{
	
	public ConsultaController(Class<ConsultaMedica> persistenceClass) { 
		super(persistenceClass);
	}
	
	@RequestMapping(value="salvar", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity salvar (@RequestBody String jsonConsultaMedica) throws Exception {
		 ConsultaMedica consulta = new Gson().fromJson(jsonConsultaMedica, ConsultaMedica.class);
		 
		 super.salvarOuAtualizar(consulta);
		 return new ResponseEntity(HttpStatus.CREATED);
		 
	 }
	
	@RequestMapping(value = "listar/{numeroPagina}", method = RequestMethod.GET)
	@ResponseBody
	public byte[] listar(@PathVariable("numeroPagina") String numeroPagina) throws Exception {

		return new Gson().toJson(super.consultaPaginada(numeroPagina)).getBytes("UTF-8");
	}
	
	@RequestMapping(value="totalPagina", method=RequestMethod.GET,headers = "Accept=application/json")
	@ResponseBody
	public String totalPagina() throws Exception{
		return ""+super.quantidadePagina();
	}
	

	@RequestMapping(value="deletar/{codConsultaMedica}", method=RequestMethod.DELETE)
	public  @ResponseBody String deletar (@PathVariable("codConsultaMedica") String codConsultaMedica) throws Exception {
		super.deletar(loadObjeto(Long.parseLong(codConsultaMedica)));
		return "";
	}

	@RequestMapping(value = "buscarconsulta{codConsultaMedica}", method = RequestMethod.GET)
	public @ResponseBody byte[] buscarMedico(@PathVariable("codConsultaMedica") String codConsultaMedica) throws Exception {
		ConsultaMedica objeto = super.loadObjeto(Long.parseLong(codConsultaMedica));
		if (objeto == null) {
			return "{}".getBytes("UTF-8");
		}
		return new Gson().toJson(objeto).getBytes("UTF-8");

	}
	
}


