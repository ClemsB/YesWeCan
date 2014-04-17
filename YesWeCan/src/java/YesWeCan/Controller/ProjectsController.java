/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package YesWeCan.Controller;

import YesWeCan.Facade.ProjectsFacade;
import YesWeCan.Projects;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Lanfeust
 */
@Named(value = "projectsController")
@SessionScoped
public class ProjectsController implements Serializable {

    @EJB
    private ProjectsFacade projectsFacade;
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
    
    public Projects getId(int id) {
        if(projectsFacade.isValidId(id))
            return projectsFacade.getId(id);
        else
            return null;
    }
    
    public void backProject(int id) {
        
    }
    
    public void createProject() {
        
    }
}
