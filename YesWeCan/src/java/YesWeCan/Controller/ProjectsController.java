/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package YesWeCan.Controller;

import YesWeCan.Facade.ProjectsFacade;
import YesWeCan.Facade.UsersFacade;
import YesWeCan.Projects;
import YesWeCan.Users;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
@Named(value = "projectsController")
@RequestScoped
public class ProjectsController implements Serializable {

    @EJB
    private ProjectsFacade projectsFacade;
    private int savedId;
    @EJB
    private UsersFacade usersFacade;
    
    private FacesContext facesCtx;
    private ExternalContext extContext;
    private Map<String,String> requestParams;
    
    /**
     * Creates a new instance of ProjectsController
     */
    public ProjectsController() {
    }
    
    public List<Projects> getLastProjects() {
        return projectsFacade.getLasts();
    }
    
    public List<Projects> getAll() {
        return projectsFacade.getAll();
    }
    
    public List<Projects> getUserProjects() {
        Users user = getLoggedUser();
        if(user != null)
            return projectsFacade.getUserProjects(user);
        return new ArrayList<Projects>();
    }
    
    public Projects getId(int id) {
        if(projectsFacade.isValidId(id))
            return projectsFacade.getId(id);
        if(projectsFacade.isValidId(savedId))
            return projectsFacade.getId(savedId);
        return new Projects();
    }
    
    public int getSavedId() {
        return savedId;
    }
    
    public void backProject(int id) {
        
    }
    
    public void doProject() {
        facesCtx = FacesContext.getCurrentInstance();
        extContext = facesCtx.getExternalContext();
        requestParams = extContext.getRequestParameterMap();
        
        int id = (requestParams.get("project:id").equals(""))?0:Integer.valueOf(requestParams.get("project:id"));
        
        Projects project;
        if(id == 0)
            project = new Projects();
        else
            project = this.getId(id);
        
        // Setting values
        project.setNom(requestParams.get("project:nom"));
        //project.setDelay(requestParams.get("delay"));
        project.setDescription(requestParams.get("project:description"));
        if(!requestParams.get("project:targetSum").equals(""))
            project.setTargetSum(Double.valueOf(requestParams.get("project:targetSum")));
        
        Users user = getLoggedUser();
        if(user != null)
        {
            project.setIdUser(user);
            if(id == 0)
            {
                projectsFacade.createProject(project);
            }else{
                savedId = id;
                projectsFacade.updateProject(project);
            }
        }
    }
    
    public boolean isUserProject(int projectId) {
        Projects project = getId(projectId);
        
        Users user = getLoggedUser();
        
        if(user == null || project.getIdUser() == null)
            return false;
        if(project.getIdUser().getId() != user.getId())
            return false;
        return true;
    }
    
    private Users getLoggedUser() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String u = (String)req.getSession().getAttribute("isLoggedIn");
        return usersFacade.getUserByUsername(u);
    }
}
