/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.etep.studio.dao;

import br.edu.etep.common.DBConnManager;
import br.edu.etep.studio.dto.CompanyDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dailton
 */
public class CompanyDAO {
    
	/**
	 * 
	 * @param id
	 * @return
	 */
	public CompanyDTO read(String id){
		CompanyDTO company = null;
		try {
			String sql = "SELECT Top 1 code,companyName,registryDate FROM Company Where code = ?";
			//Prepara o Comando de SQL
			PreparedStatement ps =	DBConnManager.getConnectionFromPool().prepareStatement(sql);
			
                        //Seta o parametro
			ps.setString(1, id);
			//Executa o Comando de SQL e converte para Objeto
			List<CompanyDTO> list = convertForList(ps.executeQuery());
			if(list.size() > 0){
				company = list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return company;
	}
	/**
	 * 
	 * @param name
	 * @return
	 */
	public CompanyDTO get(String name){
		CompanyDTO company = null;
		try {
			String sql = "SELECT code,companyName,registryDate FROM Company Where companyName = ?";
			//Prepara o Comando de SQL
			PreparedStatement ps =	DBConnManager.getConnectionFromPool().prepareStatement(sql);
			//Seta o parametro
			ps.setString(1, name);
			//Executa o Comando de SQL e converte para Objeto
			List<CompanyDTO> list = convertForList(ps.executeQuery());
			if(list.size() > 0){
				company = list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return company;
	}
	/**
	 * 
	 * @return
	 */
	public List<CompanyDTO> listAll(){
		List<CompanyDTO> list = new ArrayList<CompanyDTO>();
		try {
			String sql = "SELECT code,companyName,registryDate FROM Company";
			PreparedStatement ps =	DBConnManager.getConnectionFromPool().prepareStatement(sql);
			list = convertForList(ps.executeQuery());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 
	 * @param company
	 * @return
	 */
	public CompanyDTO create(CompanyDTO company){
		CompanyDTO result = get(company.getCompanyName());
		
		try {
			if(result == null){
								
				String sql = "Insert Into Company (code,companyName) Values('',?)";
				PreparedStatement ps =	DBConnManager.getConnectionFromPool().prepareStatement(sql);
				//Parametros
				ps.setString(1, company.getCompanyName());
//				// Se existir endereço
				
				//Execute
				int create =  ps.executeUpdate();
				if(create == 1)
					result = this.get(company.getCompanyName());
			}
		} catch (Exception e) {
                    e.printStackTrace();
		}
	return result;
	}
	/**
	 * 
	 * @param company
	 * @return
	 */
	public CompanyDTO update(CompanyDTO company){
		CompanyDTO result = null;
		try {
			if(company.getCode() != null){
				
				// SQL de atualização da empresa
				String sql = 	"	Update Company set " +
						"	companyName  = ?" +
						"	Where code = ?";
				PreparedStatement ps =	DBConnManager.getConnectionFromPool().prepareStatement(sql);
				//Parametros
				ps.setString(1, company.getCompanyName());
				ps.setString(2, company.getCode());
				//Execute
				int update =  ps.executeUpdate();
				if(update == 1)
					result = this.read(company.getCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	return result;
	}
	/**
	 * 
	 * @param company
	 * @return
	 */
	public boolean delete(CompanyDTO company){
		Boolean result = true;
		try {
			String sql = 	" Delete From Company " +
					" Where code = ?";
			PreparedStatement ps =	DBConnManager.getConnectionFromPool().prepareStatement(sql);
			//Parametros
			ps.setString(1, company.getCode());
			//Execute
			int delete =  ps.executeUpdate();
			if(delete != 1)
				result = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	return result;
	}
	/**
	 * 
	 * @param rs
	 * @return
	 */
	private List<CompanyDTO> convertForList(ResultSet rs){
		List<CompanyDTO> list = new ArrayList<CompanyDTO>();
		try {
			while(rs.next()){
				list.add(convertObject(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 
	 * @param rs
	 * @return
	 */
	private CompanyDTO convertObject(ResultSet rs){
		CompanyDTO company = new CompanyDTO();
		try {
			company.setCode(rs.getString("code"));
			company.setCompanyName(rs.getString("companyName"));
			company.setRegistryDate(rs.getDate("registryDate"));
                        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return company;
	}
}
