package br.ma.mateus.ejb.scvm.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import br.ma.mateus.ejb.scvm.dao.GenericDAO;
import br.ma.mateus.ejb.scvm.model.TbEstoque;
import br.ma.mateus.ejb.scvm.model.TbItensPedido;

@Stateless
@LocalBean
public class TbItensPedidoDAO extends GenericDAO<TbItensPedido> {

	public TbItensPedidoDAO() {
		super(TbItensPedido.class);
	}

	public List<TbItensPedido> getLista() {
		Map<String, Object> parameters = new HashMap<String, Object>();

		return super.getLista("TbItensPedido.findAll", parameters);
	}

	public List<TbItensPedido> findById(Integer idItensPedido) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		StringBuilder builderSQL = new StringBuilder();
		StringBuilder builderWhere = new StringBuilder();

		/*
		 * builderWhere.append(" t.[idTable] is not null");
		 * 
		 * if ([field] != null && ![field].trim().equals("")) {
		 * builderWhere.append(" and t.[field] like :[field] ");
		 * parameters.put("[field]", [field]); }
		 */

		builderWhere.append(" and t.idItensPedido like :idItensPedido ");
		parameters.put("idItensPedido", idItensPedido);

		builderSQL.append(" SELECT ");
		builderSQL.append(" t ");
		builderSQL.append(" FROM ");
		builderSQL.append(" idItensPedido t");
		builderSQL.append(" WHERE ");
		builderSQL.append(builderWhere);
		builderSQL.append(" ORDER BY t.idItensPedido ASC ");

		return super.getListaPorQuery(builderSQL.toString(), parameters);
	}

	public List<TbItensPedido> findByProduto(Integer idProduto) {
		// TODO Auto-generated method stub
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("idProduto", idProduto);

		return super.getLista(TbItensPedido.QUERY_FINDBYPRODUTO, parameters);
	}

}