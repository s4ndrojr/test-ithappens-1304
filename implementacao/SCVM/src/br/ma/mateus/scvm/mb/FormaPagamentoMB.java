package br.ma.mateus.scvm.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.ma.mateus.ejb.scvm.facade.FormaPagamentoFacade;
import br.ma.mateus.ejb.scvm.model.TbFormaPagamento;
import br.ma.mateus.ejb.scvm.model.TbUsuario;
import br.ma.mateus.scvm.util.ConstantesSistema;
import br.ma.mateus.scvm.util.MessageUtil;
import br.ma.mateus.scvm.util.UtilitariosSession;


/**
 * @author Sandro Jr.
 * Manter Forma de Pagamento
 */
@Named("mFormaPagamento")
@ViewScoped
public class FormaPagamentoMB implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private FormaPagamentoFacade formaPagamentoFacade;
	
	private String descricaoFormaPagamento, acaoFormaPagamento, usuarioInsercao, 
				   usuarioAlteracao, registroExcluido, usuarioExclusao, descFormaPagamentoFiltro, tipoAcao;
	private Date dataInsercao, dataAlteracao, dataExclusao;
	private Integer id;
	private Long codigoBarrasFormaPagamento, codBarraFormaPagamentoFiltro;
	
	private List<TbFormaPagamento> listFormaPagamento;
	
	public FormaPagamentoMB(){
	}
	
	/**
	 * @author Sandro Jr.
	 * @return String
	 * Método chamado após o construtor. Reponsável em inicializar váriaveis toda vez que a funcionalidade for chamada. 
	 * Este metódo é executada apenas uma vez, assim como o construtor na chamada da funcionalidade.
	 */
	@PostConstruct
	public void init(){
		listFormaPagamento = new ArrayList<>();
		listarFormaPagamento(null, null);
	}
	
	/**
	 * @author Sandro Jr.
	 * @return String
	 * Método responsável por inicializar as variáveis da funcionalidade toda vez que for necessário. 
	 * Diferente do metódo init que é chamado apenas uma vez.
	 */
	public String inicializar() {
		id 				 = null;
		descFormaPagamentoFiltro  = "";
		descricaoFormaPagamento  = "";
		acaoFormaPagamento = "";
		usuarioInsercao  = "";
		usuarioAlteracao = "";
		registroExcluido = "N";
		usuarioExclusao	 = "";
		dataInsercao 	 = null;
		dataAlteracao 	 = null;
		dataExclusao 	 = null;
		listarFormaPagamento(null, null);
		return null;
	}
	
	/**
	 * @author Sandro Jr.
	 * @return String
	 * Método chamado pelo usuário no menu do sistema. Responsável de redirecioná-lo para a tela de listagem e filtro
	 */
	public String manterFormaPagamento() {
		inicializar();
		return ConstantesSistema.REDIRECT_MANTER_FORMAPAGAMENTO;
	}
	
	/**
	 * @author Sandro Jr.
	 * @return List
	 * @param Long
	 * @param String
	 * Método responsável por listar os FormaPagamentos pelos parâmetros passados.
	 */
	private List<TbFormaPagamento> listarFormaPagamento(Integer id, String descricaoFormaPagamento){
		listFormaPagamento = formaPagamentoFacade.find(id, descricaoFormaPagamento);
		return listFormaPagamento;
	}
	
	/**
	 * @author Sandro Jr.
	 * @return List
	 * Método chamado pelo usuário na ação de filtrar. 
	 * Método responsável por filtrar os FormaPagamentos.
	 */
	public List<TbFormaPagamento> filtrar(){
		return listarFormaPagamento(id, descFormaPagamentoFiltro);
	}
	
	/**
	 * Método chamado pelo usuário. Responsável por executar a acao escolhida pelo Usuário: 'inserir', 'editar', 'excluir'.
	 * @author Sandro Jr.
	 * @return String
	 */
	public String executarAcaoFormaPagamento() {
//		String usuarioAcao = loginMB.getUsuario();
		if(!validarCampos()) {
			return null;
		}
		
		TbUsuario tbUsuario = (TbUsuario) UtilitariosSession.getHttpSessionObject("usuarioLogado");
		
		String usuarioAcao = tbUsuario.getLoginUsuario();
		
		String acao = (acaoFormaPagamento == null ? "" : acaoFormaPagamento);
		
		switch (acao) {
			case ConstantesSistema.ACAO_INSERIR:
				this.inserirFormaPagamento(descricaoFormaPagamento, usuarioAcao, new Date(), "", null, ConstantesSistema.REGISTRO_NAO_EXCLUIDO, "", null);
				break;
			
			case ConstantesSistema.ACAO_EDITAR:
				this.atualizarFormaPagamento(id, descricaoFormaPagamento, usuarioInsercao, dataInsercao, usuarioAcao, new Date(), ConstantesSistema.REGISTRO_NAO_EXCLUIDO, usuarioExclusao, dataExclusao);
				break;
				
			case ConstantesSistema.ACAO_EXCLUIR:
				this.excluirFormaPagamento(id, descricaoFormaPagamento, usuarioInsercao, dataInsercao, usuarioAlteracao, dataAlteracao, ConstantesSistema.REGISTRO_EXCLUIDO, usuarioAcao, new Date());
				break;
		}
		
		this.inicializar();
		
		return null;
	}
	
	/**
	 * Método chamado pelo usuário na ação de editar um forma de pagamento.
	 * @author Sandro Jr.
	 * @return Long -> id do forma de pagamento salvo
	 * @param codigoBarrasFormaPagamento
	 * @param descricaoFormaPagamento
	 * @param usuarioInsercao
	 * @param dataInsercao
	 * @param usuarioAlteracao
	 * @param dataAlteracao
	 * @param registroExcluido
	 * @param usuarioExclusao
	 * @param dataExclusao
	 */
	private Integer inserirFormaPagamento(String descricaoFormaPagamento, String usuarioInsercao,
			Date dataInsercao, String usuarioAlteracao, Date dataAlteracao, String registroExcluido,
			String usuarioExclusao, Date dataExclusao) {
		Integer idFormaPagamentoSalvo = null;
		try {
			idFormaPagamentoSalvo = formaPagamentoFacade.save(descricaoFormaPagamento, usuarioInsercao, dataInsercao, usuarioAlteracao,
					dataAlteracao, registroExcluido, usuarioExclusao, dataExclusao);
			
			if(idFormaPagamentoSalvo != null) MessageUtil.sucess("Inserção de FormaPagamento", "O formaPagamento com identificação "+idFormaPagamentoSalvo+" foi criado com sucesso no sistema!");
			else MessageUtil.error("Inserção de FormaPagamento", "Não foi possível criar o formaPagamento!");
		} catch (Exception e) {
			// TODO: handle exception
			MessageUtil.error("Erro Inesperado", "Contate o Administrador do Sistema!");
		}
		
		return idFormaPagamentoSalvo;
	}
	
	/**
	 * @param id
	 * @param codigoBarrasFormaPagamento
	 * @param descricaoFormaPagamento
	 * @param usuarioInsercao
	 * @param dataInsercao
	 * @param usuarioAlteracao
	 * @param dataAlteracao
	 * @param registroExcluido
	 * @param usuarioExclusao
	 * @param dataExclusao
	 * @return
	 */
	private boolean excluirFormaPagamento(Integer id,
			  String descricaoFormaPagamento, String usuarioInsercao, 
			  Date dataInsercao, String usuarioAlteracao, 
			  Date dataAlteracao, String registroExcluido,
          String usuarioExclusao, Date dataExclusao) {
		
		boolean hasEstoqueOrItensPedido;
		try {
			hasEstoqueOrItensPedido = formaPagamentoFacade.hasItensPedido(id);
			
			if(!hasEstoqueOrItensPedido) {
				formaPagamentoFacade.update(id, descricaoFormaPagamento, usuarioInsercao, dataInsercao, usuarioAlteracao, dataAlteracao, registroExcluido, usuarioExclusao, dataExclusao);
				MessageUtil.sucess("Exclusão de Forma de Pagamento", "O forma de pagamento foi excluído com sucesso no sistema!");
				return true;
			}
			
			MessageUtil.warn("Exclusão de Forma de Pagamento", 
							 "Não foi possível excluir o forma de pagamento, porque ele possui aplicações cadastradas. "
							 + "Favor exclua primeiro as aplicações para poder excluir o forma de pagamento!");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			MessageUtil.error("Erro Inesperado", "Favor contate o Administrador do Sistema!");
		}
		
		return false;
	}

	/**
	 * @param id
	 * @param codigoBarrasFormaPagamento
	 * @param descricaoFormaPagamento
	 * @param usuarioInsercao
	 * @param dataInsercao
	 * @param usuarioAlteracao
	 * @param dataAlteracao
	 * @param registroExcluido
	 * @param usuarioExclusao
	 * @param dataExclusao
	 * @return
	 */
	private boolean atualizarFormaPagamento(Integer id,
			  String descricaoFormaPagamento, String usuarioInsercao, 
			  Date dataInsercao, String usuarioAlteracao, 
			  Date dataAlteracao, String registroExcluido,
            String usuarioExclusao, Date dataExclusao) {
		
		try {
			formaPagamentoFacade.update(id, descricaoFormaPagamento, usuarioInsercao, dataInsercao, usuarioAlteracao,
					dataAlteracao, registroExcluido, usuarioExclusao, dataExclusao);
			MessageUtil.sucess("Edição de Forma de Pagamento", "O forma de pagamento foi alterado com sucesso!");
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MessageUtil.sucess("Edição de Forma de Pagamento", "Não foi possível alterar o forma de pagamento!");
		return false;
	}
	
	/**
	 * Validar os campos obrigatórios
	 * @return
	 */
	private boolean validarCampos() {
		boolean validou = true;
		if(descricaoFormaPagamento == null || descricaoFormaPagamento.equals("")) {
			MessageUtil.error("Campo Descrição", ConstantesSistema.CAMPO_OBRIGATORIO);
			validou = false;
		}
		return validou;
	}
	
	
/**********************************************GET E SET************************************************************************/

	/**
	 * @return the formaPagamentoFacade
	 */
	public FormaPagamentoFacade getFormaPagamentoFacade() {
		return formaPagamentoFacade;
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
	 * @return the codigoBarrasFormaPagamento
	 */
	public Long getCodigoBarrasFormaPagamento() {
		return codigoBarrasFormaPagamento;
	}

	/**
	 * @param codigoBarrasFormaPagamento the codigoBarrasFormaPagamento to set
	 */
	public void setCodigoBarrasFormaPagamento(Long codigoBarrasFormaPagamento) {
		this.codigoBarrasFormaPagamento = codigoBarrasFormaPagamento;
	}

	/**
	 * @param formaPagamentoFacade the formaPagamentoFacade to set
	 */
	public void setFormaPagamentoFacade(FormaPagamentoFacade formaPagamentoFacade) {
		this.formaPagamentoFacade = formaPagamentoFacade;
	}

	/**
	 * @return the codBarraFormaPagamentoFiltro
	 */
	public Long getCodBarraFormaPagamentoFiltro() {
		return codBarraFormaPagamentoFiltro;
	}

	/**
	 * @param codBarraFormaPagamentoFiltro the codBarraFormaPagamentoFiltro to set
	 */
	public void setCodBarraFormaPagamentoFiltro(Long codBarraFormaPagamentoFiltro) {
		this.codBarraFormaPagamentoFiltro = codBarraFormaPagamentoFiltro;
	}

	/**
	 * @return the descFormaPagamentoFiltro
	 */
	public String getDescFormaPagamentoFiltro() {
		return descFormaPagamentoFiltro;
	}

	/**
	 * @param descFormaPagamentoFiltro the descFormaPagamentoFiltro to set
	 */
	public void setDescFormaPagamentoFiltro(String descFormaPagamentoFiltro) {
		this.descFormaPagamentoFiltro = descFormaPagamentoFiltro;
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

	public List<TbFormaPagamento> getListFormaPagamento() {
		return listFormaPagamento;
	}

	public void setListFormaPagamento(List<TbFormaPagamento> listFormaPagamento) {
		this.listFormaPagamento = listFormaPagamento;
	}

	/**
	 * @return the acaoFormaPagamento
	 */
	public String getAcaoFormaPagamento() {
		return acaoFormaPagamento;
	}

	/**
	 * @param acaoFormaPagamento the acaoFormaPagamento to set
	 */
	public void setAcaoFormaPagamento(String acaoFormaPagamento) {
		this.acaoFormaPagamento = acaoFormaPagamento;
	}

	/**
	 * @return the descricaoFormaPagamento
	 */
	public String getDescricaoFormaPagamento() {
		return descricaoFormaPagamento;
	}

	/**
	 * @param descricaoFormaPagamento the descricaoFormaPagamento to set
	 */
	public void setDescricaoFormaPagamento(String descricaoFormaPagamento) {
		this.descricaoFormaPagamento = descricaoFormaPagamento;
	}

	/**
	 * @return the tipoAcao
	 */
	public String getTipoAcao() {
		return tipoAcao;
	}

	/**
	 * @param tipoAcao the tipoAcao to set
	 */
	public void setTipoAcao(String tipoAcao) {
		this.tipoAcao = tipoAcao;
	}

}
