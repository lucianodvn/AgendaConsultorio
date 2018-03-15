package br.com.AgendaConsultorioMedico.Controller;

import java.util.ArrayList;
import java.util.List;

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
import br.com.AgendaConsultorioMedico.Model.Consultorio;
import br.com.AgendaConsultorioMedico.Model.Medico;

	@Controller
	@RequestMapping(value="/consultorio")
	public class ConsultorioController extends DaoImplementacao<Consultorio> implements DaoInterface<Consultorio> {

		public ConsultorioController(Class<Consultorio> persistenceClass) {
			super(persistenceClass);
			// TODO Auto-generated constructor stub
		}

		
		@RequestMapping(value="salvar", method=RequestMethod.POST)
		@ResponseBody
		public ResponseEntity salvar (@RequestBody String jsonConsultorio) throws Exception {
			Consultorio consultorio = new Gson().fromJson(jsonConsultorio, Consultorio.class);
			 
			 super.salvarOuAtualizar(consultorio);
			 return new ResponseEntity(HttpStatus.CREATED);
			 
		 }
		
		@RequestMapping(value = "listar/{numeroPagina}", method = RequestMethod.GET)
		@ResponseBody
		public byte[] listar(@PathVariable("numeroPagina") String numeroPagina) throws Exception {

			return new Gson().toJson(super.consultaPaginada(numeroPagina)).getBytes("UTF-8");
		}
		
		@RequestMapping(value = "listartodos", method = RequestMethod.GET, headers = "Accept=application/json")
		@ResponseBody
		public String listartodos()
				throws Exception {
			return new Gson().toJson(super.lista());
		}
		
		@RequestMapping(value="totalPagina", method=RequestMethod.GET,headers = "Accept=application/json")
		@ResponseBody
		public String totalPagina() throws Exception{
			return ""+super.quantidadePagina();
		}
			

		@RequestMapping(value="deletar/{codConsultorio}", method=RequestMethod.DELETE)
		public  @ResponseBody String deletar (@PathVariable("codConsultorio") String codConsultorio) throws Exception {
			super.deletar(loadObjeto(Long.parseLong(codConsultorio)));
			return "";
		}
		
		@RequestMapping(value = "buscarConsultorio/{codConsultorio}", method = RequestMethod.GET)
		public @ResponseBody byte[] buscarConsultorio(@PathVariable("codConsultorio") String codConsultorio) throws Exception {
			Consultorio objeto = super.loadObjeto(Long.parseLong(codConsultorio));
			if (objeto == null) {
				return "{}".getBytes("UTF-8");
			}
			return new Gson().toJson(objeto).getBytes("UTF-8");

	}
}

