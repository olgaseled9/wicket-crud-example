package seledtsovaos.wicket;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;


/**
 * Error page, prints error's stacktrace.
 */
public class ErrorPage extends WebPage {

    private Throwable throwable;

    public ErrorPage(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new Label("error", "Opps. We can't seem the page you are looking for!!!"));
        add(new Label("stackTrace", getStackTrace(throwable)));
    }

    @Override
    public boolean isVersioned() {
        return false;
    }

    @Override
    public boolean isErrorPage() {
        return true;
    }

    @SuppressWarnings("checkstyle:RegexpSingleline")
    private String getStackTrace(final Throwable throwable) {
        StringWriter errors = new StringWriter();
        throwable.printStackTrace(new PrintWriter(errors));
        return errors.toString();
    }
}
