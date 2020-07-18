package br.ma.mateus.ejb.scvm.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import br.ma.mateus.ejb.scvm.dao.GenericDAO;
import br.ma.mateus.ejb.scvm.model.TbPedidoEstoque;
	     
@Stateless
@LocalBean
public class TbPedidoEstoqueDAO  extends GenericDAO<TbPedidoEstoque>{
	   
	   public TbPedidoEstoqueDAO() {
	   	   super(TbPedidoEstoque.class);
	   }
	   
	   public List<TbPedidoEstoque> getLista() {
	   	   Map<String, Object> parameters = new HashMap<String, Object>();
	   
	       return super.getLista("TbPedidoEstoque.findAll", parameters);
	   }
	   
	   public List<TbPedidoEstoque> findById(Integer idPedidoEstoque) {
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
	   		
	   		builderWhere.append(" and t.idPedidoEstoque like :idPedidoEstoque ");
	   		parameters.put("idPedidoEstoque", idPedidoEstoque);	
	   		
	   		builderSQL.append(" SELECT " );
	   		builderSQL.append(" t ");
	   		builderSQL.append(" FROM ");
	   		builderSQL.append(" idPedidoEstoque t");
	   		builderSQL.append(" WHERE ");
	   		builderSQL.append(builderWhere);
	   		builderSQL.append(" ORDER BY t.idPedidoEstoque ASC ");
	   
	   		return super.getListaPorQuery(builderSQL.toString(), parameters);
	   }   
	 
}   