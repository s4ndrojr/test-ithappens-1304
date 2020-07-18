package br.ma.mateus.ejb.scvm.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import br.ma.mateus.ejb.scvm.dao.GenericDAO;
import br.ma.mateus.ejb.scvm.model.TbProduto;

@Stateless
@LocalBean
public class TbProdutoDAO extends GenericDAO<TbProduto> {

	public TbProdutoDAO() {
		super(TbProduto.class);
	}

	public List<TbProduto> getLista() {
		Map<String, Object> parameters = new HashMap<String, Object>();

		return super.getLista("TbProduto.findAll", parameters);
	}

	public List<TbProduto> find(Integer id, Long codigoBarras, String descricao, String registroExcluido) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		StringBuilder builderSQL = new StringBuilder();
		StringBuilder builderWhere = new StringBuilder();

		builderWhere.append(" t.idProduto is not null");

		if (id != null) {
			builderWhere.append(" and t.idProduto = :id ");
			parameters.put("id", id.intValue());
		}

		if (codigoBarras != null) {
			builderWhere.append(" and t.codigoBarrasProduto = :codigoBarras ");
			parameters.put("codigoBarras", codigoBarras.longValue());
		}

		if (descricao != null && !descricao.trim().equals("")) {
			builderWhere.append(" and t.descricaoProduto like :descricao ");
			parameters.put("descricao", descricao);
		}
		
		if (registroExcluido != null && !registroExcluido.trim().equals("")) {
			builderWhere.append(" and t.registroExcluido like :registroExcluido ");
			parameters.put("registroExcluido", registroExcluido);
		}

		builderSQL.append(" SELECT ");
		builderSQL.append(" t ");
		builderSQL.append(" FROM ");
		builderSQL.append(" TbProduto t");
		builderSQL.append(" WHERE ");
		builderSQL.append(builderWhere);
		builderSQL.append(" ORDER BY t.descricaoProduto ASC ");

		return super.getListaPorQuery(builderSQL.toString(), parameters);
	}

	public TbProduto findWithEstoque(Integer idProduto) {
		// TODO Auto-generated method stub
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		parameters.put("idProduto", idProduto);	
		
		return super.findOneResult(TbProduto.QUERY_FINDWITHESTOQUE, parameters);
	}
	
	public TbProduto findWithItensPedido(Integer idProduto) {
		// TODO Auto-generated method stub
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		parameters.put("idProduto", idProduto);	
		
		return super.findOneResult(TbProduto.QUERY_FINDWITHITENSPEDIDO, parameters);
	}

}