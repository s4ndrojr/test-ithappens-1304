package br.ma.mateus.ejb.scvm.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the tb_estoque database table.
 * 
 */
@Entity
@Table(name="tb_estoque")

@NamedQueries({
	@NamedQuery(name="TbEstoque.findAll", query="SELECT t FROM TbEstoque t"),
	@NamedQuery(name="TbEstoque.findByProduto", query="SELECT t FROM TbEstoque t where t.tbProduto.idProduto = :idProduto")
})
public class TbEstoque implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String QUERY_FINDBYPRODUTO = "TbEstoque.findByProduto";
	@Id
	@SequenceGenerator(name="TB_ESTOQUE_IDESTOQUE_GENERATOR", sequenceName="TB_ESTOQUE_ID_ESTOQUE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_ESTOQUE_IDESTOQUE_GENERATOR")
	@Column(name="id_estoque")
	private Integer idEstoque;

	@Column(name="data_alteracao")
	private Timestamp dataAlteracao;

	@Column(name="data_exclusao")
	private Timestamp dataExclusao;

	@Column(name="data_insercao")
	private Timestamp dataInsercao;

	@Column(name="quantidade_estoque")
	private Integer quantidadeEstoque;

	@Column(name="registro_excluido")
	private String registroExcluido;

	@Column(name="usuario_alteracao")
	private String usuarioAlteracao;

	@Column(name="usuario_exclusao")
	private String usuarioExclusao;

	@Column(name="usuario_insercao")
	private String usuarioInsercao;

	//bi-directional many-to-one association to TbFilial
	@ManyToOne
	@JoinColumn(name="id_filial")
	private TbFilial tbFilial;

	//bi-directional many-to-one association to TbProduto
	@ManyToOne
	@JoinColumn(name="id_produto")
	private TbProduto tbProduto;

	public TbEstoque() {
	}

	public Integer getIdEstoque() {
		return this.idEstoque;
	}

	public void setIdEstoque(Integer idEstoque) {
		this.idEstoque = idEstoque;
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

	public Integer getQuantidadeEstoque() {
		return this.quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
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

	public TbFilial getTbFilial() {
		return this.tbFilial;
	}

	public void setTbFilial(TbFilial tbFilial) {
		this.tbFilial = tbFilial;
	}

	public TbProduto getTbProduto() {
		return this.tbProduto;
	}

	public void setTbProduto(TbProduto tbProduto) {
		this.tbProduto = tbProduto;
	}

}