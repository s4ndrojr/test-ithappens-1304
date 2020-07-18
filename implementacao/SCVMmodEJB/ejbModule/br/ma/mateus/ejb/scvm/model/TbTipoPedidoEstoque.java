package br.ma.mateus.ejb.scvm.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the tb_tipo_pedido_estoque database table.
 * 
 */
@Entity
@Table(name="tb_tipo_pedido_estoque")
@NamedQuery(name="TbTipoPedidoEstoque.findAll", query="SELECT t FROM TbTipoPedidoEstoque t")
public class TbTipoPedidoEstoque implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_TIPO_PEDIDO_ESTOQUE_IDTIPOPEDIDOESTOQUE_GENERATOR", sequenceName="TB_TIPO_PEDIDO_ESTOQUE_ID_TIPO_PEDIDO_ESTOQUE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_TIPO_PEDIDO_ESTOQUE_IDTIPOPEDIDOESTOQUE_GENERATOR")
	@Column(name="id_tipo_pedido_estoque")
	private Integer idTipoPedidoEstoque;

	@Column(name="codigo_tipo_pedido_estoque")
	private Integer codigoTipoPedidoEstoque;

	@Column(name="data_alteracao")
	private Timestamp dataAlteracao;

	@Column(name="data_exclusao")
	private Timestamp dataExclusao;

	@Column(name="data_insercao")
	private Timestamp dataInsercao;

	@Column(name="descricao_tipo_pedido_estoque")
	private String descricaoTipoPedidoEstoque;

	@Column(name="registro_excluido")
	private String registroExcluido;

	@Column(name="usuario_alteracao")
	private String usuarioAlteracao;

	@Column(name="usuario_exclusao")
	private String usuarioExclusao;

	@Column(name="usuario_insercao")
	private String usuarioInsercao;

	//bi-directional many-to-one association to TbPedidoEstoque
	@OneToMany(mappedBy="tbTipoPedidoEstoque")
	private List<TbPedidoEstoque> tbPedidoEstoques;

	public TbTipoPedidoEstoque() {
	}

	public Integer getIdTipoPedidoEstoque() {
		return this.idTipoPedidoEstoque;
	}

	public void setIdTipoPedidoEstoque(Integer idTipoPedidoEstoque) {
		this.idTipoPedidoEstoque = idTipoPedidoEstoque;
	}

	public Integer getCodigoTipoPedidoEstoque() {
		return this.codigoTipoPedidoEstoque;
	}

	public void setCodigoTipoPedidoEstoque(Integer codigoTipoPedidoEstoque) {
		this.codigoTipoPedidoEstoque = codigoTipoPedidoEstoque;
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

	public String getDescricaoTipoPedidoEstoque() {
		return this.descricaoTipoPedidoEstoque;
	}

	public void setDescricaoTipoPedidoEstoque(String descricaoTipoPedidoEstoque) {
		this.descricaoTipoPedidoEstoque = descricaoTipoPedidoEstoque;
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
		tbPedidoEstoque.setTbTipoPedidoEstoque(this);

		return tbPedidoEstoque;
	}

	public TbPedidoEstoque removeTbPedidoEstoque(TbPedidoEstoque tbPedidoEstoque) {
		getTbPedidoEstoques().remove(tbPedidoEstoque);
		tbPedidoEstoque.setTbTipoPedidoEstoque(null);

		return tbPedidoEstoque;
	}

}