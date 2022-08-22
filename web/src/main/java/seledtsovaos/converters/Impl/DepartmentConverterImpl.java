package seledtsovaos.converters.Impl;

import seledtsovaos.converters.DepartmentConverter;
import seledtsovaos.dto.DepartmentDto;
import seledtsovaos.model.Department;

public class DepartmentConverterImpl implements DepartmentConverter {

    @Override
    public DepartmentDto convertToDto(Department department) {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(department.getId());
        departmentDto.setName(department.getName());
        return departmentDto;
    }

    @Override
    public Department convertBack(DepartmentDto departmentDto) {
        Department department = new Department();
        department.setId(departmentDto.getId());
        department.setName(departmentDto.getName());
        return department;
    }
}
