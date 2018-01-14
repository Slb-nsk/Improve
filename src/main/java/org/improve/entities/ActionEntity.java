package org.improve.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "actionlist")
public class ActionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "action_id")
    private Integer action_id;

    @Column(name = "actionName")
    private String actionName;

    @Column(name = "actionGiftsNumber")
    private Integer actionGiftsNumber;

    public Integer getAction_id() {
        return action_id;
    }

    public void setAction_id(Integer action_id) {
        this.action_id = action_id;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public Integer getActionGiftsNumber() {
        return actionGiftsNumber;
    }

    public void setActionGiftsNumber(Integer actionGiftsNumber) {
        this.actionGiftsNumber = actionGiftsNumber;
    }
}
