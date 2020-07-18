package br.ma.mateus.ejb.scvm.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public abstract class GenericDAO<T> {
	private final static String UNIT_NAME = "scvmPu";
	   
	@PersistenceContext(unitName = UNIT_NAME)
	private EntityManager em;

	private Class<T> entityClass;

	public GenericDAO(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	public void save(T entity) {
		try {
			em.persist(entity);
			
	        em.flush();
	        em.clear();			
		} catch (NoResultException ne) {
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	        em.flush();
	        em.clear();	
		}		
	}

	public void delete(Object id, Class<T> classe) {
		T entityToBeRemoved = null;
		
		try {
			entityToBeRemoved = em.getReference(classe, id);

			em.remove(entityToBeRemoved);
			
	        em.flush();
	        em.clear();				
		} catch (NoResultException ne) {
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	        em.flush();
	        em.clear();	
		}
	}

	public T update(T entity) {
		T t = null;
		
		try {
			t = em.merge(entity);
			
	        em.flush();
	        em.clear();				
		} catch (NoResultException ne) {
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	        em.flush();
	        em.clear();	
		}
	
		return t;
	}

	public T find(Integer entityID)  throws NoResultException {
		T t = null;
		
		try {
			t = em.find(entityClass, entityID);
			
	        em.flush();
	        em.clear();			
		} catch (NoResultException ne) {
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	        em.flush();
	        em.clear();	
		}

		return t;
	}
	
	public void delete(String nameTable) {
		try {
			Query query = em.createQuery(nameTable);
		    query.executeUpdate();
		    
	        em.flush();
	        em.clear();			
		} catch (NoResultException ne) {
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	        em.flush();
	        em.clear();	
		}
	}
	
	@SuppressWarnings("unused")
	public void update(String namedQuery, Map<String, Object> parameters) {
		try {
			Query query = em.createNamedQuery(namedQuery);

			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}

			int i = query.executeUpdate();
		} catch (NoResultException ne) {
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	        em.flush();
	        em.clear();	
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> findAll() {
		List<T> t = null;
		
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(entityClass));
			
			t = em.createQuery(cq).getResultList();
			
	        em.flush();
	        em.clear();				
		} catch (NoResultException ne) {
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	        em.flush();
	        em.clear();	
		}
		
		return t;
	}

	@SuppressWarnings("unchecked")
	protected T findOneResult(String namedQuery, Map<String, Object> parameters) {
		T result = null;
		
		try {
			Query query = em.createNamedQuery(namedQuery);
			
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}

			result = (T) query.getSingleResult();
			
	        em.flush();
	        em.clear();			
		} catch (NoResultException ne) {
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	        em.flush();
	        em.clear();	
		}
        
		return result;
	}
	
	@SuppressWarnings("unchecked")
	protected void deletePsql(String namedQuery, Map<String, Object> parameters) {
		
		try {
			Query query = em.createNamedQuery(namedQuery);
			
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}

			query.executeUpdate();
			
	        em.flush();
	        em.clear();			
		} catch (NoResultException ne) {
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	        em.flush();
	        em.clear();	
		}
        
	}

	@SuppressWarnings("unchecked")
	protected List<T> getLista(String namedQuery, Map<String, Object> parameters) {
		List<T> result = null;

		try {
			Query query = em.createNamedQuery(namedQuery);

			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}

			result = (List<T>) query.getResultList();
			
	        em.flush();
	        em.clear();
		} catch (NoResultException ne) {
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	        em.flush();
	        em.clear();	
		}


		return result;
	}
	
	@SuppressWarnings("unchecked")
	protected List<Object[]> getLista(Map<String, Object> parameters, String namedQuery) {
		List<Object[]> result = null;

		try {
			Query query = em.createNamedQuery(namedQuery);

			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}

			result = (List<Object[]>) query.getResultList();
	        em.flush();
	        em.clear();
		} catch (NoResultException ne) {
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	        em.flush();
	        em.clear();	
		}

		return result;
	}
	
	@SuppressWarnings("rawtypes")
	protected List getListaValues(Map<String, Object> parameters, String namedQuery) {
		List result = null;

		try {
			Query query = em.createQuery(namedQuery);

			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}

			result = query.getResultList();
	        em.flush();
	        em.clear();
		} catch (NoResultException ne) {
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	        em.flush();
	        em.clear();	
		}

		return result;
	}
	
	@SuppressWarnings("unchecked")
	protected List<Object[]> getLista(String namedQuery) {
		List<Object[]> result = null;

		try {
			Query query = em.createNamedQuery(namedQuery);

			result = (List<Object[]>) query.getResultList();
			
	        em.flush();
	        em.clear();
		} catch (NoResultException ne) {
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	        em.flush();
	        em.clear();	
		}

		return result;
	}
	
	protected Object[] findOneResultObject(String namedQuery, Map<String, Object> parameters) {
		Object[] result = null;

		try {
			Query query = em.createNamedQuery(namedQuery);
			
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}
			
			result = (Object[]) query.getSingleResult();
			
	        em.flush();
	        em.clear();
		} catch (NoResultException ne) {
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	        em.flush();
	        em.clear();	
		}

		return result;
	}	

	private void populateQueryParameters(Query query,
			Map<String, Object> parameters) {

		//System.out.println(parameters.entrySet());
		
		for (Entry<String, Object> entry : parameters.entrySet()) {
			//System.out.println(entry.getKey() + "|" + entry.getValue());
			query.setParameter(entry.getKey(), entry.getValue());
		}
	}

	@SuppressWarnings("unchecked")
	protected List<T> getListaPorQuery(int start, int size, Map<String, Object> parameters, String query) {
		List<T> result = null;

		try {
			Query cQuery = em.createQuery(query);
//			em.createQuery(query);
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(cQuery, parameters);
			}
			cQuery.setFirstResult(start);
			cQuery.setMaxResults(size);
			result = (List<T>) cQuery.getResultList();
	        em.flush();
	        em.clear();
		} catch (NoResultException ne) {
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	        em.flush();
	        em.clear();	
		}

		return result;
	}	
	
	@SuppressWarnings("unchecked")
	protected Object findSingleResultPorQuery(Map<String, Object> parameters, String query) {
		Object result = null;

		try {
			Query cQuery = em.createQuery(query);
//			em.createQuery(query);
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(cQuery, parameters);
			}

			result = cQuery.getSingleResult();
	        em.flush();
	        em.clear();
		} catch (NoResultException ne) {
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	        em.flush();
	        em.clear();	
		}

		return result;
	}
	
	@SuppressWarnings("unchecked")
	protected List<Object[]> getListaPorQuery(Map<String, Object> parameters, String query) {
		List<Object[]> result = null;

		try {
			Query cQuery = em.createQuery(query);
//			em.createQuery(query);
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(cQuery, parameters);
			}

			result = (List<Object[]>) cQuery.getResultList();
	        em.flush();
	        em.clear();
		} catch (NoResultException ne) {
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	        em.flush();
	        em.clear();	
		}

		return result;
	}	
	
	@SuppressWarnings("unchecked")
	protected List<T> getListaPorQuery(String query, Map<String, Object> parameters) {
		List<T> result = null;

		try {
			Query cQuery = em.createQuery(query);
//			em.createQuery(query);
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(cQuery, parameters);
			}

			result = (List<T>) cQuery.getResultList();
	        em.flush();
	        em.clear();
		} catch (NoResultException ne) {
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	        em.flush();
	        em.clear();	
		}

		return result;
	}

	
	
	protected Object[] findOneResultObjectQuery(String namedQuery, Map<String, Object> parameters) {
		Object[] result = null;
		System.out.println(namedQuery);
		try {
			Query query = em.createQuery(namedQuery);
			
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}
			
			result = (Object[]) query.getSingleResult();
			
	        em.flush();
	        em.clear();
		} catch (NoResultException ne) {
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	        em.flush();
	        em.clear();	
		}

		return result;
	}
	
	protected Object findOneResultObjectQuery(Map<String, Object> parameters, String cQuery) {
		Object result = null;
		System.out.println(cQuery);
		try {
			Query query = em.createQuery(cQuery);
			
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}
			
			result = (Object) query.getSingleResult();
			
	        em.flush();
	        em.clear();
		} catch (NoResultException ne) {
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	        em.flush();
	        em.clear();	
		}

		return result;
	}
	
	@SuppressWarnings("unused")
	protected void deleteSqlQuery (String sqlQuery){
		Object result = null;
		
		try{
			result = em.createNativeQuery(sqlQuery).executeUpdate();
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			em.flush();
			em.clear();
		}		
	}
	
	protected Object findOneResultSqlQuery (String sqlQuery){
		Object result = null;
		
		try{
			result = em.createNativeQuery(sqlQuery).getSingleResult();
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			em.flush();
			em.clear();
		}
		
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	protected List getListaResultSqlQuery (Map<String, Object> parameters, String sqlQuery){
		List result = null;
		
		try{
			Query query = em.createNativeQuery(sqlQuery);
			
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}
			
			result =  query.getResultList();
					
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			em.flush();
			em.clear();
		}
		
		return result;
	}
}