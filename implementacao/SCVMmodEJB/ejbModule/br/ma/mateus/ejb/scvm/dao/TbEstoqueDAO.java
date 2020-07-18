package br.ma.mateus.ejb.scvm.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import br.ma.mateus.ejb.scvm.dao.GenericDAO;
import br.ma.mateus.ejb.scvm.model.TbEstoque;

@Stateless
@LocalBean
public class TbEstoqueDAO extends GenericDAO<TbEstoque> {

	public TbEstoqueDAO() {
		super(TbEstoque.class);
	}

	public List<TbEstoque> getLista() {
		Map<String, Object> parameters = new HashMap<String, Object>();

		return super.getLista("TbEstoque.findAll", parameters);
	}

	public List<TbEstoque> findById(Integer idEstoque) {
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

		builderWhere.append(" and t.idEstoque like :idEstoque ");
		parameters.put("idEstoque", idEstoque);

		builderSQL.append(" SELECT ");
		builderSQL.append(" t ");
		builderSQL.append(" FROM ");
		builderSQL.append(" idEstoque t");
		builderSQL.append(" WHERE ");
		builderSQL.append(builderWhere);
		builderSQL.append(" ORDER BY t.idEstoque ASC ");

		return super.getListaPorQuery(builderSQL.toString(), parameters);
	}

	public List<TbEstoque> findByProduto(Integer idProduto) {
		// TODO Auto-generated method stub
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		parameters.put("idProduto", idProduto);
		
		return super.getLista(TbEstoque.QUERY_FINDBYPRODUTO, parameters);
	}

}