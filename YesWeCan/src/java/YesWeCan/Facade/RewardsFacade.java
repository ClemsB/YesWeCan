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
    
}
