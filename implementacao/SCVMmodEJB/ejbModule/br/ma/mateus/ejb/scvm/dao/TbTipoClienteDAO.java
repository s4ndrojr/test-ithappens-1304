package br.ma.mateus.ejb.scvm.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import br.ma.mateus.ejb.scvm.dao.GenericDAO;
import br.ma.mateus.ejb.scvm.model.TbTipoCliente;
	     
@Stateless
@LocalBean
public class TbTipoClienteDAO  extends GenericDAO<TbTipoCliente>{
	   
	   public TbTipoClienteDAO() {
	   	   super(TbTipoCliente.class);
	   }
	   
	   public List<TbTipoCliente> getLista() {
	   	   Map<String, Object> parameters = new HashMap<String, Object>();
	   
	       return super.getLista("TbTipoCliente.findAll", parameters);
	   }
	   
	   public List<TbTipoCliente> findById(Integer idTipoCliente) {
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
	   		
	   		builderWhere.append(" and t.idTipoCliente like :idTipoCliente ");
	   		parameters.put("idTipoCliente", idTipoCliente);	
	   		
	   		builderSQL.append(" SELECT " );
	   		builderSQL.append(" t ");
	   		builderSQL.append(" FROM ");
	   		builderSQL.append(" idTipoCliente t");
	   		builderSQL.append(" WHERE ");
	   		builderSQL.append(builderWhere);
	   		builderSQL.append(" ORDER BY t.idTipoCliente ASC ");
	   
	   		return super.getListaPorQuery(builderSQL.toString(), parameters);
	   }   
	 
}   