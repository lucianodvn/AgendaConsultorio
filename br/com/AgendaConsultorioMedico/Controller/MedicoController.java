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
import br.com.AgendaConsultorioMedico.Model.Medico;



@Controller
@RequestMapping(value="/medico")
public class MedicoController extends DaoImplementacao<Medico> implements DaoInterface<Medico> {

	public MedicoController(Class<Medico> persistenceClass) { 
		super(persistenceClass);
	}
	
	@RequestMapping(value="salvar", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity salvar (@RequestBody String jsonMedico) throws Exception {
		 Medico medico = new Gson().fromJson(jsonMedico, Medico.class);
		 
		 super.salvarOuAtualizar(medico);
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
	

	@RequestMapping(value="deletar/{codMedico}", method=RequestMethod.DELETE)
	public  @ResponseBody String deletar (@PathVariable("codMedico") String codMedico) throws Exception {
		super.deletar(loadObjeto(Long.parseLong(codMedico)));
		return "";
	}

	@RequestMapping(value = "buscarMedico/{codMedico}", method = RequestMethod.GET)
	public @ResponseBody byte[] buscarCliente(@PathVariable("codMedico") String codMedico) throws Exception {
		Medico objeto = super.loadObjeto(Long.parseLong(codMedico));
		if (objeto == null) {
			return "{}".getBytes("UTF-8");
		}
		return new Gson().toJson(objeto).getBytes("UTF-8");

	}
	
	@RequestMapping(value = "buscarnome/{nomeMedico}", method = RequestMethod.GET)
	public @ResponseBody byte[] buscarnome(@PathVariable("nomeMedico") String nomeMedico) throws Exception {
		List<Medico> medicos = new ArrayList<Medico>();
		medicos = super.listaLikeExpression("nome", nomeMedico);
		if (medicos == null || medicos.isEmpty()) {
			return "{}".getBytes("UTF-8");
		}
		return new Gson().toJson(medicos).getBytes("UTF-8");

	}


	@Override
	public List<Medico> consultaPaginada(String numeroPagina) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}

