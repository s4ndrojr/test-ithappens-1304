package br.ma.mateus.ejb.scvm.facade;

import java.util.List;
import javax.ejb.Local;
import br.ma.mateus.ejb.scvm.model.TbItensPedido;

@Local
public interface ItensPedidoFacade {
	public abstract List<TbItensPedido> findAll();

	public abstract void saveTbItensPedido(TbItensPedido tbItensPedido);

	public abstract void updateTbItensPedido(TbItensPedido tbItensPedido);

	public abstract void deleteTbItensPedido(TbItensPedido tbItensPedido);

	public abstract List<TbItensPedido> findById(Integer idItensPedido);

	public abstract List<TbItensPedido> findByProduto(Integer idProduto);
}