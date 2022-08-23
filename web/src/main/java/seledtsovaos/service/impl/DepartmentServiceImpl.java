package seledtsovaos.service.impl;

import java.util.ArrayList;
import java.util.List;

import seledtsovaos.converters.DepartmentConverter;
import seledtsovaos.converters.impl.DepartmentConverterImpl;
import seledtsovaos.dao.DepartmentDao;
import seledtsovaos.dao.impl.DepartmentDaoImpl;
import seledtsovaos.dto.DepartmentDto;
import seledtsovaos.exception.DaoException;
import seledtsovaos.exception.ServiceException;
import seledtsovaos.model.Department;
import seledtsovaos.service.DepartmentService;

/**
 * Implementation of {@link DepartmentService} interface.
 */
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentDao departmentDao = new DepartmentDaoImpl();
    private final DepartmentConverter converter = new DepartmentConverterImpl();

    @Override
    public List<DepartmentDto> getAll() {
        try {
            List<Department> departmentList = departmentDao.getAll();
            List<DepartmentDto> departmentDtoList = new ArrayList<>();
            for (Department department : departmentList) {
                departmentDtoList.add(converter.convertToDto(department));
            }
            return departmentDtoList;
        }
        catch (DaoException e) {
            throw new ServiceException("Not able to get all departments. ", e);
        }
    }

    @Override
    public DepartmentDto findById(Long id) {
        try {
            return converter.convertToDto(departmentDao.findById(id));
        }
        catch (DaoException e) {
            throw new ServiceException("Department is not found with id = " + id, e);
        }
    }

    @Override
    public void add(DepartmentDto departmentDto) {
        try {
            Department department = converter.convertBack(departmentDto);
            departmentDao.add(department);
            departmentDto.setId(department.getId());
        }
        catch (DaoException e) {
            throw new ServiceException("Not able to add department : " + departmentDto, e);
        }
    }


    @Override
    public void update(DepartmentDto departmentDto) {
        try {
            departmentDao.update(converter.convertBack(departmentDto));
        }
        catch (DaoException e) {
            throw new ServiceException("Department is not updated : " + departmentDto, e);
        }
    }

    @Override
    public void removeById(Long id) {
        try {
            departmentDao.removeById(id);
        }
        catch (DaoException e) {
            throw new ServiceException("Cannot remove department with id = " + id, e);
        }
    }
}
