/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package YesWeCan.Controller;

import YesWeCan.Facade.RewardsFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Lanfeust
 */
@Named(value = "rewardsController")
@RequestScoped
public class RewardsController implements Serializable {

    @EJB
    private RewardsFacade rewardsFacade;
    
    private FacesContext facesCtx;
    private ExternalContext extContext;
    private Map<String,String> requestParams;
    
    /**
     * Creates a new instance of ProjectsController
     */
    public RewardsController() {
        
    }
    
    public void createReward() {
        facesCtx = FacesContext.getCurrentInstance();
        extContext = facesCtx.getExternalContext();
        requestParams = extContext.getRequestParameterMap();
        
        int id = Integer.valueOf(requestParams.get("reward:id"));
        
        try {
            extContext.redirect(extContext.getRequestContextPath() + "/project_form.xhtml?id=" + requestParams.get("reward:id"));
        } catch (IOException ex) {
            Logger.getLogger(RewardsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
