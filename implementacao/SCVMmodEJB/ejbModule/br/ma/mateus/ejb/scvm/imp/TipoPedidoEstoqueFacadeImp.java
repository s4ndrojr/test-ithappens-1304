package br.ma.mateus.ejb.scvm.imp;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import br.ma.mateus.ejb.scvm.facade.TipoPedidoEstoqueFacade;
import br.ma.mateus.ejb.scvm.model.TbTipoPedidoEstoque;
import br.ma.mateus.ejb.scvm.dao.TbTipoPedidoEstoqueDAO;

@Stateless
@LocalBean
public class TipoPedidoEstoqueFacadeImp  implements TipoPedidoEstoqueFacade{
	   @EJB
	   private TbTipoPedidoEstoqueDAO tbTipoPedidoEstoqueDAO;
	   
	   @Override
	   public List<TbTipoPedidoEstoque> findAll() {
	   	   return tbTipoPedidoEstoqueDAO.getLista();
	   }
	   
	   @Override
	   public void saveTbTipoPedidoEstoque(TbTipoPedidoEstoque tbTipoPedidoEstoque) {
	   	   tbTipoPedidoEstoqueDAO.save(tbTipoPedidoEstoque);
	   }
	   	  
	   @Override
	   public void updateTbTipoPedidoEstoque(TbTipoPedidoEstoque tbTipoPedidoEstoque) {	   	   
	   		tbTipoPedidoEstoqueDAO.update(tbTipoPedidoEstoque);
	   }
	   
	   @Override
	   public void deleteTbTipoPedidoEstoque(TbTipoPedidoEstoque tbTipoPedidoEstoque) {
	   	   tbTipoPedidoEstoqueDAO.delete(tbTipoPedidoEstoque, TbTipoPedidoEstoque.class);
	   }
	   
	   @Override
	   public List<TbTipoPedidoEstoque> findById(Integer idTipoPedidoEstoque) {
	   	   return tbTipoPedidoEstoqueDAO.findById(idTipoPedidoEstoque);
	   }
} 