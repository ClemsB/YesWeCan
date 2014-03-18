/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package YesWeCan.Facade;

import YesWeCan.Projects;
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
    
    public List<Projects> getLasts() {
        String query = "SELECT p FROM Projects p ORDER BY p.id DESC";
        return em.createQuery(query).setMaxResults(5).getResultList();
    }
    
}
