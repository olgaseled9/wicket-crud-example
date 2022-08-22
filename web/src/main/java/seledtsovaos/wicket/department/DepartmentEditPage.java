package seledtsovaos.wicket.department;

import org.apache.wicket.markup.html.GenericWebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.PatternValidator;
import org.apache.wicket.validation.validator.StringValidator;

import seledtsovaos.dto.DepartmentDto;
import seledtsovaos.service.impl.DepartmentServiceImpl;

/**
 * Department's add or edit page.
 */
public class DepartmentEditPage extends GenericWebPage<DepartmentDto> {

    /**
     * Regular expressions for input validation to position on employee update page.
     */
    private static final String PATTERN_TO_UPDATE_DEPARTMENT_PAGE = "[A-Za-zА-Яа-яЁё\\s0-9'-]*";

    public DepartmentEditPage(IModel<DepartmentDto> model) {
        super(model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new FeedbackPanel("feedbackMessage"));
        add(newForm("form"));
    }

    private Form<DepartmentDto> newForm(String id) {
        IModel<DepartmentDto> model = getModel();
        Form<DepartmentDto> form = new Form<>(id);

        form.add(
            new TextField<String>("name", PropertyModel.of(model, "name"))
                .add(StringValidator.lengthBetween(1, 200))
                .add(new PatternValidator(PATTERN_TO_UPDATE_DEPARTMENT_PAGE))
                .setRequired(true));

        form.add(
            new Button("submit") {
                @Override
                public void onSubmit() {
                    saveOrUpdateDepartment();
                    setResponsePage(DepartmentListPage.class);
                }
            });

        form.add(
            new Link<Void>("cancel") {
                @Override
                public void onClick() {
                    setResponsePage(DepartmentListPage.class);
                }
            });
        add(form);
        return form;
    }

    private void saveOrUpdateDepartment() {
        DepartmentDto departmentDto = getModelObject();
        if (departmentDto.isNew()) {
            new DepartmentServiceImpl().add(departmentDto);
        }
        else {
            new DepartmentServiceImpl().update(departmentDto);
        }
    }
}
