package stock.servlet;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.servlets.EventSource;
import org.eclipse.jetty.servlets.EventSourceServlet;

import stock.event.AlertEventSource;

public class StockEventServlet extends EventSourceServlet {

	@Override
	protected EventSource newEventSource(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		return new AlertEventSource();
	}

}
