package seledtsovaos.wicket.department;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import seledtsovaos.dto.DepartmentDto;
import seledtsovaos.service.impl.DepartmentServiceImpl;
import seledtsovaos.wicket.listmodel.DepartmentListModel;

/**
 * Department ListPage
 */
public class DepartmentListPage extends WebPage {

    public DepartmentListPage() {
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        addDepartmentList("departmentList");
        addLinkToNewDepartmentPage("addNewDepartment");
    }

    private void addLinkToNewDepartmentPage(String id) {
        add(new Link<Void>(id) {
            @Override
            public void onClick() {
                setResponsePage(new DepartmentEditPage(Model.of(new DepartmentDto())));
            }
        });
    }


    private void addDepartmentList(String id) {
        add(new ListView<>(id, new DepartmentListModel()) {
            @Override
            protected void populateItem(ListItem<DepartmentDto> item) {
                DepartmentDto departmentDto = item.getModelObject();
                item.add(new Label("name", PropertyModel.of(departmentDto, "name")));
                item.add(new Link<Void>("edit") {
                    @Override
                    public void onClick() {
                        setResponsePage(new DepartmentEditPage(Model.of(departmentDto)));
                    }
                });
                item.add(new Link<Void>("delete") {
                    @Override
                    public void onClick() {
                        new DepartmentServiceImpl().removeById(departmentDto.getId());
                        setResponsePage(DepartmentListPage.class);
                    }
                });
            }
        }.setOutputMarkupId(true));
    }
}
