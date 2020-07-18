package br.ma.mateus.ejb.scvm.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the tb_status database table.
 * 
 */
@Entity
@Table(name="tb_status")
@NamedQuery(name="TbStatus.findAll", query="SELECT t FROM TbStatus t")
public class TbStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_STATUS_IDSTATUS_GENERATOR", sequenceName="TB_STATUS_ID_STATUS_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_STATUS_IDSTATUS_GENERATOR")
	@Column(name="id_status")
	private Integer idStatus;

	@Column(name="data_alteracao")
	private Timestamp dataAlteracao;

	@Column(name="data_exclusao")
	private Timestamp dataExclusao;

	@Column(name="data_insercao")
	private Timestamp dataInsercao;

	@Column(name="descricao_status")
	private String descricaoStatus;

	@Column(name="registro_excluido")
	private String registroExcluido;

	@Column(name="usuario_alteracao")
	private String usuarioAlteracao;

	@Column(name="usuario_exclusao")
	private String usuarioExclusao;

	@Column(name="usuario_insercao")
	private String usuarioInsercao;

	//bi-directional many-to-one association to TbItensPedido
	@OneToMany(mappedBy="tbStatus")
	private List<TbItensPedido> tbItensPedidos;

	public TbStatus() {
	}

	public Integer getIdStatus() {
		return this.idStatus;
	}

	public void setIdStatus(Integer idStatus) {
		this.idStatus = idStatus;
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

	public String getDescricaoStatus() {
		return this.descricaoStatus;
	}

	public void setDescricaoStatus(String descricaoStatus) {
		this.descricaoStatus = descricaoStatus;
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
		tbItensPedido.setTbStatus(this);

		return tbItensPedido;
	}

	public TbItensPedido removeTbItensPedido(TbItensPedido tbItensPedido) {
		getTbItensPedidos().remove(tbItensPedido);
		tbItensPedido.setTbStatus(null);

		return tbItensPedido;
	}

}