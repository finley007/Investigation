package stock.event;

import java.io.IOException;

import org.eclipse.jetty.servlets.EventSource;

public class AlertEventSource implements EventSource {
	
	private Boolean isRunning = true;

	@Override
	public void onClose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onOpen(Emitter emitter) throws IOException {
		// TODO Auto-generated method stub
		query(emitter);
	}

	private void query(Emitter emitter) throws IOException {
//		DBConnector connector = new MysqlConnector();
//		DBQuery query = new DBQueryImpl();
//		query.setConn(connector);
//		emitter.comment("Start sending movement information.");
//		while(isRunning) {
//			try {
//				List<AlertVO> list = query.readAlert();
//				if (list != null && list.size() > 0) {
//					for (AlertVO alertVO : list) {
//						emitter.comment("");
//						emitter.data(alertVO.getStockCode() + ":" + alertVO.getMessage());
//						query.updateAlertStatus(alertVO.getAlertId(), StockConstants.ALERT_STATUS_NOTICED);
//					}
//				}
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				this.isRunning = false;
//			}
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//				break;
//			}
//		}
//		emitter.close(); 
	}
}
