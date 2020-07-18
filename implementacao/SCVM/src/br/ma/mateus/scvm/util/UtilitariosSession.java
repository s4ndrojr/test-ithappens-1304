package br.ma.mateus.scvm.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;

public class UtilitariosSession {
	
	public static HttpSession getHttpSession() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		HttpSession s = (HttpSession) externalContext.getSession(true);
		return s;
	}

	public static void setHttpSessionObject(String campo, Object o) {
		getHttpSession().setAttribute(campo, o);
	}

	public static Object getHttpSessionObject(String campo) {
		return getHttpSession().getAttribute(campo);
	}
	
	public static void genericShowMessage(String mensagem) {
		FacesMessage message = new FacesMessage(mensagem);
		message.setSeverity(FacesMessage.SEVERITY_ERROR);
		throw new ValidatorException(message);
	}

}
