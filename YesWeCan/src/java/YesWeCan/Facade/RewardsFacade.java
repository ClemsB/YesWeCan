/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package YesWeCan.Facade;

import YesWeCan.Rewards;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Lanfeust
 */
@Stateless
public class RewardsFacade extends AbstractFacade<Rewards> {
    @PersistenceContext(unitName = "YesWeCanPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RewardsFacade() {
        super(Rewards.class);
    }
    
    public void createReward(Rewards reward) {
        em.persist(reward);
        em.flush();
    }
    
    public void updateReward(Rewards reward) {
        em.merge(reward);
    }
    
    public void deleteReward(Rewards reward) {
        if(!em.contains(reward))
            em.merge(reward);
        em.remove(reward);
        em.flush();
    }
    
    public Rewards getId(int id) {
        String query = "SELECT r FROM Rewards r WHERE r.id = ?1";
        return (Rewards)em.createQuery(query).setParameter(1,id).getSingleResult();
    }
    
    public boolean isValidId(int id) {
        String query = "SELECT count(r) FROM Rewards r WHERE r.id = ?1";
        long result = (long)em.createQuery(query).setParameter(1,id).getSingleResult();
        if(result > 0)
            return true;
        else
            return false;
    }
    
}
