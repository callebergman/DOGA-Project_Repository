/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

/**
 * Thrown when user information provided is incorrect.
 */
public class LoginFailureException extends Exception {

    public LoginFailureException(String message) {
        super(message);
    }
}
