package ai.yunxi.delay.impl;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ai.yunxi.delay.DelayMessageService;
import ai.yunxi.utils.CalendarUtils;
import ai.yunxi.utils.MQProperties;

/**
 * 
 * @author 小五老师-云析学院
 * @createTime 2018年11月11日 下午5:35:58
 * 
 */
@Service("mqDelayMessageServiceImpl")
public class MQDelayMessageServiceImpl implements DelayMessageService {

	@Autowired
	private AmqpTemplate amqpTemplate;
	
	public void product() {
		String orderId = "1010101";
		for (int i = 0; i < 10; i++) {
			//创建订单
			amqpTemplate.convertAndSend(MQProperties.EXCHANGE_NAME, MQProperties.ROUTE_KEY, orderId+i);

			System.out.println(CalendarUtils.getCurrentTimeByStr(0)+" 生成了一个订单，订单ID："+orderId+i);
			if(i%3==0){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void consumer() {
		
	}

}
