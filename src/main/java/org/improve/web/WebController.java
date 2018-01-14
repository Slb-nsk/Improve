package org.improve.web;

import org.improve.dao.Dao;
import org.improve.entities.ActionEntity;
import org.improve.entities.GiftEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

@Controller
public class WebController {
    private final Dao dao;

    @Autowired
    public WebController(Dao dao) {
        this.dao = dao;
    }


    @GetMapping("/")
    public ModelAndView start() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("start");

        List<ActionEntity> searchResult = dao.actionList();
        mav.addObject("searchResult", searchResult);

        return mav;

    }


    @GetMapping("/gift/{action_id}/{gift_id}")
    public ModelAndView presentingGift(@PathVariable int action_id, @PathVariable int gift_id) {
        ModelAndView mav = new ModelAndView();
        dao.sendingGift(action_id, gift_id);
        mav.setViewName("receivingGift");
        mav.addObject("giftName", dao.giftById(gift_id).getGiftName());
        mav.addObject("action_id", action_id);
        return mav;
    }


    @GetMapping("/{actionOperation}/{action_id}")
    @ResponseBody
    public ModelAndView operation(@PathVariable String actionOperation, @PathVariable int action_id, @RequestParam(name = "userText", defaultValue = "") String userText) {
        ModelAndView mav = new ModelAndView();
        ActionEntity action = dao.actionById(action_id);
        Long giftsCount = dao.giftsCount();
        List<GiftEntity> searchResult = Collections.emptyList();
        if (giftsCount == 0) {
            mav.setViewName("storageEmpty");
        } else if (action.getActionGiftsNumber() == 0) {
            mav.setViewName("actionEnd");
        } else {
            mav.setViewName("allGifts");
            switch (actionOperation) {
                case "searchAll":
                case "action":
                    searchResult = dao.searchAllGifts();
                    break;
                case "search":
                    searchResult = dao.searchByText(userText);
                    System.out.println("searchByText");
                    System.out.println("userText =" + userText);
                    break;
            }
            mav.addObject("searchResult", searchResult);
        }
        mav.addObject("userText", "");
        mav.addObject("action", action);
        return mav;
    }

}

