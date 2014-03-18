/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package YesWeCan;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lanfeust
 */
@Entity
@Table(name = "rewards")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rewards.findAll", query = "SELECT r FROM Rewards r"),
    @NamedQuery(name = "Rewards.findById", query = "SELECT r FROM Rewards r WHERE r.id = :id"),
    @NamedQuery(name = "Rewards.findByAmount", query = "SELECT r FROM Rewards r WHERE r.amount = :amount")})
public class Rewards implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount")
    private Double amount;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "idProject", referencedColumnName = "id")
    @ManyToOne
    private Projects idProject;

    public Rewards() {
    }

    public Rewards(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Projects getIdProject() {
        return idProject;
    }

    public void setIdProject(Projects idProject) {
        this.idProject = idProject;
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
        if (!(object instanceof Rewards)) {
            return false;
        }
        Rewards other = (Rewards) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "YesWeCan.Rewards[ id=" + id + " ]";
    }
    
}
