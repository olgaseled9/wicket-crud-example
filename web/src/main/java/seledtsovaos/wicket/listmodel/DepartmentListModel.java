package seledtsovaos.wicket.listmodel;

import java.util.List;

import org.apache.wicket.model.LoadableDetachableModel;

import seledtsovaos.dto.DepartmentDto;
import seledtsovaos.service.impl.DepartmentServiceImpl;

/**
 * Reads  and returns list of departments.
 */
public class DepartmentListModel extends LoadableDetachableModel<List<DepartmentDto>> {

    @Override
    protected List<DepartmentDto> load() {
        return new DepartmentServiceImpl().getAll();
    }
}
