package br.ma.mateus.ejb.scvm.imp;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import br.ma.mateus.ejb.scvm.facade.FilialFacade;
import br.ma.mateus.ejb.scvm.model.TbFilial;
import br.ma.mateus.ejb.scvm.util.Constantes;
import br.ma.mateus.ejb.scvm.dao.TbFilialDAO;

@Stateless
@LocalBean
public class FilialFacadeImp implements FilialFacade {
	@EJB
	private TbFilialDAO tbFilialDAO;

	@Override
	public List<TbFilial> findAll() {
		return tbFilialDAO.getLista();
	}

	@Override
	public List<TbFilial> find(Integer idFilial, String nomeFilial) {
		return this.find(idFilial, nomeFilial, Constantes.REGISTRO_NAO_EXCLUIDO);
	}

	@Override
	public List<TbFilial> find(Integer idFilial, String nomeFilial, String registroExcluido) {
		// TODO Auto-generated method stub
		try {
			return tbFilialDAO.find(idFilial, nomeFilial, registroExcluido);
		} catch (NoResultException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}