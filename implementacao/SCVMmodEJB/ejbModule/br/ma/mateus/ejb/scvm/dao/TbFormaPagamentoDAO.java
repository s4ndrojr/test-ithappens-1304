package br.ma.mateus.ejb.scvm.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import br.ma.mateus.ejb.scvm.dao.GenericDAO;
import br.ma.mateus.ejb.scvm.model.TbFormaPagamento;

@Stateless
@LocalBean
public class TbFormaPagamentoDAO extends GenericDAO<TbFormaPagamento> {

	public TbFormaPagamentoDAO() {
		super(TbFormaPagamento.class);
	}

	public List<TbFormaPagamento> getLista() {
		Map<String, Object> parameters = new HashMap<String, Object>();

		return super.getLista("TbFormaPagamento.findAll", parameters);
	}

	public List<TbFormaPagamento> find(Integer id, String descricaoFormaPagamento, String registroExcluido) {
		// TODO Auto-generated method stub
		Map<String, Object> parameters = new HashMap<String, Object>();
		StringBuilder builderSQL = new StringBuilder();
		StringBuilder builderWhere = new StringBuilder();

		builderWhere.append(" t.idFormaPagamento is not null");

		if (id != null) {
			builderWhere.append(" and t.idFormaPagamento = :id ");
			parameters.put("id", id.longValue());
		}

		if (descricaoFormaPagamento != null && !descricaoFormaPagamento.trim().equals("")) {
			builderWhere.append(" and t.descricaoFormaPagamento = :descricaoFormaPagamento ");
			parameters.put("descricaoFormaPagamento", descricaoFormaPagamento);
		}

		if (registroExcluido != null && !registroExcluido.trim().equals("")) {
			builderWhere.append(" and t.registroExcluido like :registroExcluido ");
			parameters.put("registroExcluido", registroExcluido);
		}

		builderSQL.append(" SELECT ");
		builderSQL.append(" t ");
		builderSQL.append(" FROM ");
		builderSQL.append(" TbFormaPagamento t");
		builderSQL.append(" WHERE ");
		builderSQL.append(builderWhere);
		builderSQL.append(" ORDER BY t.descricaoFormaPagamento ASC ");

		return super.getListaPorQuery(builderSQL.toString(), parameters);
	}
	
	public TbFormaPagamento findComPedidoEstoque(Integer idFormaPagamento) throws NoResultException, Exception {
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("idFormaPagamento", idFormaPagamento);
		
		return super.findOneResult(TbFormaPagamento.QUERY_FINDWITHPEDIDOESTOQUE, parameters);
	}

}