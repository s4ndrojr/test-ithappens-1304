package br.ma.mateus.ejb.scvm.imp;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import br.ma.mateus.ejb.scvm.facade.ClienteFacade;
import br.ma.mateus.ejb.scvm.model.TbCliente;
import br.ma.mateus.ejb.scvm.util.Constantes;
import br.ma.mateus.ejb.scvm.dao.TbClienteDAO;

@Stateless
@LocalBean
public class ClienteFacadeImp  implements ClienteFacade{
	   @EJB
	   private TbClienteDAO tbClienteDAO;
	   
	   @Override
	   public List<TbCliente> findAll() {
	   	   return tbClienteDAO.getLista();
	   }

	@Override
	public List<TbCliente> find(Integer idCliente, String cpfCnpjCliente, String nomeCliente) {
		// TODO Auto-generated method stub
		return this.find(idCliente, cpfCnpjCliente, nomeCliente, Constantes.REGISTRO_NAO_EXCLUIDO);
	}
	
	@Override
	public List<TbCliente> find(Integer idCliente, String cpfCnpjCliente, String nomeCliente, String registroExcluido) {
		// TODO Auto-generated method stub
		return tbClienteDAO.find(idCliente, cpfCnpjCliente, nomeCliente, registroExcluido);
	}
} 