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
 * The persistent class for the tb_produto database table.
 * 
 */
@Entity
@Table(name="tb_produto")
@NamedQueries({
	@NamedQuery(name="TbProduto.findAll", query="SELECT t FROM TbProduto t"),
	@NamedQuery(name="TbProduto.findWithEstoque", query="SELECT t FROM TbProduto t join fetch t.tbEstoques where t.idProduto = :id"),
	@NamedQuery(name="TbProduto.findWithItensPedido", query="SELECT t FROM TbProduto t join fetch t.tbItensPedidos where t.idProduto = :id")
})
public class TbProduto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String QUERY_FINDWITHESTOQUE = "TbProduto.findWithEstoque";
	public static final String QUERY_FINDWITHITENSPEDIDO = "TbProduto.findWithItensPedido";
	
	@Id
	@SequenceGenerator(name="TB_PRODUTO_IDPRODUTO_GENERATOR", sequenceName="TB_PRODUTO_ID_PRODUTO_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_PRODUTO_IDPRODUTO_GENERATOR")
	@Column(name="id_produto")
	private Integer idProduto;

	@Column(name="codigo_barras_produto")
	private Long codigoBarrasProduto;

	@Column(name="data_alteracao")
	private Date dataAlteracao;

	@Column(name="data_exclusao")
	private Date dataExclusao;

	@Column(name="data_insercao")
	private Date dataInsercao;

	@Column(name="descricao_produto")
	private String descricaoProduto;

	@Column(name="registro_excluido")
	private String registroExcluido;

	@Column(name="usuario_alteracao")
	private String usuarioAlteracao;

	@Column(name="usuario_exclusao")
	private String usuarioExclusao;

	@Column(name="usuario_insercao")
	private String usuarioInsercao;
	
	@Column(name="valor_unitario_produto")
	private double valorUnitarioProduto;

	//bi-directional many-to-one association to TbEstoque
	@OneToMany(mappedBy="tbProduto" )
	private List<TbEstoque> tbEstoques;

	//bi-directional many-to-one association to TbItensPedido
	@OneToMany(mappedBy="tbProduto")
	private List<TbItensPedido> tbItensPedidos;

	public TbProduto() {
	}

	public Integer getIdProduto() {
		return this.idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public Long getCodigoBarrasProduto() {
		return this.codigoBarrasProduto;
	}

	public void setCodigoBarrasProduto(Long codigoBarrasProduto) {
		this.codigoBarrasProduto = codigoBarrasProduto;
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

	public String getDescricaoProduto() {
		return this.descricaoProduto;
	}

	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
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

	public List<TbEstoque> getTbEstoques() {
		return this.tbEstoques;
	}

	public void setTbEstoques(List<TbEstoque> tbEstoques) {
		this.tbEstoques = tbEstoques;
	}

	public TbEstoque addTbEstoque(TbEstoque tbEstoque) {
		getTbEstoques().add(tbEstoque);
		tbEstoque.setTbProduto(this);

		return tbEstoque;
	}

	public TbEstoque removeTbEstoque(TbEstoque tbEstoque) {
		getTbEstoques().remove(tbEstoque);
		tbEstoque.setTbProduto(null);

		return tbEstoque;
	}

	public List<TbItensPedido> getTbItensPedidos() {
		return this.tbItensPedidos;
	}

	public void setTbItensPedidos(List<TbItensPedido> tbItensPedidos) {
		this.tbItensPedidos = tbItensPedidos;
	}

	public TbItensPedido addTbItensPedido(TbItensPedido tbItensPedido) {
		getTbItensPedidos().add(tbItensPedido);
		tbItensPedido.setTbProduto(this);

		return tbItensPedido;
	}

	public TbItensPedido removeTbItensPedido(TbItensPedido tbItensPedido) {
		getTbItensPedidos().remove(tbItensPedido);
		tbItensPedido.setTbProduto(null);

		return tbItensPedido;
	}
	
	public double getValorUnitarioProduto() {
		return this.valorUnitarioProduto;
	}

	public void setValorUnitarioProduto(double valorUnitarioProduto) {
		this.valorUnitarioProduto = valorUnitarioProduto;
	}

}