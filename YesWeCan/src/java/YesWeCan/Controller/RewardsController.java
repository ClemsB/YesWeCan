/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package YesWeCan.Controller;

import YesWeCan.Facade.ProjectsFacade;
import YesWeCan.Facade.RewardsFacade;
import YesWeCan.Facade.UsersFacade;
import YesWeCan.Rewards;
import YesWeCan.Users;
import java.io.Serializable;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Lanfeust
 */
@Named(value = "rewardsController")
@RequestScoped
public class RewardsController implements Serializable {

    @EJB
    private RewardsFacade rewardsFacade;
    @EJB
    private UsersFacade usersFacade;
    @EJB
    private ProjectsFacade projectsFacade;
    
    private FacesContext facesCtx;
    private ExternalContext extContext;
    private Map<String,String> requestParams;
    private int savedId;
    private int projectId;
    
    /**
     * Creates a new instance of ProjectsController
     */
    public RewardsController() {
        projectId = 0;
    }
    
    public Rewards getId(int id) {
        savedId = id;
        if(rewardsFacade.isValidId(id))
            return rewardsFacade.getId(id);
        if(rewardsFacade.isValidId(savedId))
            return rewardsFacade.getId(savedId);
        return new Rewards();
    }
    
    public void createReward() {
        facesCtx = FacesContext.getCurrentInstance();
        extContext = facesCtx.getExternalContext();
        requestParams = extContext.getRequestParameterMap();
        
        Rewards reward = new Rewards();
        reward.setDescription(requestParams.get("reward:description"));
        if(!requestParams.get("reward:prize").equals(""))
            reward.setPrize(Double.valueOf(requestParams.get("reward:prize")));
        if(!requestParams.get("reward:amount").equals(""))
            reward.setAmount(Integer.valueOf(requestParams.get("reward:amount")));
        if(!requestParams.get("reward:id").equals("") && projectsFacade.isValidId(Integer.valueOf(requestParams.get("reward:id"))))
        {
            reward.setIdProject(projectsFacade.getId(Integer.valueOf(requestParams.get("reward:id"))));
        
            Users user = getLoggedUser();
            if(user != null)
            {
                rewardsFacade.createReward(reward);
            }
        }
    }
    
    public void updateReward() {
        facesCtx = FacesContext.getCurrentInstance();
        extContext = facesCtx.getExternalContext();
        requestParams = extContext.getRequestParameterMap();
        
        int id = Integer.valueOf(requestParams.get("reward:id"));
        Rewards reward = getId(id);
        
        if(reward != null)
        {
            Users user = getLoggedUser();
            if(user != null)
            {
                if(rewardsFacade.isValidId(id))
                {
                    reward.setDescription(requestParams.get("reward:description"));
                    if(!requestParams.get("reward:prize").equals(""))
                        reward.setPrize(Double.valueOf(requestParams.get("reward:prize")));
                    if(!requestParams.get("reward:amount").equals(""))
                        reward.setAmount(Integer.valueOf(requestParams.get("reward:amount")));

                    savedId = id;
                    rewardsFacade.updateReward(reward);
                }
            }
        }
    }
    
    public void deleteReward(int rewardId) {
        if(rewardsFacade.isValidId(rewardId))
            rewardsFacade.deleteReward(rewardsFacade.getId(rewardId));
    }
    
    public String getProjectId() {
        return Integer.toString(projectId);
    }
    
    public void setProjectId(int id) {
        if(id != 0 && projectsFacade.isValidId(id))
            projectId = id;
    }
    
    public String projectId(int id) {
        if(id != 0 && projectsFacade.isValidId(id))
            projectId = id;
        return Integer.toString(projectId);
    }
    
    private Users getLoggedUser() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String u = (String)req.getSession().getAttribute("isLoggedIn");
        return usersFacade.getUserByUsername(u);
    }
}
