/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package YesWeCan.Facade;

import YesWeCan.Users;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Lanfeust
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> {
    @PersistenceContext(unitName = "YesWeCanPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }
    
    public boolean isValidUser(String user) {
        String query = "SELECT u FROM Users u WHERE u.username = '"+user+"'";
       
        if(em.createQuery(query).getResultList().size() == 1)
            return true;
        
        return false;
    }
    
    public boolean isValidUserPass(String user, String pwd) {
        String query = "SELECT u FROM Users u WHERE u.username = '"+user+"' AND u.passwd = '"+pwd+"'";
       
        if(em.createQuery(query).getResultList().size() == 1)
            return true;
        
        return false;
    }
    
}
