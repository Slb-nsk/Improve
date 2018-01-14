package org.improve.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "giftlist")

public class GiftEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "gift_id")
    private Integer gift_id;

    @Column(name = "giftName")
    private String giftName;

    @Column(name = "giftsNumber")
    private Integer giftsNumber;

    public Integer getGift_id() {
        return gift_id;
    }

    public void setGift_id(Integer gift_id) {
        this.gift_id = gift_id;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public Integer getGiftsNumber() {
        return giftsNumber;
    }

    public void setGiftsNumber(Integer giftsNumber) {
        this.giftsNumber = giftsNumber;
    }
}
