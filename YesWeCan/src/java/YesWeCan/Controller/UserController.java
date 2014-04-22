/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package YesWeCan.Controller;

import YesWeCan.Facade.RewardsFacade;
import YesWeCan.Facade.UsersFacade;
import YesWeCan.Rewards;
import YesWeCan.Users;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ouaich
 */
@Named(value = "userController")
@SessionScoped
public class UserController implements Serializable {

    /**
     * Creates a new instance of UserController
     */
    
    HttpServletRequest req;
    String username;
    
    @EJB
    private UsersFacade usersFacade;
    @EJB
    private RewardsFacade rewardsFacade;
    
    public UserController() {
        
    
    }
    
    public Users getId(int userId){
        if(usersFacade.isValidId(userId))
            return usersFacade.getId(userId);
        return null;
    }
    
    public Users getLoggedUser() {
        if(isLogged())
        {
            req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String u = (String)req.getSession().getAttribute("isLoggedIn");
            return usersFacade.getUserByUsername(u);
        }
        return null;
    }
    
    public void create(){
        req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String user = req.getParameter("username");
        if(!usersFacade.isValidUser(user)){
            String passwd = req.getParameter("passwd");
            String mail = req.getParameter("mail");
            Users newUser = new Users();
            newUser.setPasswd(passwd);
            newUser.setUsername(user);
            username = user;
            usersFacade.create(newUser);
            req.getSession(true).setAttribute("isLoggedIn", user);
        }
    }
    
    public void login() throws IOException{
        req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse rep = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        String user = req.getParameter("username");
        String passwd = req.getParameter("password");
        System.out.println(user+passwd);
        if(usersFacade.isValidUserPass(user, passwd)){
            System.out.println("OK");
            req.getSession(true).setAttribute("isLoggedIn", user);
            username = user;
            rep.sendRedirect("index.xhtml");
        }else{
            rep.sendRedirect("error.xhtml");
        }
       
    }
    
    public void logout(){
        //FacesContext facesCtx = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext();
        req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        req.getSession().invalidate();
        username = null;
    }
    
    public boolean isLogged(){
        req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String u = (String)req.getSession().getAttribute("isLoggedIn");
        if(u == null)
            return false;
        return u.equals(username);
    }
    
    public boolean isNotLogged(){
        return !isLogged();
    }
    
    public boolean hasReward(int rewardId) {
        Users user = getLoggedUser();
        if(user != null)
            return user.hasReward(rewardId);
        return false;
    }
    
    public boolean hasNotReward(int rewardId) {
        Users user = getLoggedUser();
        if(user != null)
            return !user.hasReward(rewardId);
        return true;
    }
    
    public void addReward(int rewardId) {
        Rewards reward = rewardsFacade.getId(rewardId);
        
        Users user = getLoggedUser();
        if(user != null)
            user.addRewardToCollection(reward);
        
    }
}
