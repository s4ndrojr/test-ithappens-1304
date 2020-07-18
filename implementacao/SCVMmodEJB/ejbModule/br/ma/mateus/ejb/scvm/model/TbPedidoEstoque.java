package br.ma.mateus.ejb.scvm.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the tb_pedido_estoque database table.
 * 
 */
@Entity
@Table(name="tb_pedido_estoque")
@NamedQuery(name="TbPedidoEstoque.findAll", query="SELECT t FROM TbPedidoEstoque t")
public class TbPedidoEstoque implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_PEDIDO_ESTOQUE_IDPEDIDOESTOQUE_GENERATOR", sequenceName="TB_PEDIDO_ESTOQUE_ID_PEDIDO_ESTOQUE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_PEDIDO_ESTOQUE_IDPEDIDOESTOQUE_GENERATOR")
	@Column(name="id_pedido_estoque")
	private Integer idPedidoEstoque;

	@Column(name="data_alteracao")
	private Timestamp dataAlteracao;

	@Column(name="data_exclusao")
	private Timestamp dataExclusao;

	@Column(name="data_insercao")
	private Timestamp dataInsercao;

	@Column(name="observacao_pedido_estoque")
	private String observacaoPedidoEstoque;

	@Column(name="registro_excluido")
	private String registroExcluido;

	@Column(name="usuario_alteracao")
	private String usuarioAlteracao;

	@Column(name="usuario_exclusao")
	private String usuarioExclusao;

	@Column(name="usuario_insercao")
	private String usuarioInsercao;

	//bi-directional many-to-one association to TbItensPedido
	@OneToMany(mappedBy="tbPedidoEstoque")
	private List<TbItensPedido> tbItensPedidos;

	//bi-directional many-to-one association to TbCliente
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private TbCliente tbCliente;

	//bi-directional many-to-one association to TbFilial
	@ManyToOne
	@JoinColumn(name="id_filial")
	private TbFilial tbFilial;

	//bi-directional many-to-one association to TbTipoPedidoEstoque
	@ManyToOne
	@JoinColumn(name="id_tipo_pedido_estoque")
	private TbTipoPedidoEstoque tbTipoPedidoEstoque;

	//bi-directional many-to-one association to TbUsuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private TbUsuario tbUsuario;
	
	//bi-directional many-to-one association to TbFormaPagamento
	@ManyToOne
	@JoinColumn(name="id_forma_pagamento")
	private TbFormaPagamento tbFormaPagamento;

	public TbPedidoEstoque() {
	}

	public Integer getIdPedidoEstoque() {
		return this.idPedidoEstoque;
	}

	public void setIdPedidoEstoque(Integer idPedidoEstoque) {
		this.idPedidoEstoque = idPedidoEstoque;
	}

	public Timestamp getDataAlteracao() {
		return this.dataAlteracao;
	}

	public void setDataAlteracao(Timestamp dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public Timestamp getDataExclusao() {
		return this.dataExclusao;
	}

	public void setDataExclusao(Timestamp dataExclusao) {
		this.dataExclusao = dataExclusao;
	}

	public Timestamp getDataInsercao() {
		return this.dataInsercao;
	}

	public void setDataInsercao(Timestamp dataInsercao) {
		this.dataInsercao = dataInsercao;
	}

	public String getObservacaoPedidoEstoque() {
		return this.observacaoPedidoEstoque;
	}

	public void setObservacaoPedidoEstoque(String observacaoPedidoEstoque) {
		this.observacaoPedidoEstoque = observacaoPedidoEstoque;
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

	public List<TbItensPedido> getTbItensPedidos() {
		return this.tbItensPedidos;
	}

	public void setTbItensPedidos(List<TbItensPedido> tbItensPedidos) {
		this.tbItensPedidos = tbItensPedidos;
	}

	public TbItensPedido addTbItensPedido(TbItensPedido tbItensPedido) {
		getTbItensPedidos().add(tbItensPedido);
		tbItensPedido.setTbPedidoEstoque(this);

		return tbItensPedido;
	}

	public TbItensPedido removeTbItensPedido(TbItensPedido tbItensPedido) {
		getTbItensPedidos().remove(tbItensPedido);
		tbItensPedido.setTbPedidoEstoque(null);

		return tbItensPedido;
	}

	public TbCliente getTbCliente() {
		return this.tbCliente;
	}

	public void setTbCliente(TbCliente tbCliente) {
		this.tbCliente = tbCliente;
	}

	public TbFilial getTbFilial() {
		return this.tbFilial;
	}

	public void setTbFilial(TbFilial tbFilial) {
		this.tbFilial = tbFilial;
	}

	public TbTipoPedidoEstoque getTbTipoPedidoEstoque() {
		return this.tbTipoPedidoEstoque;
	}

	public void setTbTipoPedidoEstoque(TbTipoPedidoEstoque tbTipoPedidoEstoque) {
		this.tbTipoPedidoEstoque = tbTipoPedidoEstoque;
	}

	public TbUsuario getTbUsuario() {
		return this.tbUsuario;
	}

	public void setTbUsuario(TbUsuario tbUsuario) {
		this.tbUsuario = tbUsuario;
	}

	public TbFormaPagamento getTbFormaPagamento() {
		return tbFormaPagamento;
	}

	public void setTbFormaPagamento(TbFormaPagamento tbFormaPagamento) {
		this.tbFormaPagamento = tbFormaPagamento;
	}
	
}