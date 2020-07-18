package br.ma.mateus.scvm.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.sun.security.ntlm.Client;

import br.ma.mateus.ejb.scvm.facade.ClienteFacade;
import br.ma.mateus.ejb.scvm.facade.FilialFacade;
import br.ma.mateus.ejb.scvm.facade.PedidoEstoqueFacade;
import br.ma.mateus.ejb.scvm.model.TbCliente;
import br.ma.mateus.ejb.scvm.model.TbFilial;
import br.ma.mateus.ejb.scvm.model.TbItensPedido;
import br.ma.mateus.ejb.scvm.model.TbPedidoEstoque;
import br.ma.mateus.ejb.scvm.model.TbUsuario;
import br.ma.mateus.scvm.util.ConstantesSistema;
import br.ma.mateus.scvm.util.MessageUtil;
import br.ma.mateus.scvm.util.UtilitariosSession;

/**
 * @author Sandro Jr. Manter Cadastro de Usuários
 */
@Named("mPedido")
@ViewScoped
public class PedidoEstoqueMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private PedidoEstoqueFacade pedidoEstoqueFacade;
	
	@EJB
	private FilialFacade filialFacade;
	
	@EJB
	private ClienteFacade clienteFacade;
	
	private String descPedidoEstoqueFiltro;
	private String loginPedidoEstoque, nomePedidoEstoque, senhaPedidoEstoque, acaoPedidoEstoque, pedidoEstoqueInsercao, pedidoEstoqueAlteracao, registroExcluido,
			pedidoEstoqueExclusao;
	private Date dataInsercao, dataAlteracao, dataExclusao;
	private Integer id, idPedidoEstoqueFiltro;
	private String loginPedidoEstoqueFiltro, nomePedidoEstoqueFiltro;

	private List<TbPedidoEstoque> listPedidoEstoque;
	private List<TbFilial> listFilial;
	private List<TbCliente> listCliente;
	private List<TbItensPedido> listItensPedido;

	private TbPedidoEstoque pedido;
	private TbUsuario usuarioLogado;
	private TbFilial filial;
	private TbCliente cliente;
	
	public PedidoEstoqueMB() {
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
		listPedidoEstoque = new ArrayList<>();
		listarPedidoEstoque(null, null, null);
		this.usuarioLogado = (TbUsuario) UtilitariosSession.getHttpSessionObject("usuarioLogado");
	}

	/**
	 * @author Sandro Jr.
	 * @return String Método responsável por inicializar as variáveis da
	 *         funcionalidade toda vez que for necessário. Diferente do metódo init
	 *         que é chamado apenas uma vez.
	 */
	public String inicializar() {
		id = null;
		loginPedidoEstoqueFiltro = "";
		nomePedidoEstoqueFiltro = "";
		loginPedidoEstoque = "";
		nomePedidoEstoque = "";
		senhaPedidoEstoque = "";
		acaoPedidoEstoque = "";
		pedidoEstoqueInsercao = "";
		pedidoEstoqueAlteracao = "";
		registroExcluido = "N";
		pedidoEstoqueExclusao = "";
		dataInsercao = null;
		dataAlteracao = null;
		dataExclusao = null;
		this.filial = new TbFilial();
		this.cliente = new TbCliente();
		listarPedidoEstoque(null, null, null);
		return null;
	}

	/**
	 * @author Sandro Jr.
	 * @return String Método chamado pelo usuário no menu do sistema. Responsável de
	 *         redirecioná-lo para a tela de listagem e filtro
	 */
	public String manterPedido() {
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
	private List<TbPedidoEstoque> listarPedidoEstoque(Integer id, String loginPedidoEstoque, String nomePedidoEstoque) {
//		listPedidoEstoque = pedidoEstoqueFacade.find(id, loginPedidoEstoque, nomePedidoEstoque);
		return listPedidoEstoque;
	}
	
	/**
	 * @author Sandro Jr.
	 * @return List
	 * @param Long
	 * @param String Método responsável por listar as Filiais pelos parâmetros
	 *               passados.
	 */
	private List<TbFilial> listarFilial(Integer idFilial, String nomeFilial) {
		listFilial = filialFacade.find(idFilial, nomeFilial);
		return listFilial;
	}
	
	/**
	 * @author Sandro Jr.
	 * @return List
	 * @param Long
	 * @param String Método responsável por listar os Clientes pelos parâmetros
	 *               passados.
	 */
	private List<TbCliente> listarCliente(Integer idCliente, String cpfCnpjCliente, String nomeCliente) {
		listCliente = clienteFacade.find(idCliente, cpfCnpjCliente, nomeCliente);
		return listCliente;
	}

	/**
	 * @author Sandro Jr.
	 * @return List Método chamado pelo usuário na ação de filtrar. Método
	 *         responsável por filtrar os Usuários.
	 */
	public List<TbPedidoEstoque> filtrar() {
		return listarPedidoEstoque(idPedidoEstoqueFiltro, loginPedidoEstoqueFiltro, nomePedidoEstoqueFiltro);
	}
	
	/**
	 * @author Sandro Jr.
	 * @return
	 */
	public String buscarFilial() {
		listarFilial(this.filial.getIdFilial(), this.filial.getNomeFilial());
		return null;
	}
	
	/**
	 * @author Sandro Jr.
	 * @return
	 */
	public String buscarCliente() {
		listarCliente(this.cliente.getIdCliente(), this.cliente.getCpfCnpjCliente(), this.cliente.getNomeCliente());
		return null;
	}

	public void addListDeleteItem(TbItensPedido itens) {
		itens.setRegistroExcluido(ConstantesSistema.REGISTRO_EXCLUIDO);
	}
	
	public void removListDeleteItem(TbItensPedido itens) {
		itens.setRegistroExcluido(ConstantesSistema.REGISTRO_NAO_EXCLUIDO);
	}
	
	public boolean isItemRemovida(TbItensPedido itens) {
		if(itens.getRegistroExcluido().equals(ConstantesSistema.REGISTRO_EXCLUIDO)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Método chamado pelo usuário. Responsável por executar a acao escolhida pelo
	 * Usuário: 'inserir', 'editar', 'excluir'.
	 * 
	 * @author Sandro Jr.
	 * @return String
	 */
	public String executarAcaoPedidoEstoque() {
//		String pedidoEstoqueAcao = loginMB.getPedidoEstoque();
		if (!validarCampos()) {
			return null;
		}
		TbUsuario usuarioLogado = (TbUsuario) UtilitariosSession.getHttpSessionObject("usuarioLogado");
		
		String pedidoEstoqueAcao = usuarioLogado.getLoginUsuario();
		
		String acao = (acaoPedidoEstoque == null ? "" : acaoPedidoEstoque);

		switch (acao) {
		case ConstantesSistema.ACAO_INSERIR:
			this.inserirPedidoEstoque(loginPedidoEstoque, nomePedidoEstoque, senhaPedidoEstoque, pedidoEstoqueAcao, new Date(), "", null,
					ConstantesSistema.REGISTRO_NAO_EXCLUIDO, "", null);
			break;

		case ConstantesSistema.ACAO_EDITAR:
			this.atualizarPedidoEstoque(id, loginPedidoEstoque, nomePedidoEstoque, senhaPedidoEstoque, pedidoEstoqueInsercao, dataInsercao, pedidoEstoqueAcao, new Date(),
					ConstantesSistema.REGISTRO_NAO_EXCLUIDO, pedidoEstoqueExclusao, dataExclusao);
			break;

		case ConstantesSistema.ACAO_EXCLUIR:
			this.excluirPedidoEstoque(id, loginPedidoEstoque, nomePedidoEstoque, senhaPedidoEstoque, pedidoEstoqueInsercao, dataInsercao, pedidoEstoqueAlteracao,
					dataAlteracao, ConstantesSistema.REGISTRO_EXCLUIDO, pedidoEstoqueAcao, new Date());
			break;
		}

		this.inicializar();

		return null;
	}

	/**
	 * Método chamado pelo usuário na ação de editar um módulo.
	 * 
	 * @author Sandro Jr.
	 * @return Long -> id do módulo salvo
	 * @param loginPedidoEstoque
	 * @param nomePedidoEstoque
	 * @param pedidoEstoqueInsercao
	 * @param dataInsercao
	 * @param pedidoEstoqueAlteracao
	 * @param dataAlteracao
	 * @param registroExcluido
	 * @param pedidoEstoqueExclusao
	 * @param dataExclusao
	 * @return
	 */
	private Integer inserirPedidoEstoque(String loginPedidoEstoque, String nomePedidoEstoque, String senha, String pedidoEstoqueInsercao, Date dataInsercao,
			String pedidoEstoqueAlteracao, Date dataAlteracao, String registroExcluido, String pedidoEstoqueExclusao,
			Date dataExclusao) {
		Integer idPedidoEstoqueSalvo = null;
		try {
			/*idPedidoEstoqueSalvo = pedidoEstoqueFacade.save(loginPedidoEstoque, nomePedidoEstoque, senha, pedidoEstoqueInsercao, dataInsercao,
					pedidoEstoqueAlteracao, dataAlteracao, registroExcluido, pedidoEstoqueExclusao, dataExclusao);

			if (idPedidoEstoqueSalvo != null)
				MessageUtil.sucess("Inserção de PedidoEstoque",
						"O pedidoEstoque com identificação " + idPedidoEstoqueSalvo + " foi criado com sucesso no sistema!");
			else
				MessageUtil.error("Inserção de PedidoEstoque", "Não foi possível criar o pedidoEstoque!");*/
		} catch (Exception e) {
			// TODO: handle exception
			MessageUtil.error("Erro Inesperado", "Contate o Administrador do Sistema!");
		}

		return idPedidoEstoqueSalvo;
	}

	/**
	 * @param id
	 * @param loginPedidoEstoque
	 * @param nomePedidoEstoque
	 * @param pedidoEstoqueInsercao
	 * @param dataInsercao
	 * @param pedidoEstoqueAlteracao
	 * @param dataAlteracao
	 * @param registroExcluido
	 * @param pedidoEstoqueExclusao
	 * @param dataExclusao
	 * @return
	 */
	private boolean excluirPedidoEstoque(Integer id, String loginPedidoEstoque, String nomePedidoEstoque, String senha, String pedidoEstoqueInsercao,
			Date dataInsercao, String pedidoEstoqueAlteracao, Date dataAlteracao, String registroExcluido,
			String pedidoEstoqueExclusao, Date dataExclusao) {

		try {

			/*pedidoEstoqueFacade.update(id, loginPedidoEstoque, nomePedidoEstoque, senha, pedidoEstoqueInsercao, dataInsercao, pedidoEstoqueAlteracao,
					dataAlteracao, registroExcluido, pedidoEstoqueExclusao, dataExclusao);
			MessageUtil.sucess("Exclusão de Módulo", "O módulo foi excluído com sucesso no sistema!");

			MessageUtil.warn("Exclusão de Módulo",
					"Não foi possível excluir o módulo, porque ele possui aplicações cadastradas. "
							+ "Favor exclua primeiro as aplicações para poder excluir o módulo!");*/

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			MessageUtil.error("Erro Inesperado", "Favor contate o Administrador do Sistema!");
		}

		return false;
	}

	/**
	 * @param id
	 * @param loginPedidoEstoque
	 * @param nomePedidoEstoque
	 * @param pedidoEstoqueInsercao
	 * @param dataInsercao
	 * @param pedidoEstoqueAlteracao
	 * @param dataAlteracao
	 * @param registroExcluido
	 * @param pedidoEstoqueExclusao
	 * @param dataExclusao
	 * @return
	 */
	private boolean atualizarPedidoEstoque(Integer id, String loginPedidoEstoque, String nomePedidoEstoque, String senha, String pedidoEstoqueInsercao,
			Date dataInsercao, String pedidoEstoqueAlteracao, Date dataAlteracao, String registroExcluido,
			String pedidoEstoqueExclusao, Date dataExclusao) {

		try {
			/*pedidoEstoqueFacade.update(id, loginPedidoEstoque, nomePedidoEstoque, senha, pedidoEstoqueInsercao, dataInsercao, pedidoEstoqueAlteracao,
					dataAlteracao, registroExcluido, pedidoEstoqueExclusao, dataExclusao);
			MessageUtil.sucess("Edição de Módulo", "O módulo foi alterado com sucesso!");*/
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MessageUtil.sucess("Edição de Módulo", "Não foi possível alterar o módulo!");
		return false;
	}

	/**
	 * Validar os campos obrigatórios
	 * 
	 * @return
	 */
	private boolean validarCampos() {
		boolean validou = true;
		if (loginPedidoEstoque == null || loginPedidoEstoque.equals("")) {
			MessageUtil.error("Campo Login", ConstantesSistema.CAMPO_OBRIGATORIO);
			validou = false;
		}
		if (nomePedidoEstoque == null || nomePedidoEstoque.equals("")) {
			MessageUtil.error("Campo Nome", ConstantesSistema.CAMPO_OBRIGATORIO);
			validou = false;
		}
		return validou;
	}
	
	private boolean isIdPapel() {
		boolean notNull = false;
		if(id != null) notNull = true;
		return notNull;
	}
	
	public boolean isReadOnly() {
		return isIdPapel();
	}
	
	public boolean isRendered() {
		return isIdPapel();
	}

	/**********************************************
	 * GET E SET
	 ************************************************************************/

	/**
	 * @return the pedidoEstoqueFacade
	 */
	public PedidoEstoqueFacade getPedidoEstoqueFacade() {
		return pedidoEstoqueFacade;
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
	 * @param pedidoEstoqueFacade the pedidoEstoqueFacade to set
	 */
	public void setPedidoEstoqueFacade(PedidoEstoqueFacade pedidoEstoqueFacade) {
		this.pedidoEstoqueFacade = pedidoEstoqueFacade;
	}

	/**
	 * @return the descPedidoEstoqueFiltro
	 */
	public String getDescPedidoEstoqueFiltro() {
		return descPedidoEstoqueFiltro;
	}

	/**
	 * @param descPedidoEstoqueFiltro the descPedidoEstoqueFiltro to set
	 */
	public void setDescPedidoEstoqueFiltro(String descPedidoEstoqueFiltro) {
		this.descPedidoEstoqueFiltro = descPedidoEstoqueFiltro;
	}

	/**
	 * @return the pedidoEstoqueInsercao
	 */
	public String getPedidoEstoqueInsercao() {
		return pedidoEstoqueInsercao;
	}

	/**
	 * @param pedidoEstoqueInsercao the pedidoEstoqueInsercao to set
	 */
	public void setPedidoEstoqueInsercao(String pedidoEstoqueInsercao) {
		this.pedidoEstoqueInsercao = pedidoEstoqueInsercao;
	}

	/**
	 * @return the pedidoEstoqueAlteracao
	 */
	public String getPedidoEstoqueAlteracao() {
		return pedidoEstoqueAlteracao;
	}

	/**
	 * @param pedidoEstoqueAlteracao the pedidoEstoqueAlteracao to set
	 */
	public void setPedidoEstoqueAlteracao(String pedidoEstoqueAlteracao) {
		this.pedidoEstoqueAlteracao = pedidoEstoqueAlteracao;
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
	 * @return the pedidoEstoqueExclusao
	 */
	public String getPedidoEstoqueExclusao() {
		return pedidoEstoqueExclusao;
	}

	/**
	 * @param pedidoEstoqueExclusao the pedidoEstoqueExclusao to set
	 */
	public void setPedidoEstoqueExclusao(String pedidoEstoqueExclusao) {
		this.pedidoEstoqueExclusao = pedidoEstoqueExclusao;
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

	public List<TbPedidoEstoque> getListPedidoEstoque() {
		return listPedidoEstoque;
	}

	public void setListPedidoEstoque(List<TbPedidoEstoque> listPedidoEstoque) {
		this.listPedidoEstoque = listPedidoEstoque;
	}

	/**
	 * @return the acaoPedidoEstoque
	 */
	public String getAcaoPedidoEstoque() {
		return acaoPedidoEstoque;
	}

	/**
	 * @param acaoPedidoEstoque the acaoPedidoEstoque to set
	 */
	public void setAcaoPedidoEstoque(String acaoPedidoEstoque) {
		this.acaoPedidoEstoque = acaoPedidoEstoque;
	}

	/**
	 * @return the loginPedidoEstoque
	 */
	public String getLoginPedidoEstoque() {
		return loginPedidoEstoque;
	}

	/**
	 * @param loginPedidoEstoque the loginPedidoEstoque to set
	 */
	public void setLoginPedidoEstoque(String loginPedidoEstoque) {
		this.loginPedidoEstoque = loginPedidoEstoque;
	}

	/**
	 * @return the nomePedidoEstoque
	 */
	public String getNomePedidoEstoque() {
		return nomePedidoEstoque;
	}

	/**
	 * @param nomePedidoEstoque the nomePedidoEstoque to set
	 */
	public void setNomePedidoEstoque(String nomePedidoEstoque) {
		this.nomePedidoEstoque = nomePedidoEstoque;
	}

	/**
	 * @return the loginPedidoEstoqueFiltro
	 */
	public String getLoginPedidoEstoqueFiltro() {
		return loginPedidoEstoqueFiltro;
	}

	/**
	 * @param loginPedidoEstoqueFiltro the loginPedidoEstoqueFiltro to set
	 */
	public void setLoginPedidoEstoqueFiltro(String loginPedidoEstoqueFiltro) {
		this.loginPedidoEstoqueFiltro = loginPedidoEstoqueFiltro;
	}

	/**
	 * @return the nomePedidoEstoqueFiltro
	 */
	public String getNomePedidoEstoqueFiltro() {
		return nomePedidoEstoqueFiltro;
	}

	/**
	 * @param nomePedidoEstoqueFiltro the nomePedidoEstoqueFiltro to set
	 */
	public void setNomePedidoEstoqueFiltro(String nomePedidoEstoqueFiltro) {
		this.nomePedidoEstoqueFiltro = nomePedidoEstoqueFiltro;
	}

	/**
	 * @return the idPedidoEstoqueFiltro
	 */
	public Integer getIdPedidoEstoqueFiltro() {
		return idPedidoEstoqueFiltro;
	}

	/**
	 * @param idPedidoEstoqueFiltro the idPedidoEstoqueFiltro to set
	 */
	public void setIdPedidoEstoqueFiltro(Integer idPedidoEstoqueFiltro) {
		this.idPedidoEstoqueFiltro = idPedidoEstoqueFiltro;
	}

	/**
	 * @return the senhaPedidoEstoque
	 */
	public String getSenhaPedidoEstoque() {
		return senhaPedidoEstoque;
	}

	/**
	 * @param senha the senhaPedidoEstoque to set
	 */
	public void setSenhaPedidoEstoque(String senhaPedidoEstoque) {
		this.senhaPedidoEstoque = senhaPedidoEstoque;
	}

	/**
	 * @return the pedido
	 */
	public TbPedidoEstoque getPedido() {
		return pedido;
	}

	/**
	 * @param pedido the pedido to set
	 */
	public void setPedido(TbPedidoEstoque pedido) {
		this.pedido = pedido;
	}

	/**
	 * @return the usuarioLogado
	 */
	public TbUsuario getUsuarioLogado() {
		return usuarioLogado;
	}

	/**
	 * @param usuarioLogado the usuarioLogado to set
	 */
	public void setUsuarioLogado(TbUsuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	/**
	 * @return the listFilial
	 */
	public List<TbFilial> getListFilial() {
		return listFilial;
	}

	/**
	 * @param listFilial the listFilial to set
	 */
	public void setListFilial(List<TbFilial> listFilial) {
		this.listFilial = listFilial;
	}

	/**
	 * @return the filial
	 */
	public TbFilial getFilial() {
		return filial;
	}

	/**
	 * @param filial the filial to set
	 */
	public void setFilial(TbFilial filial) {
		this.filial = filial;
	}

	/**
	 * @return the cliente
	 */
	public TbCliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(TbCliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return the listCliente
	 */
	public List<TbCliente> getListCliente() {
		return listCliente;
	}

	/**
	 * @param listCliente the listCliente to set
	 */
	public void setListCliente(List<TbCliente> listCliente) {
		this.listCliente = listCliente;
	}

	/**
	 * @return the listItensPedido
	 */
	public List<TbItensPedido> getListItensPedido() {
		return listItensPedido;
	}

	/**
	 * @param listItensPedido the listItensPedido to set
	 */
	public void setListItensPedido(List<TbItensPedido> listItensPedido) {
		this.listItensPedido = listItensPedido;
	}
	
}
