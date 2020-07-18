package br.ma.mateus.scvm.util;

import java.util.Date;

public class ConstantesSistema {
	
	public static final Date DATA_ATUAL = new Date();
	public static final String ACAO_INSERIR = "INSERIR";
	public static final String ACAO_EDITAR = "EDITAR";
	public static final String ACAO_EXCLUIR = "EXCLUIR";
	public static final String REGISTRO_EXCLUIDO = "S";
	public static final String REGISTRO_NAO_EXCLUIDO = "N";
	public static final String CAMPO_OBRIGATORIO = "PREENCHA ESTE CAMPO";
	public static final Boolean ATIVO = new Boolean(true);
	
	public static final String REDIRECT_MANTER_PRODUTO = "/pages/produto/manterProduto.jsf?faces-redirect=true";
	public static final String REDIRECT_MANTER_USUARIO = "/pages/usuario/manterUsuario.jsf?faces-redirect=true";
	public static final String REDIRECT_MANTER_PEDIDO_ESTOQUE = "/pages/pedidoEstoque/manterPedidoEstoque.jsf?faces-redirect=true";
	public static final String REDIRECT_MANTER_FORMAPAGAMENTO = "/pages/formaPagamento/manterFormaPagamento.jsf?faces-redirect=true";
	
	public static final String REDIRECT_LOGIN = "/login.jsf?faces-redirect=true";
}
