package br.ma.mateus.ejb.scvm.facade;

import java.util.List;
import javax.ejb.Local;
import br.ma.mateus.ejb.scvm.model.TbTipoCliente;

@Local
public interface TipoClienteFacade {
	   public abstract List<TbTipoCliente> findAll();
	   public abstract void saveTbTipoCliente(TbTipoCliente tbTipoCliente);
	   public abstract void updateTbTipoCliente(TbTipoCliente tbTipoCliente);
	   public abstract void deleteTbTipoCliente(TbTipoCliente tbTipoCliente);
	   public abstract List<TbTipoCliente> findById(Integer idTipoCliente);
}	   