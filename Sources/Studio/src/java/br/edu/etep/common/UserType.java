/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.etep.common;

import br.edu.etep.studio.dto.UserTypeDTO;

/**
 *
 * @author dailtonlima
 */
public enum UserType {
    OWNER("02","Proprietário"),PRODUCER("03","Produtor"); 

    private UserType(String code, String userTypeName) {
        this.userType = new UserTypeDTO(code, userTypeName);
    }
    
    public void setUserType(String code, String userTypeName){
        this.userType = new UserTypeDTO(code, userTypeName);
    }
    
    private UserTypeDTO userType;
    
    public UserTypeDTO getUserType(){
      return this.userType;
    }

    @Override
    public String toString() {
        return this.userType.toString();
    }
    
}
