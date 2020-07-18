package br.ma.mateus.ejb.scvm.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import br.ma.mateus.ejb.scvm.dao.GenericDAO;
import br.ma.mateus.ejb.scvm.model.TbCliente;

@Stateless
@LocalBean
public class TbClienteDAO extends GenericDAO<TbCliente> {

	public TbClienteDAO() {
		super(TbCliente.class);
	}

	public List<TbCliente> getLista() {
		Map<String, Object> parameters = new HashMap<String, Object>();

		return super.getLista("TbCliente.findAll", parameters);
	}

	public List<TbCliente> find(Integer idCliente, String cpfCnpjCliente, String nomeCliente, String registroExcluido) {
		// TODO Auto-generated method stub
		Map<String, Object> parameters = new HashMap<String, Object>();
		StringBuilder builderSQL = new StringBuilder();
		StringBuilder builderWhere = new StringBuilder();

		builderWhere.append(" t.idProduto is not null");

		if (idCliente != null) {
			builderWhere.append(" and t.idCliente = :idCliente ");
			parameters.put("idCliente", idCliente.intValue());
		}

		if (cpfCnpjCliente != null && !cpfCnpjCliente.trim().equals("")) {
			builderWhere.append(" and t.cpfCnpjCliente like :cpfCnpjCliente ");
			parameters.put("cpfCnpjCliente", cpfCnpjCliente);
		}
		
		if (nomeCliente != null && !nomeCliente.trim().equals("")) {
			builderWhere.append(" and t.nomeCliente like :nomeCliente ");
			parameters.put("nomeCliente", nomeCliente);
		}
		
		if (registroExcluido != null && !registroExcluido.trim().equals("")) {
			builderWhere.append(" and t.registroExcluido like :registroExcluido ");
			parameters.put("registroExcluido", registroExcluido);
		}

		builderSQL.append(" SELECT ");
		builderSQL.append(" t ");
		builderSQL.append(" FROM ");
		builderSQL.append(" TbCliente t");
		builderSQL.append(" WHERE ");
		builderSQL.append(builderWhere);
		builderSQL.append(" ORDER BY t.nomeCliente ASC ");

		return super.getListaPorQuery(builderSQL.toString(), parameters);
	}

}