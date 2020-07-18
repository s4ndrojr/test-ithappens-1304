package br.ma.mateus.ejb.scvm.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the tb_itens_pedido database table.
 * 
 */
@Entity
@Table(name="tb_itens_pedido")
@NamedQueries({
	@NamedQuery(name="TbItensPedido.findAll", query="SELECT t FROM TbItensPedido t"),
	@NamedQuery(name="TbItensPedido.findByProduto", query="SELECT t FROM TbItensPedido t where t.tbProduto.idProduto = :idProduto")
})
public class TbItensPedido implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String QUERY_FINDBYPRODUTO = "TbItensPedido.findByProduto";

	@Id
	@SequenceGenerator(name="TB_ITENS_PEDIDO_IDITENSPEDIDO_GENERATOR", sequenceName="TB_ITENS_PEDIDO_ID_ITENS_PEDIDO_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_ITENS_PEDIDO_IDITENSPEDIDO_GENERATOR")
	@Column(name="id_itens_pedido")
	private Integer idItensPedido;

	@Column(name="data_alteracao")
	private Timestamp dataAlteracao;

	@Column(name="data_exclusao")
	private Timestamp dataExclusao;

	@Column(name="data_insercao")
	private Timestamp dataInsercao;

	@Column(name="quantidade_itens_pedido")
	private Integer quantidadeItensPedido;

	@Column(name="registro_excluido")
	private String registroExcluido;

	@Column(name="usuario_alteracao")
	private String usuarioAlteracao;

	@Column(name="usuario_exclusao")
	private String usuarioExclusao;

	@Column(name="usuario_insercao")
	private String usuarioInsercao;

	@Column(name="valor_total_itens_pedido")
	private double valorTotalItensPedido;

	//bi-directional many-to-one association to TbPedidoEstoque
	@ManyToOne
	@JoinColumn(name="id_pedido_estoque")
	private TbPedidoEstoque tbPedidoEstoque;

	//bi-directional many-to-one association to TbProduto
	@ManyToOne
	@JoinColumn(name="id_produto")
	private TbProduto tbProduto;

	//bi-directional many-to-one association to TbStatus
	@ManyToOne
	@JoinColumn(name="id_status")
	private TbStatus tbStatus;

	public TbItensPedido() {
	}

	public Integer getIdItensPedido() {
		return this.idItensPedido;
	}

	public void setIdItensPedido(Integer idItensPedido) {
		this.idItensPedido = idItensPedido;
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

	public Integer getQuantidadeItensPedido() {
		return this.quantidadeItensPedido;
	}

	public void setQuantidadeItensPedido(Integer quantidadeItensPedido) {
		this.quantidadeItensPedido = quantidadeItensPedido;
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

	public double getValorTotalItensPedido() {
		return this.valorTotalItensPedido;
	}

	public void setValorTotalItensPedido(double valorTotalItensPedido) {
		this.valorTotalItensPedido = valorTotalItensPedido;
	}

	public TbPedidoEstoque getTbPedidoEstoque() {
		return this.tbPedidoEstoque;
	}

	public void setTbPedidoEstoque(TbPedidoEstoque tbPedidoEstoque) {
		this.tbPedidoEstoque = tbPedidoEstoque;
	}

	public TbProduto getTbProduto() {
		return this.tbProduto;
	}

	public void setTbProduto(TbProduto tbProduto) {
		this.tbProduto = tbProduto;
	}

	public TbStatus getTbStatus() {
		return this.tbStatus;
	}

	public void setTbStatus(TbStatus tbStatus) {
		this.tbStatus = tbStatus;
	}

}