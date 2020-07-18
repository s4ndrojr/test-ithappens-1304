package br.ma.mateus.ejb.scvm.imp;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import br.ma.mateus.ejb.scvm.facade.EstoqueFacade;
import br.ma.mateus.ejb.scvm.model.TbEstoque;
import br.ma.mateus.ejb.scvm.dao.TbEstoqueDAO;

@Stateless
@LocalBean
public class EstoqueFacadeImp implements EstoqueFacade {
	@EJB
	private TbEstoqueDAO tbEstoqueDAO;

	@Override
	public List<TbEstoque> findAll() {
		return tbEstoqueDAO.getLista();
	}

	@Override
	public void saveTbEstoque(TbEstoque tbEstoque) {
		tbEstoqueDAO.save(tbEstoque);
	}

	@Override
	public void updateTbEstoque(TbEstoque tbEstoque) {
		tbEstoqueDAO.update(tbEstoque);
	}

	@Override
	public void deleteTbEstoque(TbEstoque tbEstoque) {
		tbEstoqueDAO.delete(tbEstoque, TbEstoque.class);
	}

	@Override
	public List<TbEstoque> findById(Integer idEstoque) {
		return tbEstoqueDAO.findById(idEstoque);
	}

	@Override
	public List<TbEstoque> findByProduto(Integer idProduto) {
		// TODO Auto-generated method stub
		return this.tbEstoqueDAO.findByProduto(idProduto);
	}
}