package com.xz.base.utils;


import org.apache.commons.lang3.StringUtils;


public class VehicleConfigUtil {
	
	public static Integer skyLightNum(String power_sunroof_value,String panoramic_sunroof_value){
		Integer power_sunroof_value_num = 0;
		Integer panoramic_sunroof_value_num = 0;
		if(StringUtils.isNotBlank(power_sunroof_value)&&!"无".equals(power_sunroof_value)){
			power_sunroof_value_num = 1;
		}
		if(StringUtils.isNotBlank(panoramic_sunroof_value)&&!"无".equals(panoramic_sunroof_value)){
			panoramic_sunroof_value_num = 1;
		}
		return power_sunroof_value_num+panoramic_sunroof_value_num;
	}
	
	public static Integer airBagNum(String main_copilot_seat_airbag_value,String front_rear_airbag_value,
			String front_rear_head_airbag_value,String knee_airbag_value){
		Integer main_copilot_seat_airbag_value_num = 0;
		Integer front_rear_airbag_value_num = 0;
		Integer front_rear_head_airbag_value_num = 0;
		Integer knee_airbag_value_num = 0;
		if(StringUtils.isNotBlank(main_copilot_seat_airbag_value)&&!"无".equals(main_copilot_seat_airbag_value)){
			if(main_copilot_seat_airbag_value.contains("/")){
				String[] main_copilot_seat_airbag_value_arr = main_copilot_seat_airbag_value.split("/");
				Integer temp_main_copilot_seat_airbag_value_num = 0;
				if(StringUtils.isNotBlank(main_copilot_seat_airbag_value_arr[0])&&!main_copilot_seat_airbag_value_arr[0].contains("无")){
					temp_main_copilot_seat_airbag_value_num ++;
				}
				if(StringUtils.isNotBlank(main_copilot_seat_airbag_value_arr[1])&&!main_copilot_seat_airbag_value_arr[1].contains("无")){
					temp_main_copilot_seat_airbag_value_num ++;
				}
				main_copilot_seat_airbag_value_num = temp_main_copilot_seat_airbag_value_num;
			}else{
				main_copilot_seat_airbag_value_num = 2;
			}
		}
		
		if(StringUtils.isNotBlank(front_rear_airbag_value)&&!"无".equals(front_rear_airbag_value)){
			if(front_rear_airbag_value.contains("/")){
				String[] front_rear_airbag_value_arr = front_rear_airbag_value.split("/");
				Integer temp_front_rear_airbag_value_num = 0;
				if(StringUtils.isNotBlank(front_rear_airbag_value_arr[0])&&!front_rear_airbag_value_arr[0].contains("无")){
					temp_front_rear_airbag_value_num ++;
				}
				if(StringUtils.isNotBlank(front_rear_airbag_value_arr[1])&&!front_rear_airbag_value_arr[1].contains("无")){
					temp_front_rear_airbag_value_num ++;
				}
				front_rear_airbag_value_num = temp_front_rear_airbag_value_num;
			}else{
				front_rear_airbag_value_num = 2;
			}
		}
		
		if(StringUtils.isNotBlank(front_rear_head_airbag_value)&&!"无".equals(front_rear_head_airbag_value)){
			if(front_rear_head_airbag_value.contains("/")){
				String[] front_rear_head_airbag_value_arr = front_rear_head_airbag_value.split("/");
				Integer temp_front_rear_head_airbag_value_num = 0;
				if(StringUtils.isNotBlank(front_rear_head_airbag_value_arr[0])&&!front_rear_head_airbag_value_arr[0].contains("无")){
					temp_front_rear_head_airbag_value_num ++;
				}
				if(StringUtils.isNotBlank(front_rear_head_airbag_value_arr[1])&&!front_rear_head_airbag_value_arr[1].contains("无")){
					temp_front_rear_head_airbag_value_num ++;
				}
				front_rear_head_airbag_value_num = temp_front_rear_head_airbag_value_num;
			}else{
				front_rear_head_airbag_value_num = 2;
			}
		}
		
		if(StringUtils.isNotBlank(knee_airbag_value)&&!"无".equals(knee_airbag_value)){
			knee_airbag_value_num = 1;
		}

		return main_copilot_seat_airbag_value_num+front_rear_airbag_value_num+front_rear_head_airbag_value_num+knee_airbag_value_num;
		
	}
	
}
