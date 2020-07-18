package br.ma.mateus.ejb.scvm.imp;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import br.ma.mateus.ejb.scvm.facade.PedidoEstoqueFacade;
import br.ma.mateus.ejb.scvm.model.TbPedidoEstoque;
import br.ma.mateus.ejb.scvm.dao.TbPedidoEstoqueDAO;

@Stateless
@LocalBean
public class PedidoEstoqueFacadeImp  implements PedidoEstoqueFacade{
	   @EJB
	   private TbPedidoEstoqueDAO tbPedidoEstoqueDAO;
	   
	   @Override
	   public List<TbPedidoEstoque> findAll() {
	   	   return tbPedidoEstoqueDAO.getLista();
	   }
	   
	   @Override
	   public void saveTbPedidoEstoque(TbPedidoEstoque tbPedidoEstoque) {
	   	   tbPedidoEstoqueDAO.save(tbPedidoEstoque);
	   }
	   	  
	   @Override
	   public void updateTbPedidoEstoque(TbPedidoEstoque tbPedidoEstoque) {	   	   
	   		tbPedidoEstoqueDAO.update(tbPedidoEstoque);
	   }
	   
	   @Override
	   public void deleteTbPedidoEstoque(TbPedidoEstoque tbPedidoEstoque) {
	   	   tbPedidoEstoqueDAO.delete(tbPedidoEstoque, TbPedidoEstoque.class);
	   }
	   
	   @Override
	   public List<TbPedidoEstoque> findById(Integer idPedidoEstoque) {
	   	   return tbPedidoEstoqueDAO.findById(idPedidoEstoque);
	   }
} 