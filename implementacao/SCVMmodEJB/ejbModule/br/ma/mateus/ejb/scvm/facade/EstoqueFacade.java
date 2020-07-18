package br.ma.mateus.ejb.scvm.facade;

import java.util.List;
import javax.ejb.Local;
import br.ma.mateus.ejb.scvm.model.TbEstoque;

@Local
public interface EstoqueFacade {
	public abstract List<TbEstoque> findAll();

	public abstract void saveTbEstoque(TbEstoque tbEstoque);

	public abstract void updateTbEstoque(TbEstoque tbEstoque);

	public abstract void deleteTbEstoque(TbEstoque tbEstoque);

	public abstract List<TbEstoque> findById(Integer idEstoque);

	public abstract List<TbEstoque> findByProduto(Integer idProduto);
}