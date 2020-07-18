package br.ma.mateus.ejb.scvm.imp;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.ma.mateus.ejb.scvm.dao.TbProdutoDAO;
import br.ma.mateus.ejb.scvm.facade.EstoqueFacade;
import br.ma.mateus.ejb.scvm.facade.ItensPedidoFacade;
import br.ma.mateus.ejb.scvm.facade.ProdutoFacade;
import br.ma.mateus.ejb.scvm.model.TbEstoque;
import br.ma.mateus.ejb.scvm.model.TbItensPedido;
import br.ma.mateus.ejb.scvm.model.TbProduto;
import br.ma.mateus.ejb.scvm.util.Constantes;

@Stateless
@LocalBean
public class ProdutoFacadeImp implements ProdutoFacade {
	@EJB
	private TbProdutoDAO tbProdutoDAO;
	
	@EJB
	private EstoqueFacade estoqueFacade;
	
	@EJB
	private ItensPedidoFacade itensPedidoFacade;

	@Override
	public List<TbProduto> findAll() {
		return tbProdutoDAO.getLista();
	}

	@Override
	public List<TbProduto> find(Integer id, Long codigoBarras, String descricao) {
		// TODO Auto-generated method stub
		return this.find(id, codigoBarras, descricao, Constantes.REGISTRO_NAO_EXCLUIDO);
	}
	
	@Override
	public List<TbProduto> find(Integer id, Long codigoBarras, String descricao, String registroExcluido) {
		// TODO Auto-generated method stub
		return tbProdutoDAO.find(id, codigoBarras, descricao, registroExcluido);
	}
	
	@Override
	public Integer save(Long codigoBarrasProduto, String descricaoProduto, String usuarioInsercao, Date dataInsercao,
			String usuarioAlteracao, Date dataAlteracao, String registroExcluido, String usuarioExclusao,
			Date dataExclusao) throws Exception {
		// TODO Auto-generated method stub
		TbProduto produto;
		try {
			produto = this.montarProduto(null, codigoBarrasProduto, descricaoProduto, usuarioInsercao, 
					dataInsercao, usuarioAlteracao, dataAlteracao, registroExcluido, usuarioExclusao, dataExclusao);

			this.tbProdutoDAO.save(produto);
		} catch (Exception e) {
			produto = null;
			throw e;
			// TODO: handle exception
		}

		return produto == null ? null : produto.getIdProduto();
	}

	@Override
	public void update(Integer id, Long codigoBarrasProduto, String descricaoProduto, String usuarioInsercao,
			Date dataInsercao, String usuarioAlteracao, Date dataAlteracao, String registroExcluido,
			String usuarioExclusao, Date dataExclusao) throws Exception {
		// TODO Auto-generated method stub
		TbProduto produto;
		try {
			produto = this.montarProduto(id, codigoBarrasProduto, descricaoProduto, usuarioInsercao, 
						dataInsercao, usuarioAlteracao, dataAlteracao, registroExcluido, usuarioExclusao, dataExclusao);
			
			this.tbProdutoDAO.update(produto);
		} catch (Exception e) {
			produto = null;
			throw e;
			// TODO: handle exception
		}
	}

	@Override
	public boolean hasEstoqueOrItensPedido(Integer idProduto) {
		// TODO Auto-generated method stub
		List<TbEstoque> listEstoque = estoqueFacade.findByProduto(idProduto);
		List<TbItensPedido> listItensPedido = itensPedidoFacade.findByProduto(idProduto);
		
		boolean has = false;
		
		if(!listEstoque.isEmpty() || !listItensPedido.isEmpty()) {
			has = true;
		}
		
		return has;
	}
	
	private TbProduto montarProduto(Integer id, Long codigoBarrasProduto, String descricaoProduto, String usuarioInsercao,
			Date dataInsercao, String usuarioAlteracao, Date dataAlteracao, String registroExcluido,
			String usuarioExclusao, Date dataExclusao) {
		
		TbProduto produto = new TbProduto();
		produto.setIdProduto(id);
		produto.setCodigoBarrasProduto(codigoBarrasProduto);
		produto.setDescricaoProduto(descricaoProduto);
		produto.setUsuarioInsercao(usuarioInsercao);
		produto.setDataInsercao(dataInsercao);
		produto.setUsuarioAlteracao(usuarioAlteracao);
		produto.setDataAlteracao(dataAlteracao);
		produto.setRegistroExcluido(registroExcluido);
		produto.setUsuarioExclusao(usuarioExclusao);
		produto.setDataExclusao(dataExclusao);
		
		return produto;
		
	}
}