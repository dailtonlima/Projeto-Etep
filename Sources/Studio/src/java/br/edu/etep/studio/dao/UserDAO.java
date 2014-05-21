/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.etep.studio.dao;

import br.edu.etep.common.DBConnManager;
import br.edu.etep.studio.dto.PersonDTO;
import br.edu.etep.studio.dto.UserDTO;
import java.sql.PreparedStatement;

/**
 *
 * @author dailtonlima
 */
public class UserDAO {
    /**
     * 
     * @param user
     * @return 
     */
    public UserDTO get(UserDTO user){
        if(user.getPerson() != null && 
                user.getPerson().getCode() != null){
            return get(user.getPerson());
        }
        return null;
    }
    /**
//     * 
     * @param person
     * @return 
     */
    public UserDTO get(PersonDTO person){
        try {
            String sql = " SELECT code,password,validateDate,expireDate,personCode,userType FROM UserInfo Where personCode = ?";
            //Prepara o Comando de SQL
            PreparedStatement ps = DBConnManager.getConnectionFromPool().prepareStatement(sql);
            //Seta o parametro
            ps.setString(1, person.getCode());
        } catch (Exception e) {
        }
        return null;
    }
    /**
     * 
     * @param user
     * @return 
     */
    public UserDTO create(UserDTO user){
        UserDTO result = this.get(user.getPerson());
        try {
            if(result == null){
                String sql = "Insert Into UserInfo(code,password,personCode,userTypeCode) Values('',?,?,?)";
                //Prepara o Comando de SQL
                PreparedStatement ps = DBConnManager.getConnectionFromPool().prepareStatement(sql);
                //Seta o parametro
                ps.setString(1, user.getPassword());
                ps.setString(2, user.getPerson().getCode());
                ps.setString(3, user.getUserType().getCode());

                //Execute
                int create =  ps.executeUpdate();
                if(create == 1)
                        result = this.get(user.getPerson());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
