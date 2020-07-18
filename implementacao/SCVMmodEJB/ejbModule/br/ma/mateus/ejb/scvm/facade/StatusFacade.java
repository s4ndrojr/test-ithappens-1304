package br.ma.mateus.ejb.scvm.facade;

import java.util.List;
import javax.ejb.Local;
import br.ma.mateus.ejb.scvm.model.TbStatus;

@Local
public interface StatusFacade {
	   public abstract List<TbStatus> findAll();
	   public abstract void saveTbStatus(TbStatus tbStatus);
	   public abstract void updateTbStatus(TbStatus tbStatus);
	   public abstract void deleteTbStatus(TbStatus tbStatus);
	   public abstract List<TbStatus> findById(Integer idStatus);
}	   