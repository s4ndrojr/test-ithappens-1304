package br.ma.mateus.ejb.scvm.imp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import br.ma.mateus.ejb.scvm.dao.TbFormaPagamentoDAO;
import br.ma.mateus.ejb.scvm.facade.FormaPagamentoFacade;
import br.ma.mateus.ejb.scvm.model.TbFormaPagamento;
import br.ma.mateus.ejb.scvm.util.Constantes;

@Stateless
@LocalBean
public class FormaPagamentoFacadeImp implements FormaPagamentoFacade {
	@EJB
	private TbFormaPagamentoDAO tbFormaPagamentoDAO;

	@Override
	public List<TbFormaPagamento> find(Integer id, String descricaoFormaPagamento) {
		// TODO Auto-generated method stub
		return this.find(id, descricaoFormaPagamento, Constantes.REGISTRO_NAO_EXCLUIDO);
	}
	
	@Override
	public List<TbFormaPagamento> find(Integer id, String descricaoFormaPagamento, String registroExcluido) {
		// TODO Auto-generated method stub
		return tbFormaPagamentoDAO.find(id, descricaoFormaPagamento, registroExcluido);
	}

	@Override
	public Integer save(String descricaoFormaPagamento, String usuarioInsercao, Date dataInsercao,
			String usuarioAlteracao, Date dataAlteracao, String registroExcluido, String usuarioExclusao,
			Date dataExclusao) throws Exception {
		// TODO Auto-generated method stub
		TbFormaPagamento formaPagamento = null;
		
		try {
			formaPagamento = this.montarTbFormaPagamento(null, descricaoFormaPagamento, usuarioInsercao, dataInsercao, 
					usuarioAlteracao, dataAlteracao, registroExcluido, usuarioExclusao, dataExclusao);
		
			this.tbFormaPagamentoDAO.save(formaPagamento);
		} catch (Exception e) {
			formaPagamento = null;
			throw e;
			// TODO: handle exception
		}
		
		return formaPagamento == null ? null : formaPagamento.getIdFormaPagamento();
	}

	@Override
	public void update(Integer id, String descricaoFormaPagamento, String usuarioInsercao, Date dataInsercao,
			String usuarioAlteracao, Date dataAlteracao, String registroExcluido, String usuarioExclusao,
			Date dataExclusao) throws Exception {
		// TODO Auto-generated method stub
		TbFormaPagamento formaPagamento = null;
		
		try {
			formaPagamento = this.montarTbFormaPagamento(id, descricaoFormaPagamento, usuarioInsercao, dataInsercao, 
					usuarioAlteracao, dataAlteracao, registroExcluido, usuarioExclusao, dataExclusao);
			this.tbFormaPagamentoDAO.update(formaPagamento);
		} catch (Exception e) {
			formaPagamento = null;
			throw e;
			// TODO: handle exception
		}
	}
	
	private TbFormaPagamento montarTbFormaPagamento(Integer id, String descricaoFormaPagamento, String usuarioInsercao,
			Date dataInsercao, String usuarioAlteracao, Date dataAlteracao, String registroExcluido,
			String usuarioExclusao, Date dataExclusao) {
		
		TbFormaPagamento formaPagamento = new TbFormaPagamento();
		formaPagamento.setIdFormaPagamento(id);
		formaPagamento.setDescricaoFormaPagamento(descricaoFormaPagamento);
		formaPagamento.setUsuarioInsercao(usuarioInsercao);
		formaPagamento.setDataInsercao(dataInsercao);
		formaPagamento.setUsuarioAlteracao(usuarioAlteracao);
		formaPagamento.setDataAlteracao(dataAlteracao);
		formaPagamento.setRegistroExcluido(registroExcluido);
		formaPagamento.setUsuarioExclusao(usuarioExclusao);
		formaPagamento.setDataExclusao(dataExclusao);
		
		return formaPagamento;
		
	}

	@Override
	public boolean hasItensPedido(Integer id) {
		// TODO Auto-generated method stub
		boolean has = false;
		try {
			TbFormaPagamento tbFormaPagamento = this.findComPedidoEstoque(id);
			
			if(tbFormaPagamento != null && !tbFormaPagamento.getTbPedidoEstoques().isEmpty()) has = true;
		}catch (Exception e) {
			throw e;
		}
		return has;
	}

	private TbFormaPagamento findComPedidoEstoque(Integer idFormaPagamento) {
		// TODO Auto-generated method stub
		try {
			this.tbFormaPagamentoDAO.findComPedidoEstoque(idFormaPagamento);
		} catch (NoResultException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}