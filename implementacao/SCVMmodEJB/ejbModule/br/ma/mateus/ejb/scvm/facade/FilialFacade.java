package br.ma.mateus.ejb.scvm.facade;

import java.util.List;
import javax.ejb.Local;
import br.ma.mateus.ejb.scvm.model.TbFilial;

@Local
public interface FilialFacade {
	public abstract List<TbFilial> findAll();
	public abstract List<TbFilial> find(Integer idFilial, String nomeFilial, String registroExcluido);
	public abstract List<TbFilial> find(Integer idFilial, String nomeFilial);
}