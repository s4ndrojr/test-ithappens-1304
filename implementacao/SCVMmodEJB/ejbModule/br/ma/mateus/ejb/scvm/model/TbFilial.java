package br.ma.mateus.ejb.scvm.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the tb_filial database table.
 * 
 */
@Entity
@Table(name="tb_filial")
@NamedQuery(name="TbFilial.findAll", query="SELECT t FROM TbFilial t")
public class TbFilial implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_FILIAL_IDFILIAL_GENERATOR", sequenceName="TB_FILIAL_ID_FILIAL_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_FILIAL_IDFILIAL_GENERATOR")
	@Column(name="id_filial")
	private Integer idFilial;

	@Column(name="data_alteracao")
	private Timestamp dataAlteracao;

	@Column(name="data_exclusao")
	private Timestamp dataExclusao;

	@Column(name="data_insercao")
	private Timestamp dataInsercao;

	@Column(name="nome_filial")
	private String nomeFilial;

	@Column(name="registro_excluido")
	private String registroExcluido;

	@Column(name="usuario_alteracao")
	private String usuarioAlteracao;

	@Column(name="usuario_exclusao")
	private String usuarioExclusao;

	@Column(name="usuario_insercao")
	private String usuarioInsercao;

	//bi-directional many-to-one association to TbEstoque
	@OneToMany(mappedBy="tbFilial")
	private List<TbEstoque> tbEstoques;

	public TbFilial() {
	}

	public Integer getIdFilial() {
		return this.idFilial;
	}

	public void setIdFilial(Integer idFilial) {
		this.idFilial = idFilial;
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

	public String getNomeFilial() {
		return this.nomeFilial;
	}

	public void setNomeFilial(String nomeFilial) {
		this.nomeFilial = nomeFilial;
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
		tbEstoque.setTbFilial(this);

		return tbEstoque;
	}

	public TbEstoque removeTbEstoque(TbEstoque tbEstoque) {
		getTbEstoques().remove(tbEstoque);
		tbEstoque.setTbFilial(null);

		return tbEstoque;
	}

}