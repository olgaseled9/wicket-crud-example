package seledtsovaos.wicket;

import java.util.Locale;

import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.core.request.handler.PageProvider;
import org.apache.wicket.core.request.handler.RenderPageRequestHandler;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.cycle.IRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.settings.ExceptionSettings;

/**
 * Configures web application.
 */
public class WicketApplication extends WebApplication {

    public WicketApplication() {
    }

    @Override
    public Class<? extends Page> getHomePage() {
        return HomePage.class;
    }

    @Override
    public void init() {
        super.init();
        getCspSettings().blocking().disabled();
        getCspSettings().blocking().disabled();
        getApplicationSettings().setAccessDeniedPage(ErrorPage.class);
        getApplicationSettings().setInternalErrorPage(ErrorPage.class);
        getApplicationSettings().setPageExpiredErrorPage(ErrorPage.class);
        getExceptionSettings().setUnexpectedExceptionDisplay(ExceptionSettings.SHOW_INTERNAL_ERROR_PAGE);
        mountPage("/error", ErrorPage.class);

        getRequestCycleListeners().add(new IRequestCycleListener() {
            @Override
            public IRequestHandler onException(RequestCycle cycle, Exception e) {
                return new RenderPageRequestHandler(new PageProvider(new ErrorPage(e)));
            }
        });
    }

    @Override
    public Session newSession(Request request, Response response) {
        Session session = super.newSession(request, response);
        session.setLocale(new Locale("ru", "RU"));
        return session;
    }
}
