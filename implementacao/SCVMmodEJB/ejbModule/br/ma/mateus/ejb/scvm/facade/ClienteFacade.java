package br.ma.mateus.ejb.scvm.facade;

import java.util.List;
import javax.ejb.Local;
import br.ma.mateus.ejb.scvm.model.TbCliente;

@Local
public interface ClienteFacade {
	public abstract List<TbCliente> findAll();

	public abstract List<TbCliente> find(Integer idCliente, String cpfCnpjCliente, String nomeCliente);

	public abstract List<TbCliente> find(Integer idCliente, String cpfCnpjCliente, String nomeCliente, String registroExcluido);

}