/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package YesWeCan;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
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
    @NamedQuery(name = "Projects.findByDelay", query = "SELECT p FROM Projects p WHERE p.delay = :delay")})

public class Projects implements Serializable {
    @Column(name = "delay")
    @NotNull
    @Temporal(TemporalType.DATE)
    @Future
    private Date delay;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(min=3, max = 45)
    @Column(name = "nom")
    @NotNull
    private String nom;
    @Lob
    @Size(min=3, max = 65535)
    @Column(name = "description")
    @NotNull
    private String description;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "targetSum")
    @Digits(integer=6,fraction=2)
    @NotNull
    private Double targetSum;
    @JoinColumn(name = "idUser", referencedColumnName = "id")
    @ManyToOne
    private Users idUser;
    @OneToMany(mappedBy = "idProject")
    private Collection<Rewards> rewardsCollection;

    public Projects() {
        this.delay = new Date();
    }

    public Projects(Integer id) {
        this.id = id;
        this.delay = new Date();
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
    
    public double getCurrentSum() {
        double sum = 0;
        if(this.getRewardsCollection() == null)
            return 0;
        for(Rewards reward : this.getRewardsCollection())
        {
            sum += reward.getUsersNumber()*reward.getPrize();
        }
        return sum;
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

    public Date getDelay() {
        return delay;
    }

    public void setDelay(Date delay) {
        this.delay = delay;
    }

    private void foreach(Collection<Rewards> rewardsCollection) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
