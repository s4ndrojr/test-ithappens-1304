package br.ma.mateus.ejb.scvm.facade;

import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import br.ma.mateus.ejb.scvm.model.TbFormaPagamento;

@Local
public interface FormaPagamentoFacade {
	public abstract List<TbFormaPagamento> find(Integer id, String descricaoFormaPagamento);
	public abstract List<TbFormaPagamento> find(Integer id, String descricaoFormaPagamento, String registroExcluido);
	public abstract Integer save(String descricaoFormaPagamento, String usuarioInsercao, Date dataInsercao,
			String usuarioAlteracao, Date dataAlteracao, String registroExcluido, String usuarioExclusao,
			Date dataExclusao) throws Exception;
	
	public abstract void update(Integer id, String descricaoFormaPagamento, String usuarioInsercao, Date dataInsercao,
			String usuarioAlteracao, Date dataAlteracao, String registroExcluido, String usuarioExclusao,
			Date dataExclusao) throws Exception;
	
	public abstract boolean hasItensPedido(Integer id);
	
}