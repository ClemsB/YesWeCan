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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lanfeust
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findById", query = "SELECT u FROM Users u WHERE u.id = :id"),
    @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username"),
    @NamedQuery(name = "Users.findByPasswd", query = "SELECT u FROM Users u WHERE u.passwd = :passwd")})
public class Users implements Serializable {
    @ManyToMany(mappedBy = "usersCollection")
    private Collection<Rewards> rewardsCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(min=3, max = 45)
    @NotNull
    @Column(name = "username")
    private String username;
    @Size(min=3, max = 45)
    @NotNull
    @Column(name = "passwd")
    private String passwd;
    @OneToMany(mappedBy = "idUser")
    private Collection<Projects> projectsCollection;

    public Users() {
    }

    public Users(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @XmlTransient
    public Collection<Projects> getProjectsCollection() {
        return projectsCollection;
    }

    public void setProjectsCollection(Collection<Projects> projectsCollection) {
        this.projectsCollection = projectsCollection;
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
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "YesWeCan.Users[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<Rewards> getRewardsCollection() {
        return rewardsCollection;
    }

    public void setRewardsCollection(Collection<Rewards> rewardsCollection) {
        this.rewardsCollection = rewardsCollection;
    }
    
    public void addRewardToCollection(Rewards reward) {
        rewardsCollection.add(reward);
    }
    
    public boolean hasReward(Rewards reward) {
        for(Rewards currentReward : rewardsCollection) {
            if(reward.getId().equals(currentReward.getId()))
                return true;
        }
        return false;
    }
    
    public boolean hasReward(int rewardId) {
        for(Rewards currentReward : rewardsCollection) {
            if(rewardId == currentReward.getId())
                return true;
        }
        return false;
    }
    
}
