package br.com.AgendaConsultorioMedico.Dao;

import java.util.List;

public interface DaoInterface<T> {

	void salvar(T objeto)throws Exception;
	
	void deletar (T objeto)throws Exception;
	
	void salvarOuAtualizar (T objeto)throws Exception;
	
	void atualizar (T objeto)throws Exception;
	
	List<T> lista() throws Exception;

	T merge(T objeto) throws Exception;

	T loadObjeto(Long codigo) throws Exception;

	List<T> lista(String campoBanco, String valorCampo) throws Exception;

	List<T> lista(String campoBanco, Long valorCampo) throws Exception;

	List<T> listaLikeExpression(String campoBanco, String valorCampo) throws Exception;

	List<T> lista(String ids) throws Exception; 
}
