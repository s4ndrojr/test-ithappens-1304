package br.ma.mateus.ejb.scvm.imp;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import br.ma.mateus.ejb.scvm.facade.UsuarioFacade;
import br.ma.mateus.ejb.scvm.model.TbFormaPagamento;
import br.ma.mateus.ejb.scvm.model.TbProduto;
import br.ma.mateus.ejb.scvm.model.TbUsuario;
import br.ma.mateus.ejb.scvm.util.Constantes;
import br.ma.mateus.ejb.scvm.util.Criptografia;
import br.ma.mateus.ejb.scvm.dao.TbUsuarioDAO;

@Stateless
@LocalBean
public class UsuarioFacadeImp implements UsuarioFacade {
	@EJB
	private TbUsuarioDAO tbUsuarioDAO;

	@Override
	public List<TbUsuario> findAll() {
		return tbUsuarioDAO.getLista();
	}

	@Override
	public TbUsuario hasUsuario(String login, String senha) {
		// TODO Auto-generated method stub
		String senhaCriptografa = Criptografia.encriptarSenha(senha);
		return tbUsuarioDAO.hasUsuario(login, senhaCriptografa);
	}

	@Override
	public List<TbUsuario> find(Integer id, String loginUsuario, String nomeUsuario) {
		// TODO Auto-generated method stub
		return this.find(id, loginUsuario, nomeUsuario, Constantes.REGISTRO_NAO_EXCLUIDO);
	}
	
	@Override
	public List<TbUsuario> find(Integer id, String loginUsuario, String nomeUsuario, String registroExcluido) {
		// TODO Auto-generated method stub
		return tbUsuarioDAO.find(id, loginUsuario, nomeUsuario, registroExcluido);
	}

	@Override
	public Integer save(String loginUsuario, String nomeUsuario, String senha, String usuarioInsercao, Date dataInsercao,
			String usuarioAlteracao, Date dataAlteracao, String registroExcluido, String usuarioExclusao,
			Date dataExclusao) throws Exception {
		// TODO Auto-generated method stub
		TbUsuario usuario = null;
		
		try {
			String senhaCriptografa = Criptografia.encriptarSenha(senha);
			usuario = this.montarTbUsuario(null, loginUsuario, nomeUsuario, senhaCriptografa, usuarioInsercao, dataInsercao, 
					usuarioAlteracao, dataAlteracao, registroExcluido, usuarioExclusao, dataExclusao);
		
			this.tbUsuarioDAO.save(usuario);
		} catch (Exception e) {
			usuario = null;
			throw e;
			// TODO: handle exception
		}
		
		return usuario == null ? null : usuario.getIdUsuario();
	}

	private TbUsuario montarTbUsuario(Integer id, String loginUsuario, String nomeUsuario, String senhaUsuario, 
			String usuarioInsercao, Date dataInsercao, String usuarioAlteracao, Date dataAlteracao,
			String registroExcluido, String usuarioExclusao, Date dataExclusao) {
		// TODO Auto-generated method stub
		TbUsuario usuario = new TbUsuario();
		usuario.setIdUsuario(id);
		usuario.setLoginUsuario(loginUsuario);
		usuario.setNomeUsuario(nomeUsuario);
		usuario.setSenhaUsuario(senhaUsuario);
		usuario.setUsuarioInsercao(usuarioInsercao);
		usuario.setDataInsercao(dataInsercao);
		usuario.setUsuarioAlteracao(usuarioAlteracao);
		usuario.setDataAlteracao(dataAlteracao);
		usuario.setRegistroExcluido(registroExcluido);
		usuario.setUsuarioExclusao(usuarioExclusao);
		usuario.setDataExclusao(dataExclusao);
		
		return usuario;
	}

	@Override
	public void update(Integer id, String loginUsuario, String nomeUsuario, String senha, String usuarioInsercao, Date dataInsercao,
			String usuarioAlteracao, Date dataAlteracao, String registroExcluido, String usuarioExclusao,
			Date dataExclusao) throws Exception {
		// TODO Auto-generated method stub
		TbUsuario usuario = null;
		try {
			String senhaCriptografa = Criptografia.encriptarSenha(senha);
			usuario = this.montarTbUsuario(id, loginUsuario, nomeUsuario, senhaCriptografa, usuarioInsercao, dataInsercao, 
					usuarioAlteracao, dataAlteracao, registroExcluido, usuarioExclusao, dataExclusao);
			
			this.tbUsuarioDAO.update(usuario);
		} catch (Exception e) {
			usuario = null;
			throw e;
			// TODO: handle exception
		}
	}


}