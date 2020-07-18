package br.ma.mateus.ejb.scvm.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import br.ma.mateus.ejb.scvm.dao.GenericDAO;
import br.ma.mateus.ejb.scvm.model.TbTipoPedidoEstoque;
	     
@Stateless
@LocalBean
public class TbTipoPedidoEstoqueDAO  extends GenericDAO<TbTipoPedidoEstoque>{
	   
	   public TbTipoPedidoEstoqueDAO() {
	   	   super(TbTipoPedidoEstoque.class);
	   }
	   
	   public List<TbTipoPedidoEstoque> getLista() {
	   	   Map<String, Object> parameters = new HashMap<String, Object>();
	   
	       return super.getLista("TbTipoPedidoEstoque.findAll", parameters);
	   }
	   
	   public List<TbTipoPedidoEstoque> findById(Integer idTipoPedidoEstoque) {
	    	Map<String, Object> parameters = new HashMap<String, Object>();
	     	StringBuilder builderSQL = new StringBuilder();
	   		StringBuilder builderWhere =  new StringBuilder();
	   		
	   		/*
	   		builderWhere.append(" t.[idTable] is not null");
	   			
	   		if ([field] != null && ![field].trim().equals("")) {
	   			builderWhere.append(" and t.[field] like :[field] ");
	   			parameters.put("[field]", [field]);			
	   		}	
	   		*/
	   		
	   		builderWhere.append(" and t.idTipoPedidoEstoque like :idTipoPedidoEstoque ");
	   		parameters.put("idTipoPedidoEstoque", idTipoPedidoEstoque);	
	   		
	   		builderSQL.append(" SELECT " );
	   		builderSQL.append(" t ");
	   		builderSQL.append(" FROM ");
	   		builderSQL.append(" idTipoPedidoEstoque t");
	   		builderSQL.append(" WHERE ");
	   		builderSQL.append(builderWhere);
	   		builderSQL.append(" ORDER BY t.idTipoPedidoEstoque ASC ");
	   
	   		return super.getListaPorQuery(builderSQL.toString(), parameters);
	   }   
	 
}   