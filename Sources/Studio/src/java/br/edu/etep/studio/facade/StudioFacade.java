/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.etep.studio.facade;

import br.edu.etep.common.UserType;
import br.edu.etep.studio.dao.CompanyDAO;
import br.edu.etep.studio.dao.PersonDAO;
import br.edu.etep.studio.dao.UserDAO;
import br.edu.etep.studio.dto.CompanyDTO;
import br.edu.etep.studio.dto.PersonDTO;
import br.edu.etep.studio.dto.UserDTO;
import br.edu.etep.studio.dto.UserTypeDTO;

/**
 *
 * @author dailtonlima
 */
public class StudioFacade {
     
    private CompanyDAO companyDAO;
    private PersonDAO personDAO; 
    private UserDAO userDAO;
    /**
     * 
     * @param user
     * @param userType
     * @return 
     */
    private boolean createUser(UserDTO user, UserTypeDTO userType){
        boolean result = true;
        try {
            
           PersonDTO person = user.getPerson();
           //
           companyDAO = new CompanyDAO();
           //
           CompanyDTO company = person.getCompany();
           //
           company = companyDAO.get(company);
           //
           if(company.getCode() == null){
              company = companyDAO.create(company);
           }
           //
           person.setCompany(company);
           //
           personDAO = new PersonDAO();
           //
           person = personDAO.get(person);
           //
           if(person.getCode() == null){
                person = personDAO.create(person);
           }
          //
           user.setPerson(person);
           user.setUserType(userType);
           //
           userDAO = new UserDAO();
           //
           UserDTO verifyUser = userDAO.get(user);
           //
           if(verifyUser == null){
               userDAO.create(user);
           }
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 
     * @param user
     * @return 
     */
    public boolean createOWNER(UserDTO user){
        return createUser(user, UserType.OWNER.getUserType());
    }
    /**
     * 
     * @param user
     * @return 
     */
    public boolean createPRODUCER(UserDTO user){
        return createUser(user, UserType.PRODUCER.getUserType());
    }
}
