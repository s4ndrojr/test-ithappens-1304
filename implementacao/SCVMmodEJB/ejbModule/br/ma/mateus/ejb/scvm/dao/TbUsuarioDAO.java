package br.ma.mateus.ejb.scvm.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import br.ma.mateus.ejb.scvm.dao.GenericDAO;
import br.ma.mateus.ejb.scvm.model.TbUsuario;

@Stateless
@LocalBean
public class TbUsuarioDAO extends GenericDAO<TbUsuario> {

	public TbUsuarioDAO() {
		super(TbUsuario.class);
	}

	public List<TbUsuario> getLista() {
		Map<String, Object> parameters = new HashMap<String, Object>();

		return super.getLista("TbUsuario.findAll", parameters);
	}

	public TbUsuario hasUsuario(String login, String senha) {
		// TODO Auto-generated method stub
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		parameters.put("login", login);
		parameters.put("senha", senha);
		
		return super.findOneResult(TbUsuario.QUERY_FINDUSUARIO, parameters);
	}

	public List<TbUsuario> find(Integer id, String loginUsuario, String nomeUsuario, String registroExcluido) {
		// TODO Auto-generated method stub
		Map<String, Object> parameters = new HashMap<String, Object>();
		StringBuilder builderSQL = new StringBuilder();
		StringBuilder builderWhere = new StringBuilder();

		builderWhere.append(" t.idUsuario is not null");

		if (id != null) {
			builderWhere.append(" and t.idUsuario = :id ");
			parameters.put("id", id.intValue());
		}

		if (nomeUsuario != null && !nomeUsuario.trim().equals("")) {
			builderWhere.append(" and t.nomeUsuario like :nomeUsuario ");
			parameters.put("nomeUsuario", nomeUsuario);
		}
		
		if (loginUsuario != null && !loginUsuario.trim().equals("")) {
			builderWhere.append(" and t.loginUsuario like :loginUsuario ");
			parameters.put("loginUsuario", loginUsuario);
		}
		
		if (registroExcluido != null && !registroExcluido.trim().equals("")) {
			builderWhere.append(" and t.registroExcluido like :registroExcluido ");
			parameters.put("registroExcluido", registroExcluido);
		}

		builderSQL.append(" SELECT ");
		builderSQL.append(" t ");
		builderSQL.append(" FROM ");
		builderSQL.append(" TbUsuario t");
		builderSQL.append(" WHERE ");
		builderSQL.append(builderWhere);
		builderSQL.append(" ORDER BY t.nomeUsuario ASC ");

		return super.getListaPorQuery(builderSQL.toString(), parameters);
	}

}