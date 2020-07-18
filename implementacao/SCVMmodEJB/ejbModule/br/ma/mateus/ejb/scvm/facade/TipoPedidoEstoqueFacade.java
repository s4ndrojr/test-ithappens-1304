package br.ma.mateus.ejb.scvm.facade;

import java.util.List;
import javax.ejb.Local;
import br.ma.mateus.ejb.scvm.model.TbTipoPedidoEstoque;

@Local
public interface TipoPedidoEstoqueFacade {
	   public abstract List<TbTipoPedidoEstoque> findAll();
	   public abstract void saveTbTipoPedidoEstoque(TbTipoPedidoEstoque tbTipoPedidoEstoque);
	   public abstract void updateTbTipoPedidoEstoque(TbTipoPedidoEstoque tbTipoPedidoEstoque);
	   public abstract void deleteTbTipoPedidoEstoque(TbTipoPedidoEstoque tbTipoPedidoEstoque);
	   public abstract List<TbTipoPedidoEstoque> findById(Integer idTipoPedidoEstoque);
}	   