package br.ma.mateus.ejb.scvm.imp;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import br.ma.mateus.ejb.scvm.facade.TipoClienteFacade;
import br.ma.mateus.ejb.scvm.model.TbTipoCliente;
import br.ma.mateus.ejb.scvm.dao.TbTipoClienteDAO;

@Stateless
@LocalBean
public class TipoClienteFacadeImp  implements TipoClienteFacade{
	   @EJB
	   private TbTipoClienteDAO tbTipoClienteDAO;
	   
	   @Override
	   public List<TbTipoCliente> findAll() {
	   	   return tbTipoClienteDAO.getLista();
	   }
	   
	   @Override
	   public void saveTbTipoCliente(TbTipoCliente tbTipoCliente) {
	   	   tbTipoClienteDAO.save(tbTipoCliente);
	   }
	   	  
	   @Override
	   public void updateTbTipoCliente(TbTipoCliente tbTipoCliente) {	   	   
	   		tbTipoClienteDAO.update(tbTipoCliente);
	   }
	   
	   @Override
	   public void deleteTbTipoCliente(TbTipoCliente tbTipoCliente) {
	   	   tbTipoClienteDAO.delete(tbTipoCliente, TbTipoCliente.class);
	   }
	   
	   @Override
	   public List<TbTipoCliente> findById(Integer idTipoCliente) {
	   	   return tbTipoClienteDAO.findById(idTipoCliente);
	   }
} 