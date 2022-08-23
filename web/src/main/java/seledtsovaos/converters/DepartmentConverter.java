package seledtsovaos.converters;

import seledtsovaos.dto.DepartmentDto;
import seledtsovaos.model.Department;

/**
 * Converts a container of Department entity data {@link Department}
 * into a container for representing data {@link DepartmentDto}.
 */
public interface DepartmentConverter {

    DepartmentDto convertToDto(Department department);

    Department convertBack(DepartmentDto departmentDto);
}
