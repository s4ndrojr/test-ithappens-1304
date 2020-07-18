package br.ma.mateus.ejb.scvm.imp;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import br.ma.mateus.ejb.scvm.facade.StatusFacade;
import br.ma.mateus.ejb.scvm.model.TbStatus;
import br.ma.mateus.ejb.scvm.dao.TbStatusDAO;

@Stateless
@LocalBean
public class StatusFacadeImp  implements StatusFacade{
	   @EJB
	   private TbStatusDAO tbStatusDAO;
	   
	   @Override
	   public List<TbStatus> findAll() {
	   	   return tbStatusDAO.getLista();
	   }
	   
	   @Override
	   public void saveTbStatus(TbStatus tbStatus) {
	   	   tbStatusDAO.save(tbStatus);
	   }
	   	  
	   @Override
	   public void updateTbStatus(TbStatus tbStatus) {	   	   
	   		tbStatusDAO.update(tbStatus);
	   }
	   
	   @Override
	   public void deleteTbStatus(TbStatus tbStatus) {
	   	   tbStatusDAO.delete(tbStatus, TbStatus.class);
	   }
	   
	   @Override
	   public List<TbStatus> findById(Integer idStatus) {
	   	   return tbStatusDAO.findById(idStatus);
	   }
} 