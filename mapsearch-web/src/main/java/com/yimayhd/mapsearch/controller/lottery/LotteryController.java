//package com.pajk.snscenter.controller.lottery;
//
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.pajk.snscenter.errorcode.ErrorCode;
//import net.pocrd.entity.CallerInfo;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.pajk.snscenter.client.LotteryService;
//import com.pajk.snscenter.entity.PromotionConfig;
//import com.pajk.snscenter.entity.lottery.LotteryResult;
//import com.pajk.snscenter.client.result.ProResult;
//
///**
// * @author: jiangzhihu@jk.cn
// * @date: 2015年4月1日 下午4:19:05
// *
// */
//@Component
//@RequestMapping("/lottery")
//public class LotteryController {
//	private static final Logger logger = LoggerFactory.getLogger(LotteryController.class);
//
//	@Autowired
//	private LotteryService lotteryServiceImpl;
//
//	@ResponseBody
//    @RequestMapping(value="/draw")
//	public ProResult<LotteryResult> draw(@RequestParam("activityId") long activityId,HttpServletRequest request, HttpServletResponse response){
//
//		CallerInfo callerInfo =(CallerInfo)request.getAttribute(PromotionConfig.KEY_OF_CALLER);
//		long userId=callerInfo.uid;
//
//
//		//解决跨域问题
//		response.addHeader("Access-Control-Allow-Origin", this.getReferer(request));
//		response.addHeader("Access-Control-Allow-Credentials", "true");
//		response.addHeader("Access-Control-Allow-Headers", "application/json; charset=UTF-8");
//        response.addHeader("Access-Control-Allow-Methods", "OPTIONS,GET,POST");
//        response.addHeader("Allow", "OPTIONS,GET,POST");
//		logger.info("userId={}, activityId={}",userId,activityId);
//		ProResult<LotteryResult> proResult;
//		try {
//			proResult = lotteryServiceImpl.draw(userId, activityId);
//		} catch (Exception e) {
//			proResult = new ProResult<LotteryResult>();
//			proResult.setResultCode(ErrorCode.SYSTEM_EXCEPTION_CODE);
//		}
//		return proResult;
//	}
//
//	private String getReferer(HttpServletRequest request){
//		String referer = request.getHeader("Referer");
//		//String referer = "http://gc.pre.jk.cn/health_path/index.html";
//		logger.info("getReferer referer="+referer);
//		String regex = "http://[^/]*";
//		Pattern p = Pattern.compile(regex);
//		Matcher m = p.matcher(referer);
//		if(m.find()){
//			String prefixReferer = m.group(0);
//			logger.info("getReferer prefixReferer="+prefixReferer);
//			return prefixReferer;
//
//		}
//		logger.info("getReferer prefixReferer=*");
//		return "*";
//	}
//}
