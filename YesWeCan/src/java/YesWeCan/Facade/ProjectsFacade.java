/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package YesWeCan.Facade;

import YesWeCan.Projects;
import YesWeCan.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Lanfeust
 */
@Stateless
public class ProjectsFacade extends AbstractFacade<Projects> {
    @PersistenceContext(unitName = "YesWeCanPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProjectsFacade() {
        super(Projects.class);
    }
    
    public void createProject(Projects project) {
        em.persist(project);
        em.flush();
    }
    
    public void updateProject(Projects project) {
        em.merge(project);
    }
    
    public void deleteProject(Projects project) {
        // Not working ?!?
        //em.remove(em.contains(project) ? project : em.merge(project));
        //em.flush();
        String query = "DELETE FROM Projects p, Rewards r WHERE r.idProject = ?1 AND p.id = ?1";
        em.createQuery(query).setParameter(1,project.getId()).getResultList();
    }
    
    public List<Projects> getLasts() {
        String query = "SELECT p FROM Projects p ORDER BY p.id DESC";
        return em.createQuery(query).setMaxResults(5).getResultList();
    }
    
    public List<Projects> getAll() {
        String query = "SELECT p FROM Projects p ORDER BY p.id DESC";
        return em.createQuery(query).getResultList();
    }
    
    public List<Projects> getUserProjects(Users user) {
        String query = "SELECT p FROM Projects p WHERE p.idUser = ?1 ORDER BY p.id DESC";
        return em.createQuery(query).setParameter(1,user).getResultList();
    }
    
    public Projects getId(int id) {
        String query = "SELECT p FROM Projects p WHERE p.id = ?1";
        return (Projects)em.createQuery(query).setParameter(1,id).getSingleResult();
    }
    
    public boolean isValidId(int id) {
        String query = "SELECT count(p) FROM Projects p WHERE p.id = ?1";
        long result = (long)em.createQuery(query).setParameter(1,id).getSingleResult();
        if(result > 0)
            return true;
        else
            return false;
    }
    
}
