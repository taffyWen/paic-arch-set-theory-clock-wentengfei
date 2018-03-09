package com.paic.arch.interviews;


public class TimeConverterImpl implements TimeConverter {
	private final String TIME_TYPE_HOUR = "hour";
	private final String TIME_TYPE_MINUTE = "minute";
	private final String LIGHT_COLOR_RED = "R";
	private final String LIGHT_COLOR_YELLOW = "Y";
	private final String LIGHT_COLOR_EMPTY = "O";
	@Override
	public String convertTime(String aTime) {
		String[] strs = aTime.split(":");
		int hour = Integer.parseInt(strs[0]);
		int min = Integer.parseInt(strs[1]);
		int second = Integer.parseInt(strs[2]);
		
		StringBuffer lightResult = new StringBuffer(second%2==0?"Y":"O").append("\r\n");
		lightResult.append(getLightColor(hour,TIME_TYPE_HOUR,4,4))		//拼接小时灯色彩
					.append("\r\n")
					.append(getLightColor(min,TIME_TYPE_MINUTE,11,4));	//拼接分钟色彩
		String rslt = lightResult.toString();
		return rslt;
	}
	
	/**
	 * 为时间(小时、分钟)单位选择主色
	 * @param hourOrMin
	 * @return
	 */
	public  String chooseLightColor(String hourOrMin){
		String colorResult = null;
		if(hourOrMin.equals(TIME_TYPE_HOUR)){
			colorResult = LIGHT_COLOR_RED;
		}else if(hourOrMin.equals(TIME_TYPE_MINUTE)){
			colorResult = LIGHT_COLOR_YELLOW;
		}
		return colorResult;
	}
	
	/**
	 * 根据计算类型获得亮灯情况
	 * @param value   时间或者分钟的具体值
	 * @param hourOrMin 计算时间类型（小时/分钟）
	 * @param lightNum1 第一排灯总数量
	 * @param lightNum2 第二排灯总数量
	 * @return
	 */
	public String  getLightColor(int value,String hourOrMin,int lightNum1,int lightNum2){
		StringBuffer colorShow = new StringBuffer();
		int showLightNum1 = 0;	//第一行亮灯数量
		int showLightNum2 = 0;	//第二行亮灯数量
		if(value < 5){	//当小时或者分钟值<对应每种类型第一行灯的单位值5，得到单位为1的灯亮起的数量
			showLightNum2 = value;
		}else{
			showLightNum1 = value/5;			//得到单位为5灯亮的数量
			showLightNum2 = value%5;			//得到单位为1灯亮的数量
		}
		for (int i = 1; i <= lightNum1 ; i++) {
			 if(showLightNum1 >= i){
				 if(hourOrMin.equals(TIME_TYPE_MINUTE) && i%3 == 0){  //针对分钟计算时，亮灯序号为3的倍数时为红色
					 colorShow.append(LIGHT_COLOR_RED);	//分钟为15、30、45颜色
				 }else{
					 colorShow.append(chooseLightColor(hourOrMin));
				 }
			 }else{
				 colorShow.append(LIGHT_COLOR_EMPTY);
			 }
		}
		colorShow.append("\r\n");
		for (int i = 1; i <= lightNum2; i++) {		//获取第二行灯亮的数目
			if(showLightNum2 >= i){
				colorShow.append(chooseLightColor(hourOrMin));
			 }else{
				 colorShow.append(LIGHT_COLOR_EMPTY);
			 }
		}
		
		return colorShow.toString();
	}
	
	public static void main(String[] args) {
		TimeConverterImpl a = new TimeConverterImpl();
		String aTime = "00:00:00";
		String result = a.convertTime(aTime);
		System.out.println(result);
	}
}
