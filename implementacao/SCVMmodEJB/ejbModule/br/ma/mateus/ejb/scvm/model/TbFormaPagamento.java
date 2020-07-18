package br.ma.mateus.ejb.scvm.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the tb_forma_pagamento database table.
 * 
 */
@Entity
@Table(name="tb_forma_pagamento")
@NamedQueries({
	@NamedQuery(name="TbFormaPagamento.findAll", query="SELECT t FROM TbFormaPagamento t"),
	@NamedQuery(name="TbFormaPagamento.findWithPedidoEstoque", query="SELECT t FROM TbFormaPagamento t join fetch t.tbPedidoEstoques where t.idFormaPagamento = :idFormaPagamento")
})
public class TbFormaPagamento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String QUERY_FINDWITHPEDIDOESTOQUE= "TbFormaPagamento.findWithPedidoEstoque";
	
	@Id
	@SequenceGenerator(name="TB_FORMA_PAGAMENTO_IDFORMAPAGAMENTO_GENERATOR", sequenceName="TB_FORMA_PAGAMENTO_ID_FORMA_PAGAMENTO_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_FORMA_PAGAMENTO_IDFORMAPAGAMENTO_GENERATOR")
	@Column(name="id_forma_pagamento")
	private Integer idFormaPagamento;

	@Column(name="data_alteracao")
	private Date dataAlteracao;

	@Column(name="data_exclusao")
	private Date dataExclusao;

	@Column(name="data_insercao")
	private Date dataInsercao;

	@Column(name="descricao_forma_pagamento")
	private String descricaoFormaPagamento;

	@Column(name="registro_excluido")
	private String registroExcluido;

	@Column(name="usuario_alteracao")
	private String usuarioAlteracao;

	@Column(name="usuario_exclusao")
	private String usuarioExclusao;

	@Column(name="usuario_insercao")
	private String usuarioInsercao;

	//bi-directional many-to-one association to TbItensPedido
	@OneToMany(mappedBy="tbFormaPagamento")
	private List<TbPedidoEstoque> tbPedidoEstoques;

	public TbFormaPagamento() {
	}

	public Integer getIdFormaPagamento() {
		return this.idFormaPagamento;
	}

	public void setIdFormaPagamento(Integer idFormaPagamento) {
		this.idFormaPagamento = idFormaPagamento;
	}

	public Date getDataAlteracao() {
		return this.dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public Date getDataExclusao() {
		return this.dataExclusao;
	}

	public void setDataExclusao(Date dataExclusao) {
		this.dataExclusao = dataExclusao;
	}

	public Date getDataInsercao() {
		return this.dataInsercao;
	}

	public void setDataInsercao(Date dataInsercao) {
		this.dataInsercao = dataInsercao;
	}

	public String getDescricaoFormaPagamento() {
		return this.descricaoFormaPagamento;
	}

	public void setDescricaoFormaPagamento(String descricaoFormaPagamento) {
		this.descricaoFormaPagamento = descricaoFormaPagamento;
	}

	public String getRegistroExcluido() {
		return this.registroExcluido;
	}

	public void setRegistroExcluido(String registroExcluido) {
		this.registroExcluido = registroExcluido;
	}

	public String getUsuarioAlteracao() {
		return this.usuarioAlteracao;
	}

	public void setUsuarioAlteracao(String usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public String getUsuarioExclusao() {
		return this.usuarioExclusao;
	}

	public void setUsuarioExclusao(String usuarioExclusao) {
		this.usuarioExclusao = usuarioExclusao;
	}

	public String getUsuarioInsercao() {
		return this.usuarioInsercao;
	}

	public void setUsuarioInsercao(String usuarioInsercao) {
		this.usuarioInsercao = usuarioInsercao;
	}
	
	public List<TbPedidoEstoque> getTbPedidoEstoques() {
		return this.tbPedidoEstoques;
	}

	public void setTbPedidoEstoques(List<TbPedidoEstoque> tbPedidoEstoques) {
		this.tbPedidoEstoques = tbPedidoEstoques;
	}

	public TbPedidoEstoque addTbPedidoEstoque(TbPedidoEstoque tbPedidoEstoque) {
		getTbPedidoEstoques().add(tbPedidoEstoque);
		tbPedidoEstoque.setTbFormaPagamento(this);

		return tbPedidoEstoque;
	}

	public TbPedidoEstoque removeTbPedidoEstoque(TbPedidoEstoque tbPedidoEstoque) {
		getTbPedidoEstoques().remove(tbPedidoEstoque);
		tbPedidoEstoque.setTbFormaPagamento(null);

		return tbPedidoEstoque;
	}

}