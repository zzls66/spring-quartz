package com.zzl.web.util;//package com.cnhutong.cs.mobile.web.util;
//
//import com.cnhutong.staff.entity.SourceData;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// *
// * @author wilson
// *
// */
//public class StringUtils {
//
//	/**
//	 * split string into a long list
//	 *
//	 * @param str
//	 * @param seperator
//	 * @return
//	 */
//	public static List<Long> splitToLongList(String str, String seperator) {
//
//		List<Long> results = new ArrayList<>();
//
//		String[] subs = org.apache.commons.lang3.StringUtils.split(str, seperator);
//
//		if(subs != null) {
//			for(String sub : subs) {
//				results.add(Long.valueOf(sub.trim()));
//			}
//		}
//
//		return results;
//	}
//
//	/**
//	 * split string into a integer list
//	 *
//	 * @param str
//	 * @param seperator
//	 * @return
//	 */
//	public static List<Integer> splitToIntegerList(String str, String seperator) {
//
//		List<Integer> results = new ArrayList<>();
//
//		String[] subs = org.apache.commons.lang3.StringUtils.split(str, seperator);
//
//		if(subs != null) {
//			for(String sub : subs) {
//				results.add(Integer.valueOf(sub.trim()));
//			}
//		}
//
//		return results;
//	}
//
//	public static List<Map<String, Object>> sourceToMaps(List<SourceData> sourceDatas){
//		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
// 		for(SourceData sourceData : sourceDatas){
//   	 		Map<String, Object> source = new HashMap<String, Object>();
//   	 		source.put("value", sourceData.getId().toString());
//   	 		source.put("title", sourceData.getName());
//       		source.put("ordinal", sourceData.getLevel().ordinal());
//       		source.put("fid", sourceData.getSourceFid());
//       		list.add(source);
//   		}
//		return list;
//	}
//
//	public static List<Map<String, Object>> sourceToMaps(List<SourceData> sourceDatas,String level){
//		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
//		for(SourceData sourceData : sourceDatas){
//			Map<String, Object> source = new HashMap<String, Object>();
//			source.put("value", sourceData.getId());
//			source.put("title", sourceData.getName());
//			source.put("level", level);
//			source.put("ordinal", sourceData.getLevel().ordinal());
//			source.put("fid", sourceData.getSourceFid());
//			list.add(source);
//		}
//		return list;
//	}
//
//	public static Map<Long, List<Map<String, Object>>> sourceToMap(List<SourceData> sourceDatas){
//
//		Map<Long, List<Map<String, Object>>> map = new HashMap<Long, List<Map<String,Object>>>();
// 		for(SourceData sourceData : sourceDatas){
//   	 		Map<String, Object> source = new HashMap<String, Object>();
//   	 		source.put("value", sourceData.getId().toString());
//   	 		source.put("title", sourceData.getName());
//       		source.put("ordinal", sourceData.getLevel().ordinal());
//       		source.put("fid", sourceData.getSourceFid());
//       		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
//       		if (map.get(sourceData.getSourceFid()) != null) {
//       			list = map.get(sourceData.getSourceFid());
//			}
//       		list.add(source);
//       		map.put(sourceData.getSourceFid(), list);
//   		}
//		return map;
//	}
//
//	public static Map<Long, List<Map<String, Object>>> sourceToMap(List<SourceData> sourceDatas,String level){
//
//		Map<Long, List<Map<String, Object>>> map = new HashMap<Long, List<Map<String,Object>>>();
//		for(SourceData sourceData : sourceDatas){
//			Map<String, Object> source = new HashMap<String, Object>();
//			source.put("value", sourceData.getId());
//			source.put("title", sourceData.getName());
//			source.put("level", level);
//			source.put("ordinal", sourceData.getLevel().ordinal());
//			source.put("fid", sourceData.getSourceFid());
//			List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
//			if (map.get(sourceData.getSourceFid()) != null) {
//				list = map.get(sourceData.getSourceFid());
//			}
//			list.add(source);
//			map.put(sourceData.getSourceFid(), list);
//		}
//		return map;
//	}
//}
