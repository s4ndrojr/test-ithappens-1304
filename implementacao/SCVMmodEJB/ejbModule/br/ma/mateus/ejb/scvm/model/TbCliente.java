package br.ma.mateus.ejb.scvm.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the tb_cliente database table.
 * 
 */
@Entity
@Table(name="tb_cliente")
@NamedQuery(name="TbCliente.findAll", query="SELECT t FROM TbCliente t")
public class TbCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_CLIENTE_IDCLIENTE_GENERATOR", sequenceName="TB_CLIENTE_ID_CLIENTE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_CLIENTE_IDCLIENTE_GENERATOR")
	@Column(name="id_cliente")
	private Integer idCliente;

	@Column(name="cpf_cnpj_cliente")
	private String cpfCnpjCliente;

	@Column(name="data_alteracao")
	private Timestamp dataAlteracao;

	@Column(name="data_exclusao")
	private Timestamp dataExclusao;

	@Column(name="data_insercao")
	private Timestamp dataInsercao;

	@Column(name="id_tipo_cliente")
	private Integer idTipoCliente;

	@Column(name="nome_cliente")
	private String nomeCliente;

	@Column(name="registro_excluido")
	private String registroExcluido;

	@Column(name="usuario_alteracao")
	private String usuarioAlteracao;

	@Column(name="usuario_exclusao")
	private String usuarioExclusao;

	@Column(name="usuario_insercao")
	private String usuarioInsercao;

	//bi-directional many-to-one association to TbPedidoEstoque
	@OneToMany(mappedBy="tbCliente")
	private List<TbPedidoEstoque> tbPedidoEstoques;

	public TbCliente() {
	}

	public Integer getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getCpfCnpjCliente() {
		return this.cpfCnpjCliente;
	}

	public void setCpfCnpjCliente(String cpfCnpjCliente) {
		this.cpfCnpjCliente = cpfCnpjCliente;
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

	public Integer getIdTipoCliente() {
		return this.idTipoCliente;
	}

	public void setIdTipoCliente(Integer idTipoCliente) {
		this.idTipoCliente = idTipoCliente;
	}

	public String getNomeCliente() {
		return this.nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
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
		tbPedidoEstoque.setTbCliente(this);

		return tbPedidoEstoque;
	}

	public TbPedidoEstoque removeTbPedidoEstoque(TbPedidoEstoque tbPedidoEstoque) {
		getTbPedidoEstoques().remove(tbPedidoEstoque);
		tbPedidoEstoque.setTbCliente(null);

		return tbPedidoEstoque;
	}

}