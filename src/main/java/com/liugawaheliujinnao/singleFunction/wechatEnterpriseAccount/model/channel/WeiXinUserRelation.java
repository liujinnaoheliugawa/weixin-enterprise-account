package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.channel;

import com.asus.weixin.model.user.ZenlifeUser;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by wufei on 16/2/29.
 */
@Entity
@Table(name = "wx_user_relation")
@org.hibernate.annotations.Cache(
        region = "common", usage = CacheConcurrencyStrategy.READ_WRITE
)
@NamedQueries({
})
public class WeiXinUserRelation implements Serializable {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "superiorId")
    private ZenlifeUser superior;  /**上级uid**/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inferiorId")
    private ZenlifeUser inferior;  /**下级uid**/

    @Id
    @Column(name = "date")
    private Date date;  
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZenlifeUser getSuperior() {
        return superior;
    }

    public void setSuperior(ZenlifeUser superior) {
        this.superior = superior;
    }

    public ZenlifeUser getInferior() {
        return inferior;
    }

    public void setInferior(ZenlifeUser inferior) {
        this.inferior = inferior;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
