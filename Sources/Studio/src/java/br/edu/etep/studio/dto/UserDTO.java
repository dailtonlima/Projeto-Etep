/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.etep.studio.dto;

import java.util.Date;

/**
 *
 * @author dailtonlima
 */
public class UserDTO {
    
    private String code;
    
    private String password;
    
    private Date validateDate;
    
    private Date expireDate;
    
    private PersonDTO person;
    
    private UserTypeDTO userType;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getValidateDate() {
        return validateDate;
    }

    public void setValidateDate(Date validateDate) {
        this.validateDate = validateDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public PersonDTO getPerson() {
        if(this.person == null)
            this.person = new PersonDTO();
        return person;
    }

    public void setPerson(PersonDTO person) {
        this.person = person;
    }

    public UserTypeDTO getUserType() {
        if(this.userType == null)
            this.userType = new UserTypeDTO();
        return userType;
    }

    public void setUserType(UserTypeDTO userType) {
        this.userType = userType;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + (this.code != null ? this.code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserDTO other = (UserDTO) obj;
        if ((this.code == null) ? (other.code != null) : !this.code.equals(other.code)) {
            return false;
        }
        return true;
    }
    
}
