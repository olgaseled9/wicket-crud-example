package seledtsovaos.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import seledtsovaos.connection.ConnectionToDatabase;
import seledtsovaos.dao.DepartmentDao;
import seledtsovaos.dao.GenericDao;
import seledtsovaos.exception.DaoException;
import seledtsovaos.model.Department;

/**
 * Implementation of {@link DepartmentDao} interface.
 */
public class DepartmentDaoImpl implements DepartmentDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentDaoImpl.class);
    private static final String SELECT_ALL_FROM_DEPARTMENT_TABLE = "SELECT department_id, name FROM department";
    private static final String DELETE_FROM_DEPARTMENT_BY_ID = "DELETE FROM department WHERE department_id = ?";
    private static final String ADD_NEW_DEPARTMENT = "INSERT INTO department (name) VALUES (?)";
    private static final String SELECT_FROM_DEPARTMENT_TABLE_DEPARTMENT_BY_ID = "SELECT department_id, name" +
        "FROM department WHERE department_id=?";
    private static final String UPDATE_DEPARTMENT = "UPDATE department SET name=? WHERE department_id = ?";

    @Override
    public List<Department> getAll() throws DaoException {
        List<Department> departmentList = new ArrayList<>();
        try (Connection connection = ConnectionToDatabase.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_DEPARTMENT_TABLE)) {
            while (resultSet.next()) {
                departmentList.add(getDepartment(resultSet));
            }
            LOGGER.info("Successfully get all departments.");
        }
        catch (SQLException e) {
            throw new DaoException("Not able to get all departments.", e);
        }
        return departmentList;
    }

    @Override
    public Department findById(Long id) throws DaoException {
        Department department;
        try (Connection connection = ConnectionToDatabase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_DEPARTMENT_TABLE_DEPARTMENT_BY_ID)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.next()) {
                    throw new DaoException("Department not found with id = " + id);
                }
                department = getDepartment(resultSet);
                LOGGER.info("Department successfully found with id = " + id + " : " + department);
            }
        }
        catch (SQLException e) {
            throw new DaoException("Department not found with id = " + id, e);
        }
        return department;
    }

    @Override
    public void update(Department department) throws DaoException {
        try (Connection connection = ConnectionToDatabase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DEPARTMENT)) {
            preparedStatement.setString(1, department.getName());
            preparedStatement.setLong(2, department.getId());
            if (preparedStatement.executeUpdate() == 0) {
                throw new DaoException("Not able to update department : " + department);
            }
            LOGGER.info("Department successfully updated : " + department);
        }
        catch (SQLException e) {
            throw new DaoException("Not able to update department : " + department, e);
        }
    }

    @Override
    public void removeById(Long id) throws DaoException {
        try (Connection connection = ConnectionToDatabase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FROM_DEPARTMENT_BY_ID)) {
            preparedStatement.setLong(1, id);
            if (preparedStatement.executeUpdate() == 0) {
                throw new DaoException("Not able to delete department with id = " + id);
            }
            LOGGER.info("Successfully deleted department with id = " + id);
        }
        catch (SQLException e) {
            throw new DaoException("Not able to delete department with id = " + id, e);
        }
    }

    @Override
    public void add(Department department) {
        try (Connection connection = ConnectionToDatabase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_DEPARTMENT,
                 Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, department.getName());
            if (preparedStatement.executeUpdate() == 0) {
                throw new DaoException("Not able to save department : " + department);
            }
            department.setId(GenericDao.setGeneratedKey(preparedStatement));
            LOGGER.info("Successfully saved department : " + department);
        }
        catch (SQLException e) {
            throw new DaoException("Not able to save department : " + department, e);
        }
    }

    private Department getDepartment(ResultSet resultSet) throws SQLException {
        Department department = new Department();
        department.setId(resultSet.getLong("department_id"));
        department.setName(resultSet.getString("name"));
        return department;
    }
}
