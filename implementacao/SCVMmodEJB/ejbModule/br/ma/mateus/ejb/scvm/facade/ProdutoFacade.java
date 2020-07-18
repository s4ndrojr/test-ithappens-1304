package br.ma.mateus.ejb.scvm.facade;

import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import br.ma.mateus.ejb.scvm.model.TbProduto;

@Local
public interface ProdutoFacade {
	public abstract List<TbProduto> findAll();

	/*
	 * public abstract void saveTbProduto(TbProduto tbProduto);
	 * 
	 * public abstract void updateTbProduto(TbProduto tbProduto);
	 * 
	 * public abstract void deleteTbProduto(TbProduto tbProduto);
	 * 
	 * public abstract List<TbProduto> findById(Integer idProduto);
	 */

	public abstract List<TbProduto> find(Integer id, Long codigoBarras, String descricao);
	
	public abstract List<TbProduto> find(Integer id, Long codigoBarras, String descricao, String registroExcluido);

	public abstract Integer save(Long codigoBarrasProduto, String descricaoProduto, String usuarioInsercao,
			Date dataInsercao, String usuarioAlteracao, Date dataAlteracao, String registroExcluido,
			String usuarioExclusao, Date dataExclusao) throws Exception;

	public abstract void update(Integer id, Long codigoBarrasProduto, String descricaoProduto, String usuarioInsercao, Date dataInsercao,
			String usuarioAlteracao, Date dataAlteracao, String registroExcluido, String usuarioExclusao, Date dataExclusao) throws Exception;

	public abstract boolean hasEstoqueOrItensPedido(Integer id);
}