package br.ma.mateus.ejb.scvm.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.ma.mateus.ejb.scvm.dao.GenericDAO;
import br.ma.mateus.ejb.scvm.model.TbFilial;
	     
@Stateless
@LocalBean
public class TbFilialDAO  extends GenericDAO<TbFilial>{
	   
	   public TbFilialDAO() {
	   	   super(TbFilial.class);
	   }
	   
	   public List<TbFilial> getLista() {
	   	   Map<String, Object> parameters = new HashMap<String, Object>();
	   
	       return super.getLista("TbFilial.findAll", parameters);
	   }
	   
	public List<TbFilial> find(Integer idFilial, String nomeFilial, String registroExcluido) {
		// TODO Auto-generated method stub
		Map<String, Object> parameters = new HashMap<String, Object>();
		StringBuilder builderSQL = new StringBuilder();
		StringBuilder builderWhere = new StringBuilder();

		builderWhere.append(" t.idFilial is not null");

		if (idFilial != null) {
			builderWhere.append(" and t.idFilial = :idFilial ");
			parameters.put("idFilial", idFilial.intValue());
		}

		if (nomeFilial != null && !nomeFilial.trim().equals("")) {
			builderWhere.append(" and t.nomeFilial like :nomeFilial ");
			parameters.put("nomeFilial", nomeFilial);
		}
		
		if (registroExcluido != null && !registroExcluido.trim().equals("")) {
			builderWhere.append(" and t.registroExcluido like :registroExcluido ");
			parameters.put("registroExcluido", registroExcluido);
		}

		builderSQL.append(" SELECT ");
		builderSQL.append(" t ");
		builderSQL.append(" FROM ");
		builderSQL.append(" TbFilial t");
		builderSQL.append(" WHERE ");
		builderSQL.append(builderWhere);
		builderSQL.append(" ORDER BY t.nomeFilial ASC ");
		
		return super.getListaPorQuery(builderSQL.toString(), parameters);
	}   
	 
}   