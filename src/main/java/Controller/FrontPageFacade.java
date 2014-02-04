    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.*;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * A controller. All calls to the model that are executed because of an action
 * taken by the cashier pass through here.
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class FrontPageFacade{

    @PersistenceContext(unitName = "webshopPU")
    private EntityManager em;
    
    public UsersDTO searchUser (String username) {
        Users    userFound = em.find (Users.class, username);
        return userFound;
    }
    
    /*
    public UsersDTO getUser (String username, String password) {
        Users    userFound = em.find (Users.class, username);
        if (userFound == null) {
            throw new EntityNotFoundException("No such user");
        }
        return userFound;
    }
    */
    
    
    /**
     * Adds a new user to the system
     * @param username the new user's name
     * @param password the new user's password
     * @param isAdmin determines if its an admin or not
     * @param isBanned determines if the user is banned or not
     * @return the new user
     */
    public UsersDTO addUser (String username, String password, boolean isAdmin, boolean isBanned) {
        Users    newUser = new Users (username, password, isAdmin, isBanned);
        em.persist(newUser);
        return newUser;
    }
    
    /**
     * Adds a new item
     * @param name the name of the item
     * @param price the price of the item
     * @param amount amount of the item
     */
    public void addItem (String name, int price, int amount) {
        Query   query = em.createQuery ("SELECT c FROM Item c WHERE c.name=:n");
        query.setParameter ("n", name);
        
        List    list = query.getResultList ();
        
        if (list == null || list.size () < 1){
            Item    item = new Item (name, price, amount);
            em.persist(item);
        }
        else {
            query = em.createQuery ("UPDATE Item b SET b.amount = b.amount + "+amount+" WHERE b.name = :n");    
            query.setParameter ("n", name);
            query.executeUpdate();
        }
    }
    
    /**
     * Removes a selected item
     * @param name the selected items name
     * @param amount how many items that are going to get removed
     */
    public void removeItem (String name, int amount) {
        Query   query = em.createQuery ("UPDATE Item b SET b.amount = b.amount - "+amount+" WHERE b.name = :u"); 
        query.setParameter ("u", name);
        query.executeUpdate();
    }

    /**
     * Get all the items from the database
     * @return all of the items in the database
     */
    public List getAllItem () {
        Query   query = em.createQuery ("SELECT c FROM Item c");
        return query.getResultList ();
    }
    
    /**
     * Gets the users basket
     * @param name the users name
     * @return the users current basket
     */
    public List getBasket (String name) {
        Query   query = em.createQuery ("SELECT c FROM Basket c WHERE c.username=:name");
        query.setParameter ("name", name);
        return query.getResultList ();  
    }
    
    /**
     * Adds an item to the users basket
     * @param username the users name
     * @param itemname the items name
     * @param amount the amount of the item
     */
    public void addToBasket (String username, String itemname, int amount){
        Query   query = em.createQuery ("SELECT c FROM Basket c WHERE c.username=:un AND c.itemname=:in");
        query.setParameter ("un", username);
        query.setParameter ("in", itemname);
        
        List    list = query.getResultList ();
        
        if (list == null || list.size () < 1){
            Basket    basket = new Basket (username, itemname, amount);
            em.persist(basket);
        }
        else {
            query = em.createQuery ("UPDATE Basket b SET b.amount = b.amount + "+amount+" WHERE b.username = :u AND b.itemname = :i");    
            query.setParameter ("u", username);
            query.setParameter ("i", itemname);
            query.executeUpdate();
        }     
    }
    
    /**
     * Removes an item from the basket
     * @param username the name of the user
     * @param itemname the name of the item
     */
    public void removeFromBasket (String username, String itemname) {
        Query   query = em.createQuery ("DELETE FROM Basket b WHERE b.username=:u AND b.itemname=:i");
        query.setParameter ("u", username);
        query.setParameter ("i", itemname);
        query.executeUpdate();
    }
    
    /**
     * Gets all the users
     * @return all of the users
     */
    public List getUsers () {
        Query   query = em.createQuery ("SELECT c FROM Users c");
        return query.getResultList ();  
    }
    
    /**
     * Bans an existing user
     * @param userName the selected users name
     */
    public void banUser (String userName) {
        Query   query = em.createQuery ("UPDATE Users b SET b.isBanned = 1 WHERE b.username = :u");    
        query.setParameter ("u", userName);
        query.executeUpdate();
    }
    
    /**
     * Unban a selected user
     * @param userName the selected users name
     */
    public void unBanUser (String userName) {
        Query   query = em.createQuery ("UPDATE Users b SET b.isBanned = 0 WHERE b.username = :u");    
        query.setParameter ("u", userName);
        query.executeUpdate();
    }
}
