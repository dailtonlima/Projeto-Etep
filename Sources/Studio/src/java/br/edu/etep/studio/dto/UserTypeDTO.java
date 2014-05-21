/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.etep.studio.dto;

/**
 *
 * @author dailtonlima
 */
public class UserTypeDTO {
    
    private String code;
    
    private String userTypeName;

    public UserTypeDTO() {
    }
    
    public UserTypeDTO(String code, String userTypeName) {
        this.code = code;
        this.userTypeName = userTypeName;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserTypeName() {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.code != null ? this.code.hashCode() : 0);
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
        final UserTypeDTO other = (UserTypeDTO) obj;
        if ((this.code == null) ? (other.code != null) : !this.code.equals(other.code)) {
            return false;
        }
        return true;
    }
    
    
    
}
