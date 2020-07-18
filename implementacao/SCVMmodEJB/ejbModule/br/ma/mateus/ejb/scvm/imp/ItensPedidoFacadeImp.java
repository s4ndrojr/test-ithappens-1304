package br.ma.mateus.ejb.scvm.imp;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import br.ma.mateus.ejb.scvm.facade.ItensPedidoFacade;
import br.ma.mateus.ejb.scvm.model.TbItensPedido;
import br.ma.mateus.ejb.scvm.dao.TbItensPedidoDAO;

@Stateless
@LocalBean
public class ItensPedidoFacadeImp implements ItensPedidoFacade {
	@EJB
	private TbItensPedidoDAO tbItensPedidoDAO;

	@Override
	public List<TbItensPedido> findAll() {
		return tbItensPedidoDAO.getLista();
	}

	@Override
	public void saveTbItensPedido(TbItensPedido tbItensPedido) {
		tbItensPedidoDAO.save(tbItensPedido);
	}

	@Override
	public void updateTbItensPedido(TbItensPedido tbItensPedido) {
		tbItensPedidoDAO.update(tbItensPedido);
	}

	@Override
	public void deleteTbItensPedido(TbItensPedido tbItensPedido) {
		tbItensPedidoDAO.delete(tbItensPedido, TbItensPedido.class);
	}

	@Override
	public List<TbItensPedido> findById(Integer idItensPedido) {
		return tbItensPedidoDAO.findById(idItensPedido);
	}

	@Override
	public List<TbItensPedido> findByProduto(Integer idProduto) {
		// TODO Auto-generated method stub
		return this.tbItensPedidoDAO.findByProduto(idProduto);
	}
}