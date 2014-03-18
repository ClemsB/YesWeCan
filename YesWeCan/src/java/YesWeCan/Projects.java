/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package YesWeCan;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lanfeust
 */
@Entity
@Table(name = "projects")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Projects.findAll", query = "SELECT p FROM Projects p"),
    @NamedQuery(name = "Projects.findById", query = "SELECT p FROM Projects p WHERE p.id = :id"),
    @NamedQuery(name = "Projects.findByNom", query = "SELECT p FROM Projects p WHERE p.nom = :nom"),
    @NamedQuery(name = "Projects.findByTargetSum", query = "SELECT p FROM Projects p WHERE p.targetSum = :targetSum"),
    @NamedQuery(name = "Projects.findByCurrentSum", query = "SELECT p FROM Projects p WHERE p.currentSum = :currentSum"),
    @NamedQuery(name = "Projects.findByDelay", query = "SELECT p FROM Projects p WHERE p.delay = :delay"),
    @NamedQuery(name = "Projects.findByProjectscol", query = "SELECT p FROM Projects p WHERE p.projectscol = :projectscol")})

public class Projects implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "nom")
    private String nom;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "targetSum")
    private Double targetSum;
    @Column(name = "currentSum")
    private Double currentSum;
    @Column(name = "delay")
    private Integer delay;
    @Size(max = 45)
    @Column(name = "projectscol")
    private String projectscol;
    @JoinColumn(name = "idUser", referencedColumnName = "id")
    @ManyToOne
    private Users idUser;
    @OneToMany(mappedBy = "idProject")
    private Collection<Rewards> rewardsCollection;

    public Projects() {
    }

    public Projects(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTargetSum() {
        return targetSum;
    }

    public void setTargetSum(Double targetSum) {
        this.targetSum = targetSum;
    }

    public Double getCurrentSum() {
        return currentSum;
    }

    public void setCurrentSum(Double currentSum) {
        this.currentSum = currentSum;
    }

    public Integer getDelay() {
        return delay;
    }

    public void setDelay(Integer delay) {
        this.delay = delay;
    }

    public String getProjectscol() {
        return projectscol;
    }

    public void setProjectscol(String projectscol) {
        this.projectscol = projectscol;
    }

    public Users getIdUser() {
        return idUser;
    }

    public void setIdUser(Users idUser) {
        this.idUser = idUser;
    }

    @XmlTransient
    public Collection<Rewards> getRewardsCollection() {
        return rewardsCollection;
    }

    public void setRewardsCollection(Collection<Rewards> rewardsCollection) {
        this.rewardsCollection = rewardsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Projects)) {
            return false;
        }
        Projects other = (Projects) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "YesWeCan.Projects[ id=" + id + " ]";
    }
    
}
