package br.ma.mateus.scvm.mb;


import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.ma.mateus.ejb.scvm.facade.UsuarioFacade;
import br.ma.mateus.ejb.scvm.model.TbUsuario;
import br.ma.mateus.scvm.util.ConstantesSistema;
import br.ma.mateus.scvm.util.MessageUtil;
import br.ma.mateus.scvm.util.UtilitariosSession;

@Named("loginBean")
@SessionScoped
public class LoginMB implements Serializable{
	
	@EJB
	private UsuarioFacade usuarioFacade;
	
	private static final long serialVersionUID = 1L;
	private String login, senha;	
	
	private TbUsuario tbUsuario;
	
	public LoginMB(){
	} 
	
	@PostConstruct
	public void init(){
		this.login = "";
		this.senha = "";
	}
	
	public String logar(){
		
		this.tbUsuario = usuarioFacade.hasUsuario(this.login, this.senha);
		
		if (tbUsuario != null){
			UtilitariosSession.setHttpSessionObject("usuarioLogado", tbUsuario);
			this.init();
			
			return ConstantesSistema.REDIRECT_MANTER_PRODUTO;
		}else{
			MessageUtil.error("Login", "Nome de Usuário ou Senha inválidos");
			return null;
		}
	}

	public String deslogar(){
		
        FacesMessage msg = new FacesMessage("Você Saiu do Sistema de Controle de Vendas Mateus!", "Logoff");
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, msg);
		
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.setAttribute("loginBean", null);
		session.invalidate();
		 
		return "/login.jsf?faces-redirect=true";
	}
	
	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * @param senha the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
