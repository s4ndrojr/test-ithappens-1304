package br.ma.mateus.ejb.scvm.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import br.ma.mateus.ejb.scvm.dao.GenericDAO;
import br.ma.mateus.ejb.scvm.model.TbStatus;
	     
@Stateless
@LocalBean
public class TbStatusDAO  extends GenericDAO<TbStatus>{
	   
	   public TbStatusDAO() {
	   	   super(TbStatus.class);
	   }
	   
	   public List<TbStatus> getLista() {
	   	   Map<String, Object> parameters = new HashMap<String, Object>();
	   
	       return super.getLista("TbStatus.findAll", parameters);
	   }
	   
	   public List<TbStatus> findById(Integer idStatus) {
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
	   		
	   		builderWhere.append(" and t.idStatus like :idStatus ");
	   		parameters.put("idStatus", idStatus);	
	   		
	   		builderSQL.append(" SELECT " );
	   		builderSQL.append(" t ");
	   		builderSQL.append(" FROM ");
	   		builderSQL.append(" idStatus t");
	   		builderSQL.append(" WHERE ");
	   		builderSQL.append(builderWhere);
	   		builderSQL.append(" ORDER BY t.idStatus ASC ");
	   
	   		return super.getListaPorQuery(builderSQL.toString(), parameters);
	   }   
	 
}   