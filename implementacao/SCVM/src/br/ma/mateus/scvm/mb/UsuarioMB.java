package br.ma.mateus.scvm.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.ma.mateus.ejb.scvm.facade.UsuarioFacade;
import br.ma.mateus.ejb.scvm.model.TbUsuario;
import br.ma.mateus.scvm.util.ConstantesSistema;
import br.ma.mateus.scvm.util.MessageUtil;
import br.ma.mateus.scvm.util.UtilitariosSession;

/**
 * @author Sandro Jr. Manter Cadastro de Usuários
 */
@Named("mUsuario")
@ViewScoped
public class UsuarioMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private UsuarioFacade usuarioFacade;

	private String descUsuarioFiltro;
	private String loginUsuario, nomeUsuario, senhaUsuario, acaoUsuario, usuarioInsercao, usuarioAlteracao, registroExcluido,
			usuarioExclusao;
	private Date dataInsercao, dataAlteracao, dataExclusao;
	private Integer id, idUsuarioFiltro;
	private String loginUsuarioFiltro, nomeUsuarioFiltro;

	private List<TbUsuario> listUsuario;

	public UsuarioMB() {
	}

	/**
	 * @author Sandro Jr.
	 * @return String Método chamado após o construtor. Reponsável em inicializar
	 *         váriaveis toda vez que a funcionalidade for chamada. Este metódo é
	 *         executada apenas uma vez, assim como o construtor na chamada da
	 *         funcionalidade.
	 */
	@PostConstruct
	public void init() {
		listUsuario = new ArrayList<>();
		listarUsuario(null, null, null);
	}

	/**
	 * @author Sandro Jr.
	 * @return String Método responsável por inicializar as variáveis da
	 *         funcionalidade toda vez que for necessário. Diferente do metódo init
	 *         que é chamado apenas uma vez.
	 */
	public String inicializar() {
		id = null;
		loginUsuarioFiltro = "";
		nomeUsuarioFiltro = "";
		loginUsuario = "";
		nomeUsuario = "";
		senhaUsuario = "";
		acaoUsuario = "";
		usuarioInsercao = "";
		usuarioAlteracao = "";
		registroExcluido = "N";
		usuarioExclusao = "";
		dataInsercao = null;
		dataAlteracao = null;
		dataExclusao = null;
		listarUsuario(null, null, null);
		return null;
	}

	/**
	 * @author Sandro Jr.
	 * @return String Método chamado pelo usuário no menu do sistema. Responsável de
	 *         redirecioná-lo para a tela de listagem e filtro
	 */
	public String manterUsuario() {
		inicializar();
		return ConstantesSistema.REDIRECT_MANTER_USUARIO;
	}

	/**
	 * @author Sandro Jr.
	 * @return List
	 * @param Long
	 * @param String Método responsável por listar os Usuários pelos parâmetros
	 *               passados.
	 */
	private List<TbUsuario> listarUsuario(Integer id, String loginUsuario, String nomeUsuario) {
		listUsuario = usuarioFacade.find(id, loginUsuario, nomeUsuario);
		return listUsuario;
	}

	/**
	 * @author Sandro Jr.
	 * @return List Método chamado pelo usuário na ação de filtrar. Método
	 *         responsável por filtrar os Usuários.
	 */
	public List<TbUsuario> filtrar() {
		return listarUsuario(idUsuarioFiltro, loginUsuarioFiltro, nomeUsuarioFiltro);
	}

	/**
	 * Método chamado pelo usuário. Responsável por executar a acao escolhida pelo
	 * Usuário: 'inserir', 'editar', 'excluir'.
	 * 
	 * @author Sandro Jr.
	 * @return String
	 */
	public String executarAcaoUsuario() {
//		String usuarioAcao = loginMB.getUsuario();
		if (!validarCampos()) {
			return null;
		}
		TbUsuario tbUsuario = (TbUsuario) UtilitariosSession.getHttpSessionObject("usuarioLogado");
		
		String usuarioAcao = tbUsuario.getLoginUsuario();
		
		String acao = (acaoUsuario == null ? "" : acaoUsuario);

		switch (acao) {
		case ConstantesSistema.ACAO_INSERIR:
			this.inserirUsuario(loginUsuario, nomeUsuario, senhaUsuario, usuarioAcao, new Date(), "", null,
					ConstantesSistema.REGISTRO_NAO_EXCLUIDO, "", null);
			break;

		case ConstantesSistema.ACAO_EDITAR:
			this.atualizarUsuario(id, loginUsuario, nomeUsuario, senhaUsuario, usuarioInsercao, dataInsercao, usuarioAcao, new Date(),
					ConstantesSistema.REGISTRO_NAO_EXCLUIDO, usuarioExclusao, dataExclusao);
			break;

		case ConstantesSistema.ACAO_EXCLUIR:
			this.excluirUsuario(id, loginUsuario, nomeUsuario, senhaUsuario, usuarioInsercao, dataInsercao, usuarioAlteracao,
					dataAlteracao, ConstantesSistema.REGISTRO_EXCLUIDO, usuarioAcao, new Date());
			break;
		}

		this.inicializar();

		return null;
	}

	/**
	 * Método chamado pelo usuário na ação de editar um usuario.
	 * 
	 * @author Sandro Jr.
	 * @return Long -> id do usuario salvo
	 * @param loginUsuario
	 * @param nomeUsuario
	 * @param usuarioInsercao
	 * @param dataInsercao
	 * @param usuarioAlteracao
	 * @param dataAlteracao
	 * @param registroExcluido
	 * @param usuarioExclusao
	 * @param dataExclusao
	 * @return
	 */
	private Integer inserirUsuario(String loginUsuario, String nomeUsuario, String senha, String usuarioInsercao, Date dataInsercao,
			String usuarioAlteracao, Date dataAlteracao, String registroExcluido, String usuarioExclusao,
			Date dataExclusao) {
		Integer idUsuarioSalvo = null;
		try {
			idUsuarioSalvo = usuarioFacade.save(loginUsuario, nomeUsuario, senha, usuarioInsercao, dataInsercao,
					usuarioAlteracao, dataAlteracao, registroExcluido, usuarioExclusao, dataExclusao);

			if (idUsuarioSalvo != null)
				MessageUtil.sucess("Inserção de Usuario",
						"O usuario com identificação " + idUsuarioSalvo + " foi criado com sucesso no sistema!");
			else
				MessageUtil.error("Inserção de Usuario", "Não foi possível criar o usuario!");
		} catch (Exception e) {
			// TODO: handle exception
			MessageUtil.error("Erro Inesperado", "Contate o Administrador do Sistema!");
		}

		return idUsuarioSalvo;
	}

	/**
	 * @param id
	 * @param loginUsuario
	 * @param nomeUsuario
	 * @param usuarioInsercao
	 * @param dataInsercao
	 * @param usuarioAlteracao
	 * @param dataAlteracao
	 * @param registroExcluido
	 * @param usuarioExclusao
	 * @param dataExclusao
	 * @return
	 */
	private boolean excluirUsuario(Integer id, String loginUsuario, String nomeUsuario, String senha, String usuarioInsercao,
			Date dataInsercao, String usuarioAlteracao, Date dataAlteracao, String registroExcluido,
			String usuarioExclusao, Date dataExclusao) {

		try {

			usuarioFacade.update(id, loginUsuario, nomeUsuario, senha, usuarioInsercao, dataInsercao, usuarioAlteracao,
					dataAlteracao, registroExcluido, usuarioExclusao, dataExclusao);
			MessageUtil.sucess("Exclusão de Usuário", "O usuario foi excluído com sucesso no sistema!");

			MessageUtil.warn("Exclusão de Usuário",
					"Não foi possível excluir o usuario, porque ele possui aplicações cadastradas. "
							+ "Favor exclua primeiro as aplicações para poder excluir o usuario!");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			MessageUtil.error("Erro Inesperado", "Favor contate o Administrador do Sistema!");
		}

		return false;
	}

	/**
	 * @param id
	 * @param loginUsuario
	 * @param nomeUsuario
	 * @param usuarioInsercao
	 * @param dataInsercao
	 * @param usuarioAlteracao
	 * @param dataAlteracao
	 * @param registroExcluido
	 * @param usuarioExclusao
	 * @param dataExclusao
	 * @return
	 */
	private boolean atualizarUsuario(Integer id, String loginUsuario, String nomeUsuario, String senha, String usuarioInsercao,
			Date dataInsercao, String usuarioAlteracao, Date dataAlteracao, String registroExcluido,
			String usuarioExclusao, Date dataExclusao) {

		try {
			usuarioFacade.update(id, loginUsuario, nomeUsuario, senha, usuarioInsercao, dataInsercao, usuarioAlteracao,
					dataAlteracao, registroExcluido, usuarioExclusao, dataExclusao);
			MessageUtil.sucess("Edição de Usuário", "O usuario foi alterado com sucesso!");
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MessageUtil.sucess("Edição de Usuário", "Não foi possível alterar o usuario!");
		return false;
	}

	/**
	 * Validar os campos obrigatórios
	 * 
	 * @return
	 */
	private boolean validarCampos() {
		boolean validou = true;
		if (loginUsuario == null || loginUsuario.equals("")) {
			MessageUtil.error("Campo Login", ConstantesSistema.CAMPO_OBRIGATORIO);
			validou = false;
		}
		if (nomeUsuario == null || nomeUsuario.equals("")) {
			MessageUtil.error("Campo Nome", ConstantesSistema.CAMPO_OBRIGATORIO);
			validou = false;
		}
		return validou;
	}

	/**********************************************
	 * GET E SET
	 ************************************************************************/

	/**
	 * @return the usuarioFacade
	 */
	public UsuarioFacade getUsuarioFacade() {
		return usuarioFacade;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @param usuarioFacade the usuarioFacade to set
	 */
	public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
		this.usuarioFacade = usuarioFacade;
	}

	/**
	 * @return the descUsuarioFiltro
	 */
	public String getDescUsuarioFiltro() {
		return descUsuarioFiltro;
	}

	/**
	 * @param descUsuarioFiltro the descUsuarioFiltro to set
	 */
	public void setDescUsuarioFiltro(String descUsuarioFiltro) {
		this.descUsuarioFiltro = descUsuarioFiltro;
	}

	/**
	 * @return the usuarioInsercao
	 */
	public String getUsuarioInsercao() {
		return usuarioInsercao;
	}

	/**
	 * @param usuarioInsercao the usuarioInsercao to set
	 */
	public void setUsuarioInsercao(String usuarioInsercao) {
		this.usuarioInsercao = usuarioInsercao;
	}

	/**
	 * @return the usuarioAlteracao
	 */
	public String getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	/**
	 * @param usuarioAlteracao the usuarioAlteracao to set
	 */
	public void setUsuarioAlteracao(String usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	/**
	 * @return the registroExcluido
	 */
	public String getRegistroExcluido() {
		return registroExcluido;
	}

	/**
	 * @param registroExcluido the registroExcluido to set
	 */
	public void setRegistroExcluido(String registroExcluido) {
		this.registroExcluido = registroExcluido;
	}

	/**
	 * @return the usuarioExclusao
	 */
	public String getUsuarioExclusao() {
		return usuarioExclusao;
	}

	/**
	 * @param usuarioExclusao the usuarioExclusao to set
	 */
	public void setUsuarioExclusao(String usuarioExclusao) {
		this.usuarioExclusao = usuarioExclusao;
	}

	/**
	 * @return the dataInsercao
	 */
	public Date getDataInsercao() {
		return dataInsercao;
	}

	/**
	 * @param dataInsercao the dataInsercao to set
	 */
	public void setDataInsercao(Date dataInsercao) {
		this.dataInsercao = dataInsercao;
	}

	/**
	 * @return the dataAlteracao
	 */
	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	/**
	 * @param dataAlteracao the dataAlteracao to set
	 */
	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	/**
	 * @return the dataExclusao
	 */
	public Date getDataExclusao() {
		return dataExclusao;
	}

	/**
	 * @param dataExclusao the dataExclusao to set
	 */
	public void setDataExclusao(Date dataExclusao) {
		this.dataExclusao = dataExclusao;
	}

	public List<TbUsuario> getListUsuario() {
		return listUsuario;
	}

	public void setListUsuario(List<TbUsuario> listUsuario) {
		this.listUsuario = listUsuario;
	}

	/**
	 * @return the acaoUsuario
	 */
	public String getAcaoUsuario() {
		return acaoUsuario;
	}

	/**
	 * @param acaoUsuario the acaoUsuario to set
	 */
	public void setAcaoUsuario(String acaoUsuario) {
		this.acaoUsuario = acaoUsuario;
	}

	/**
	 * @return the loginUsuario
	 */
	public String getLoginUsuario() {
		return loginUsuario;
	}

	/**
	 * @param loginUsuario the loginUsuario to set
	 */
	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}

	/**
	 * @return the nomeUsuario
	 */
	public String getNomeUsuario() {
		return nomeUsuario;
	}

	/**
	 * @param nomeUsuario the nomeUsuario to set
	 */
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	/**
	 * @return the loginUsuarioFiltro
	 */
	public String getLoginUsuarioFiltro() {
		return loginUsuarioFiltro;
	}

	/**
	 * @param loginUsuarioFiltro the loginUsuarioFiltro to set
	 */
	public void setLoginUsuarioFiltro(String loginUsuarioFiltro) {
		this.loginUsuarioFiltro = loginUsuarioFiltro;
	}

	/**
	 * @return the nomeUsuarioFiltro
	 */
	public String getNomeUsuarioFiltro() {
		return nomeUsuarioFiltro;
	}

	/**
	 * @param nomeUsuarioFiltro the nomeUsuarioFiltro to set
	 */
	public void setNomeUsuarioFiltro(String nomeUsuarioFiltro) {
		this.nomeUsuarioFiltro = nomeUsuarioFiltro;
	}

	/**
	 * @return the idUsuarioFiltro
	 */
	public Integer getIdUsuarioFiltro() {
		return idUsuarioFiltro;
	}

	/**
	 * @param idUsuarioFiltro the idUsuarioFiltro to set
	 */
	public void setIdUsuarioFiltro(Integer idUsuarioFiltro) {
		this.idUsuarioFiltro = idUsuarioFiltro;
	}

	/**
	 * @return the senhaUsuario
	 */
	public String getSenhaUsuario() {
		return senhaUsuario;
	}

	/**
	 * @param senha the senhaUsuario to set
	 */
	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}
	
}
