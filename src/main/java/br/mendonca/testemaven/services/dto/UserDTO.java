package br.mendonca.testemaven.services.dto;

import br.mendonca.testemaven.dao.UserDAO;
import br.mendonca.testemaven.model.entities.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDTO {
	private String uuid;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	private String name;
	private String email;
	private int idade;
	private boolean status;

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


	public List<UserDTO> searchByName(String name) throws ClassNotFoundException, SQLException {
		UserDAO dao = new UserDAO();
		List<User> users = dao.searchByName(name);

		List<UserDTO> result = new ArrayList<>();
		for (User user : users) {
			result.add(UserDTO.userMapper(user));
		}
		return result;
	}



	public static UserDTO userMapper(User user) {
		UserDTO dto = new UserDTO();
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		dto.setIdade(user.getIdade());
		dto.setStatus(user.isStatus());
		
		return dto;
	}
}
