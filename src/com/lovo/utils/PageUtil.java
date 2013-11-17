package com.lovo.utils;

import com.lovo.constant.Constant;


public class PageUtil {

	/**
	 * 获取当前开始行数 (默认每页显示CommonConst.PAGE_SIZE条)
	 * @param pageNo	当前页数
	 * @return	
	 */
	public static Integer getCurrentRow(Integer pageNo) {
		return pageNo != null ? (pageNo - 1) * Constant.PAGE_SIZE : null;
	}
	
	/**
	 * 获取当前开始行数 (每页显示pageSize条)
	 * @param pageNo	当前页数
	 * @return	
	 */
	public static Integer getCurrentRow(Integer pageNo, Integer pageSize) {
		return pageNo != null ? (pageNo - 1) * pageSize : null;
	}
	
	/**
	 * 获取总页数
	 * @param total
	 * @return
	 */
	public static Integer getTotalPage(int total){
		int pageTotal = 0;
		if (total > 0) {
			pageTotal = total / Constant.PAGE_SIZE;

			if (total % Constant.PAGE_SIZE != 0)
				pageTotal++;
		}
		return pageTotal;
	}
}
