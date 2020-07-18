package br.ma.mateus.ejb.scvm.facade;

import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import br.ma.mateus.ejb.scvm.model.TbUsuario;

@Local
public interface UsuarioFacade {
	public abstract List<TbUsuario> findAll();

	public abstract TbUsuario hasUsuario(String matricula, String senha);

	public abstract List<TbUsuario> find(Integer id, String loginUsuario, String nomeUsuario);
	
	public abstract List<TbUsuario> find(Integer id, String loginUsuario, String nomeUsuario, String registroExcluido);

	public abstract Integer save(String loginUsuario, String nomeUsuario, String senha, String usuarioInsercao, Date dataInsercao,
			String usuarioAlteracao, Date dataAlteracao, String registroExcluido, String usuarioExclusao,
			Date dataExclusao) throws Exception;

	public abstract void update(Integer id, String loginUsuario, String nomeUsuario, String senha, String usuarioInsercao,
			Date dataInsercao, String usuarioAlteracao, Date dataAlteracao, String registroExcluido,
			String usuarioExclusao, Date dataExclusao) throws Exception;
}