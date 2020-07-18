package br.ma.mateus.scvm.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.ma.mateus.ejb.scvm.facade.ProdutoFacade;
import br.ma.mateus.ejb.scvm.model.TbProduto;
import br.ma.mateus.ejb.scvm.model.TbUsuario;
import br.ma.mateus.scvm.util.ConstantesSistema;
import br.ma.mateus.scvm.util.MessageUtil;
import br.ma.mateus.scvm.util.UtilitariosSession;


/**
 * @author Sandro Jr.
 * Manter Cadastro de Produtos
 */
@Named("mProduto")
@ViewScoped
public class ProdutoMB implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ProdutoFacade produtoFacade;
	
	
	private String descProdutoFiltro;
	private String descricaoProduto, acaoProdutoDialog, usuarioInsercao, usuarioAlteracao, registroExcluido, usuarioExclusao;
	private Date dataInsercao, dataAlteracao, dataExclusao;
	private Integer id;
	private Long codigoBarrasProduto, codBarraProdutoFiltro;
	
	private List<TbProduto> listProduto;
	
	public ProdutoMB(){
	}
	
	/**
	 * @author Sandro Jr.
	 * @return String
	 * Método chamado após o construtor. Reponsável em inicializar váriaveis toda vez que a funcionalidade for chamada. 
	 * Este metódo é executada apenas uma vez, assim como o construtor na chamada da funcionalidade.
	 */
	@PostConstruct
	public void init(){
		listProduto = new ArrayList<>();
		listarProduto(null, null, null);
	}
	
	/**
	 * @author Sandro Jr.
	 * @return String
	 * Método responsável por inicializar as variáveis da funcionalidade toda vez que for necessário. 
	 * Diferente do metódo init que é chamado apenas uma vez.
	 */
	public String inicializar() {
		id 				 = null;
		descProdutoFiltro  = "";
		descricaoProduto  = "";
		acaoProdutoDialog = "";
		usuarioInsercao  = "";
		usuarioAlteracao = "";
		registroExcluido = "N";
		usuarioExclusao	 = "";
		dataInsercao 	 = null;
		dataAlteracao 	 = null;
		dataExclusao 	 = null;
		listarProduto(null, null, null);
		return null;
	}
	
	/**
	 * @author Sandro Jr.
	 * @return String
	 * Método chamado pelo usuário no menu do sistema. Responsável de redirecioná-lo para a tela de listagem e filtro
	 */
	public String manterProduto() {
		inicializar();
		return ConstantesSistema.REDIRECT_MANTER_PRODUTO;
	}
	
	/**
	 * @author Sandro Jr.
	 * @return List
	 * @param Long
	 * @param String
	 * Método responsável por listar os Produtos pelos parâmetros passados.
	 */
	private List<TbProduto> listarProduto(Integer id, Long codigoBarrasProduto, String descricaoProduto){
		listProduto = produtoFacade.find(id, codigoBarrasProduto, descricaoProduto);
		return listProduto;
	}
	
	/**
	 * @author Sandro Jr.
	 * @return List
	 * Método chamado pelo usuário na ação de filtrar. 
	 * Método responsável por filtrar os Produtos.
	 */
	public List<TbProduto> filtrar(){
		return listarProduto(id, codBarraProdutoFiltro, descProdutoFiltro);
	}
	
	/**
	 * Método chamado pelo usuário. Responsável por executar a acao escolhida pelo Usuário: 'inserir', 'editar', 'excluir'.
	 * @author Sandro Jr.
	 * @return String
	 */
	public String executarAcaoProduto() {
//		String usuarioAcao = loginMB.getUsuario();
		if(!validarCampos()) {
			return null;
		}
		
		TbUsuario tbUsuario = (TbUsuario) UtilitariosSession.getHttpSessionObject("usuarioLogado");
		
		String usuarioAcao = tbUsuario.getLoginUsuario();
		
		String acao = (acaoProdutoDialog == null ? "" : acaoProdutoDialog);
		
		switch (acao) {
			case ConstantesSistema.ACAO_INSERIR:
				this.inserirProduto(codigoBarrasProduto, descricaoProduto, usuarioAcao, new Date(), "", null, ConstantesSistema.REGISTRO_NAO_EXCLUIDO, "", null);
				break;
			
			case ConstantesSistema.ACAO_EDITAR:
				this.atualizarProduto(id, codigoBarrasProduto, descricaoProduto, usuarioInsercao, dataInsercao, usuarioAcao, new Date(), ConstantesSistema.REGISTRO_NAO_EXCLUIDO, usuarioExclusao, dataExclusao);
				break;
				
			case ConstantesSistema.ACAO_EXCLUIR:
				this.excluirProduto(id, codigoBarrasProduto, descricaoProduto, usuarioInsercao, dataInsercao, usuarioAlteracao, dataAlteracao, ConstantesSistema.REGISTRO_EXCLUIDO, usuarioAcao, new Date());
				break;
		}
		
		this.inicializar();
		
		return null;
	}
	
	/**
	 * Método chamado pelo usuário na ação de editar um usuário.
	 * @author Sandro Jr.
	 * @return Long -> id do usuário salvo
	 * @param codigoBarrasProduto
	 * @param descricaoProduto
	 * @param usuarioInsercao
	 * @param dataInsercao
	 * @param usuarioAlteracao
	 * @param dataAlteracao
	 * @param registroExcluido
	 * @param usuarioExclusao
	 * @param dataExclusao
	 */
	private Integer inserirProduto(Long codigoBarrasProduto, String descricaoProduto, String usuarioInsercao,
			Date dataInsercao, String usuarioAlteracao, Date dataAlteracao, String registroExcluido,
			String usuarioExclusao, Date dataExclusao) {
		Integer idProdutoSalvo = null;
		try {
			idProdutoSalvo = produtoFacade.save(codigoBarrasProduto, descricaoProduto, usuarioInsercao, dataInsercao, usuarioAlteracao,
					dataAlteracao, registroExcluido, usuarioExclusao, dataExclusao);
			
			if(idProdutoSalvo != null) MessageUtil.sucess("Inserção de Produto", "O produto com identificação "+idProdutoSalvo+" foi criado com sucesso no sistema!");
			else MessageUtil.error("Inserção de Produto", "Não foi possível criar o produto!");
		} catch (Exception e) {
			// TODO: handle exception
			MessageUtil.error("Erro Inesperado", "Contate o Administrador do Sistema!");
		}
		
		return idProdutoSalvo;
	}
	
	/**
	 * @param id
	 * @param codigoBarrasProduto
	 * @param descricaoProduto
	 * @param usuarioInsercao
	 * @param dataInsercao
	 * @param usuarioAlteracao
	 * @param dataAlteracao
	 * @param registroExcluido
	 * @param usuarioExclusao
	 * @param dataExclusao
	 * @return
	 */
	private boolean excluirProduto(Integer id, Long codigoBarrasProduto, 
			  String descricaoProduto, String usuarioInsercao, 
			  Date dataInsercao, String usuarioAlteracao, 
			  Date dataAlteracao, String registroExcluido,
          String usuarioExclusao, Date dataExclusao) {
		
		boolean hasEstoqueOrItensPedido;
		try {
			hasEstoqueOrItensPedido = produtoFacade.hasEstoqueOrItensPedido(id);
			
			if(!hasEstoqueOrItensPedido) {
				produtoFacade.update(id, codigoBarrasProduto, descricaoProduto, usuarioInsercao, dataInsercao, usuarioAlteracao, dataAlteracao, registroExcluido, usuarioExclusao, dataExclusao);
				MessageUtil.sucess("Exclusão de Produto", "O usuário foi excluído com sucesso no sistema!");
				return true;
			}
			
			MessageUtil.warn("Exclusão de Produto", 
							 "Não foi possível excluir o usuário, porque ele possui aplicações cadastradas. "
							 + "Favor exclua primeiro as aplicações para poder excluir o usuário!");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			MessageUtil.error("Erro Inesperado", "Favor contate o Administrador do Sistema!");
		}
		
		return false;
	}

	/**
	 * @param id
	 * @param codigoBarrasProduto
	 * @param descricaoProduto
	 * @param usuarioInsercao
	 * @param dataInsercao
	 * @param usuarioAlteracao
	 * @param dataAlteracao
	 * @param registroExcluido
	 * @param usuarioExclusao
	 * @param dataExclusao
	 * @return
	 */
	private boolean atualizarProduto(Integer id, Long codigoBarrasProduto, 
			  String descricaoProduto, String usuarioInsercao, 
			  Date dataInsercao, String usuarioAlteracao, 
			  Date dataAlteracao, String registroExcluido,
            String usuarioExclusao, Date dataExclusao) {
		
		try {
			produtoFacade.update(id, codigoBarrasProduto, descricaoProduto, usuarioInsercao, dataInsercao, usuarioAlteracao,
					dataAlteracao, registroExcluido, usuarioExclusao, dataExclusao);
			MessageUtil.sucess("Edição de Produto", "O usuário foi alterado com sucesso!");
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MessageUtil.sucess("Edição de Produto", "Não foi possível alterar o usuário!");
		return false;
	}
	
	/**
	 * Validar os campos obrigatórios
	 * @return
	 */
	private boolean validarCampos() {
		boolean validou = true;
		if(codigoBarrasProduto == null) {
			MessageUtil.error("Campo Código de Barras", ConstantesSistema.CAMPO_OBRIGATORIO);
			validou = false;
		}
		if(descricaoProduto == null || descricaoProduto.equals("")) {
			MessageUtil.error("Campo Descrição", ConstantesSistema.CAMPO_OBRIGATORIO);
			validou = false;
		}
		return validou;
	}
	
	
/**********************************************GET E SET************************************************************************/

	/**
	 * @return the produtoFacade
	 */
	public ProdutoFacade getProdutoFacade() {
		return produtoFacade;
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
	 * @return the codigoBarrasProduto
	 */
	public Long getCodigoBarrasProduto() {
		return codigoBarrasProduto;
	}

	/**
	 * @param codigoBarrasProduto the codigoBarrasProduto to set
	 */
	public void setCodigoBarrasProduto(Long codigoBarrasProduto) {
		this.codigoBarrasProduto = codigoBarrasProduto;
	}

	/**
	 * @param produtoFacade the produtoFacade to set
	 */
	public void setProdutoFacade(ProdutoFacade produtoFacade) {
		this.produtoFacade = produtoFacade;
	}

	/**
	 * @return the codBarraProdutoFiltro
	 */
	public Long getCodBarraProdutoFiltro() {
		return codBarraProdutoFiltro;
	}

	/**
	 * @param codBarraProdutoFiltro the codBarraProdutoFiltro to set
	 */
	public void setCodBarraProdutoFiltro(Long codBarraProdutoFiltro) {
		this.codBarraProdutoFiltro = codBarraProdutoFiltro;
	}

	/**
	 * @return the descProdutoFiltro
	 */
	public String getDescProdutoFiltro() {
		return descProdutoFiltro;
	}

	/**
	 * @param descProdutoFiltro the descProdutoFiltro to set
	 */
	public void setDescProdutoFiltro(String descProdutoFiltro) {
		this.descProdutoFiltro = descProdutoFiltro;
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

	public List<TbProduto> getListProduto() {
		return listProduto;
	}

	public void setListProduto(List<TbProduto> listProduto) {
		this.listProduto = listProduto;
	}

	/**
	 * @return the acaoProdutoDialog
	 */
	public String getAcaoProdutoDialog() {
		return acaoProdutoDialog;
	}

	/**
	 * @param acaoProdutoDialog the acaoProdutoDialog to set
	 */
	public void setAcaoProdutoDialog(String acaoProdutoDialog) {
		this.acaoProdutoDialog = acaoProdutoDialog;
	}

	/**
	 * @return the descricaoProduto
	 */
	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	/**
	 * @param descricaoProduto the descricaoProduto to set
	 */
	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}

}
