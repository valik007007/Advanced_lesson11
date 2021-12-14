package ua.lviv.lgs.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.lviv.lgs.dao.UserDao;
import ua.lviv.lgs.domain.User;
import ua.lviv.lgs.utils.ConnectionUtils;

public class UserDaoImpl implements UserDao {

	private static String READ_ALL = "select * from user";
	private static String CREATE = "insert into user(`email`,`first_name`, `last_name`, `role`) values (?,?,?,?)";
	private static String READ_BY_ID = "select * from user where id =?";
	private static String UPDATE_BY_ID = "update user set email=?, first_name = ?, last_name=?, role=? where id = ?";
	private static String DELETE_BY_ID = "delete from user where id=?";

	private Connection connection;
	private PreparedStatement preparedStatement;

	public UserDaoImpl() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		connection = ConnectionUtils.openConnection();

	}

	@Override
	public User create(User user) {
		try {
			preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getFirstName());
			preparedStatement.setString(3, user.getLastName());
			preparedStatement.setString(4, user.getRole());

			preparedStatement.executeUpdate();

			ResultSet rs = preparedStatement.getGeneratedKeys();
			rs.next();
			user.setId(rs.getInt(1));
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return user;
	}

	@Override
	public User read(Integer id) {
		User user = null;

		try {
			preparedStatement = connection.prepareStatement(READ_BY_ID);
			preparedStatement.setInt(1, id);

			ResultSet rezult = preparedStatement.executeQuery();

			rezult.next();
			Integer userId = rezult.getInt("id");
			String email = rezult.getString("email");
			String firstName = rezult.getString("first_name");
			String lastName = rezult.getString("last_name");
			String role = rezult.getString("role");

			user = new User(userId, email, firstName, lastName, role);

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return user;
	}

	@Override
	public User update(User user) {
		try {
			preparedStatement = connection.prepareStatement(UPDATE_BY_ID);
			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getFirstName());
			preparedStatement.setString(3, user.getLastName());
			preparedStatement.setString(4, user.getRole());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return user;
	}

	@Override
	public void delete(Integer id) {
		try {
			preparedStatement = connection.prepareStatement(DELETE_BY_ID);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public List<User> readAll() {
		List<User> userRecords = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(READ_ALL);
			ResultSet rezult = preparedStatement.executeQuery();

			while (rezult.next()) {
				Integer userId = rezult.getInt("id");
				String email = rezult.getString("email");
				String firstName = rezult.getString("first_name");
				String lastName = rezult.getString("last_name");
				String role = rezult.getString("role");

				userRecords.add(new User(userId, email, firstName, lastName, role));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return userRecords;
	}

}
