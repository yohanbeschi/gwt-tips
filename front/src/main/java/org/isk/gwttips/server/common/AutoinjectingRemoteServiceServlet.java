package org.isk.gwttips.server.common;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * Expose Spring Beans to GWT app
 * From http://pgt.de/2009/07/17/non-invasive-gwt-and-spring-integration-reloaded/
 */
public class AutoinjectingRemoteServiceServlet extends RemoteServiceServlet {

    private static final long serialVersionUID = 3219648202530817074L;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        final ServletContext servletContext = config.getServletContext();
        final WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        final AutowireCapableBeanFactory beanFactory = ctx.getAutowireCapableBeanFactory();
        beanFactory.autowireBean(this);
    }
}
