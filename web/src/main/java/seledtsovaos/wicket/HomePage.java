package seledtsovaos.wicket;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

import seledtsovaos.wicket.department.DepartmentListPage;

/**
 * Application start page.
 */
public class HomePage extends WebPage {

    public HomePage() {
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new Link<Void>("departmentLink") {
                @Override
                public void onClick() {
                    setResponsePage(DepartmentListPage.class);
                }
            }
        );
    }

}
