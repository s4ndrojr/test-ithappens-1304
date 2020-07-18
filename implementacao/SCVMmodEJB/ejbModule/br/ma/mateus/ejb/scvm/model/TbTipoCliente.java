package br.ma.mateus.ejb.scvm.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the tb_tipo_cliente database table.
 * 
 */
@Entity
@Table(name="tb_tipo_cliente")
@NamedQuery(name="TbTipoCliente.findAll", query="SELECT t FROM TbTipoCliente t")
public class TbTipoCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_TIPO_CLIENTE_IDTIPOCLIENTE_GENERATOR", sequenceName="TB_TIPO_CLIENTE_ID_TIPO_CLIENTE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_TIPO_CLIENTE_IDTIPOCLIENTE_GENERATOR")
	@Column(name="id_tipo_cliente")
	private Integer idTipoCliente;

	@Column(name="codigo_tipo_cliente")
	private Integer codigoTipoCliente;

	@Column(name="data_alteracao")
	private Timestamp dataAlteracao;

	@Column(name="data_exclusao")
	private Timestamp dataExclusao;

	@Column(name="data_insercao")
	private Timestamp dataInsercao;

	@Column(name="descricao_tipo_cliente")
	private String descricaoTipoCliente;

	@Column(name="registro_excluido")
	private String registroExcluido;

	@Column(name="usuario_alteracao")
	private String usuarioAlteracao;

	@Column(name="usuario_exclusao")
	private String usuarioExclusao;

	@Column(name="usuario_insercao")
	private String usuarioInsercao;

	public TbTipoCliente() {
	}

	public Integer getIdTipoCliente() {
		return this.idTipoCliente;
	}

	public void setIdTipoCliente(Integer idTipoCliente) {
		this.idTipoCliente = idTipoCliente;
	}

	public Integer getCodigoTipoCliente() {
		return this.codigoTipoCliente;
	}

	public void setCodigoTipoCliente(Integer codigoTipoCliente) {
		this.codigoTipoCliente = codigoTipoCliente;
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

	public String getDescricaoTipoCliente() {
		return this.descricaoTipoCliente;
	}

	public void setDescricaoTipoCliente(String descricaoTipoCliente) {
		this.descricaoTipoCliente = descricaoTipoCliente;
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

}