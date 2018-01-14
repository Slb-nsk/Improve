package org.improve.dao;

import org.improve.entities.ActionEntity;
import org.improve.entities.GiftEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
@Transactional
public class Dao {

    @PersistenceContext
    private EntityManager em;

    //количество имеющихся акций
    public Long actionsCount() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = cb.createQuery(Long.class);
        Root<ActionEntity> root = criteriaQuery.from(ActionEntity.class);
        criteriaQuery.select(cb.count(root));
        criteriaQuery.where(cb.gt(root.get("actionGiftsNumber"), 0));
        return em.createQuery(criteriaQuery).getSingleResult();
    }

    //список имеющихся акций с количеством подарков
    public List<ActionEntity> actionList() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ActionEntity> actionCriteria = cb.createQuery(ActionEntity.class);
        Root<ActionEntity> actionRoot = actionCriteria.from(ActionEntity.class);
        actionCriteria.select(actionRoot);
        actionCriteria.where(cb.gt(actionRoot.get("actionGiftsNumber"), 0));
        return em.createQuery(actionCriteria).getResultList();
    }

    //название акции и количество оставшихся по акции товаров
    public ActionEntity actionById(int action_id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ActionEntity> actionCriteria = cb.createQuery(ActionEntity.class);
        Root<ActionEntity> actionRoot = actionCriteria.from(ActionEntity.class);
        actionCriteria.select(actionRoot);
        actionCriteria.where(cb.equal(actionRoot.get("action_id"), action_id));
        return em.createQuery(actionCriteria).getSingleResult();
    }

    //название товара и количество имеющихся товаров данного вида
    public GiftEntity giftById(int gift_id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<GiftEntity> giftCriteria = cb.createQuery(GiftEntity.class);
        Root<GiftEntity> giftRoot = giftCriteria.from(GiftEntity.class);
        giftCriteria.select(giftRoot);
        giftCriteria.where(cb.equal(giftRoot.get("gift_id"), gift_id));
        return em.createQuery(giftCriteria).getSingleResult();
    }

    //количество наименований оставшихся на складе товаров
    public Long itemsCount() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = cb.createQuery(Long.class);
        Root<GiftEntity> root = criteriaQuery.from(GiftEntity.class);
        criteriaQuery.select(cb.count(root));
        criteriaQuery.where(cb.gt(root.get("giftsNumber"), 0));
        return em.createQuery(criteriaQuery).getSingleResult();
    }

    //сколько всего на складе осталось единиц товаров
    public Long giftsCount() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = cb.createQuery(Long.class);
        Root<GiftEntity> root = criteriaQuery.from(GiftEntity.class);
        CriteriaQuery<Long> giftsNumber = criteriaQuery.select(cb.sumAsLong(root.get("giftsNumber")));
        return em.createQuery(criteriaQuery).getSingleResult();
    }

    //список всех имеющихся на складе товаров с количеством
    public List<GiftEntity> searchAllGifts() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<GiftEntity> giftCriteria = cb.createQuery(GiftEntity.class);
        Root<GiftEntity> giftRoot = giftCriteria.from(GiftEntity.class);
        giftCriteria.select(giftRoot);
        giftCriteria.where(cb.gt(giftRoot.get("giftsNumber"), 0));
        return em.createQuery(giftCriteria).getResultList();
    }

    //фильтрованный список имеющихся на складе товаров с количеством
    public List<GiftEntity> searchByText(String userText) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<GiftEntity> giftCriteria = cb.createQuery(GiftEntity.class);
        Root<GiftEntity> giftRoot = giftCriteria.from(GiftEntity.class);
        giftCriteria.select(giftRoot);
        giftCriteria.where(
                cb.and(
                        cb.like(giftRoot.get("giftName"), "%" + userText + "%"),
                        (cb.gt(giftRoot.get("giftsNumber"), 0))
                )
        );
        System.out.println("запрос = " + giftCriteria);
        return em.createQuery(giftCriteria).getResultList();
    }

    //выдача подарка
    public void sendingGift(int action_id, int gift_id) {
        ActionEntity currentAction = em.find(ActionEntity.class, action_id);
        GiftEntity currentGift = em.find(GiftEntity.class, gift_id);
        currentGift.setGiftsNumber(giftById(gift_id).getGiftsNumber() - 1);
        currentAction.setActionGiftsNumber(actionById(action_id).getActionGiftsNumber() - 1);
        em.merge(currentAction);
        em.merge(currentGift);
    }

}
