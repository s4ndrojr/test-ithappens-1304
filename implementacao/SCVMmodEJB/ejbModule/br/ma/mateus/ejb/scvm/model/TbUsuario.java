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
 * The persistent class for the tb_usuario database table.
 * 
 */
@Entity
@Table(name="tb_usuario")

@NamedQueries({
	@NamedQuery(name="TbUsuario.findAll", query="SELECT t FROM TbUsuario t"),
	@NamedQuery(name="TbUsuario.findUsuarioByLoginSenha", query="SELECT t FROM TbUsuario t where t.loginUsuario = :login and t.senhaUsuario = :senha")
})
public class TbUsuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String QUERY_FINDUSUARIO = "TbUsuario.findUsuarioByLoginSenha";
	
	@Id
	@SequenceGenerator(name="TB_USUARIO_IDUSUARIO_GENERATOR", sequenceName="TB_USUARIO_ID_USUARIO_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_USUARIO_IDUSUARIO_GENERATOR")
	@Column(name="id_usuario")
	private Integer idUsuario;

	@Column(name="data_alteracao")
	private Date dataAlteracao;

	@Column(name="data_exclusao")
	private Date dataExclusao;

	@Column(name="data_insercao")
	private Date dataInsercao;

	@Column(name="login_usuario")
	private String loginUsuario;

	@Column(name="nome_usuario")
	private String nomeUsuario;

	@Column(name="registro_excluido")
	private String registroExcluido;

	@Column(name="senha_usuario")
	private String senhaUsuario;

	@Column(name="usuario_alteracao")
	private String usuarioAlteracao;

	@Column(name="usuario_exclusao")
	private String usuarioExclusao;

	@Column(name="usuario_insercao")
	private String usuarioInsercao;

	//bi-directional many-to-one association to TbPedidoEstoque
	@OneToMany(mappedBy="tbUsuario")
	private List<TbPedidoEstoque> tbPedidoEstoques;

	public TbUsuario() {
	}

	public Integer getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
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

	public String getLoginUsuario() {
		return this.loginUsuario;
	}

	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}

	public String getNomeUsuario() {
		return this.nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getRegistroExcluido() {
		return this.registroExcluido;
	}

	public void setRegistroExcluido(String registroExcluido) {
		this.registroExcluido = registroExcluido;
	}

	public String getSenhaUsuario() {
		return this.senhaUsuario;
	}

	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
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
		tbPedidoEstoque.setTbUsuario(this);

		return tbPedidoEstoque;
	}

	public TbPedidoEstoque removeTbPedidoEstoque(TbPedidoEstoque tbPedidoEstoque) {
		getTbPedidoEstoques().remove(tbPedidoEstoque);
		tbPedidoEstoque.setTbUsuario(null);

		return tbPedidoEstoque;
	}

}