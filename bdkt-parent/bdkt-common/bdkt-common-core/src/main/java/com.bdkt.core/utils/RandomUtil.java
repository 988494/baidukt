package com.bdkt.core.utils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

/**
 * 随机数 工具类
 */
public class RandomUtil {
	private static final String src_number = "0123456789";
	private static final String src_upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	private static final String weixin_open_char = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_";

	
	/**
     * 返回随机数
     * @param list 备选号码
     * @param selected 备选数量
     * @return
     */
    public static List getRandomNum(List list, int selected) {
        List reList = new ArrayList();
 
        Random random = new Random();
        // 先抽取，备选数量的个数
        if (list.size() >= selected) {
            for (int i = 0; i < selected; i++) {
                // 随机数的范围为0-list.size()-1;
                int target = random.nextInt(list.size());
                reList.add(list.get(target));
                list.remove(target);
            }
        } else {
            selected = list.size();
            for (int i = 0; i < selected; i++) {
                // 随机数的范围为0-list.size()-1;
                int target = random.nextInt(list.size());
                reList.add(list.get(target));
                list.remove(target);
            }
        }
       
        return reList;
    }

	public static String get(int size) {
		StringBuffer r = new StringBuffer(size);
		String src = src_number + src_upper;
		for (int i = 0; i < size; i++) {
			r.append(getRandomChar(src));
		}
		return r.toString();
	}
	
	public static String getRandomNum(int size) {
		StringBuffer r = new StringBuffer(size);
		String src = src_number;
		for (int i = 0; i < size; i++) {
			r.append(getRandomChar(src));
		}
		return r.toString();
	}

	private static final String getRandomChar(String src) {
		if (null == src || "".equals(src)) {
			return "";
		}
		return String.valueOf((src.charAt((int) (Math.random() * src.length()))));
	}
	
	public static double getRandomDouble(double start,double end){
		return ArithmeticUtil.round(new Random().nextDouble()*(end-start)+start, 2);
	}
	
	public static double getRandomDouble(double start,double end,int scale){
		return ArithmeticUtil.round(new Random().nextDouble()*(end-start)+start, scale);
	}
	
	public static float getRandomFloat(float start,float end){
		return ArithmeticUtil.round(new Random().nextFloat()*(end-start)+start, 2);
	}
	
	public static float getRandomFloat(float start,float end,int scale){
		return ArithmeticUtil.round(new Random().nextFloat()*(end-start)+start, scale);
	}
	
	public static int getRandomInt(int start,int end){
		return new Random().nextInt(end-start+1)+start;
	}
	
	public static long getRandomLong(long start,long end){
		long rtn = start + (long)(Math.random() * (end - start));  
		//如果返回的是开始时间和结束时间，则递归调用本函数查找随机值  
		if(rtn == start || rtn == end){  
			return getRandomLong(start,end);  
		}  
		return rtn;
	}
	
	public static Date getRandomDate(String beginDate, String endDate) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date start = format.parse(beginDate);// 构造开始日期
			Date end = format.parse(endDate);// 构造结束日期
			// getTime()表示返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。
			if (start.getTime() >= end.getTime()) {
				return null;
			}
			long date = getRandomLong(start.getTime(), end.getTime());
			return new Date(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getRandomDate(Date beginDate, Date endDate) {
		try {
			// getTime()表示返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。
			if (beginDate.getTime() >= endDate.getTime()) {
				return null;
			}
			long date = getRandomLong(beginDate.getTime(), endDate.getTime());
			return new SimpleDateFormat("yyyy-MM-dd").format(new Date(date));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date getRandomDateTime(Date beginDate, Date endDate) {
		try {
			// getTime()表示返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。
			if (beginDate.getTime() >= endDate.getTime()) {
				return null;
			}
			long date = getRandomLong(beginDate.getTime(), endDate.getTime());
			return new Date(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	/**
	 * 按照概率集合参数随机对象
	 * // map 记录每个图片的概率
	    Map<String, Float> map = new HashMap<String, Float>();
	    // a,b,c的概率和也可以不为1，它对自动把概率值当比例值去运行。
	    map.put("a", 0.5f);
	    map.put("b", 0.4f);
	    map.put("c", 0.1f);
	 * @param map
	 * @return
	 */
	public static String probability(Map<String, Float> map) {
	    Float total = 0f;
	    Map<Float, String> tempMap = new LinkedHashMap<Float, String>(); // 使用有序的map集合以保证key值是递增的
	    Iterator<Entry<String, Float>> it = map.entrySet().iterator();
	    while (it.hasNext()) {
	        Entry<String, Float> entry = it.next();
	        total += entry.getValue();
	        tempMap.put(total, entry.getKey());
	    }
	    float index = new Random().nextFloat()*total;
	    Iterator<Entry<Float, String>> tempIt = tempMap.entrySet().iterator();
	    while (tempIt.hasNext()) {
	        Entry<Float, String> next = tempIt.next();
	        if (index < next.getKey()) {
	            return next.getValue();
	        }
	    }
	    return null;
	}
	
	public static Enum probabilityEnum(Map<Enum, Float> map) {
	    Float total = 0f;
	    Map<Float, Enum> tempMap = new LinkedHashMap<Float, Enum>(); // 使用有序的map集合以保证key值是递增的
	    Iterator<Entry<Enum, Float>> it = map.entrySet().iterator();
	    while (it.hasNext()) {
	        Entry<Enum, Float> entry = it.next();
	        total += entry.getValue();
	        tempMap.put(total, entry.getKey());
	    }
	    float index = new Random().nextFloat()*total;
	    Iterator<Entry<Float, Enum>> tempIt = tempMap.entrySet().iterator();
	    while (tempIt.hasNext()) {
	        Entry<Float, Enum> next = tempIt.next();
	        if (index < next.getKey()) {
	            return next.getValue();
	        }
	    }
	    return null;
	}
	
	//总数total,分成divide份 每份随机
	public static int[] divideArray(int divide, int total) {
		int[] result = new int[divide];
		Random random = new Random();
        //拿到divide个随机数，可以做个池什么的每次取divide个来提升效率
        List<Double> r = new ArrayList<>();
        for (int i = 0; i < divide; i++) {
            r.add(random.nextDouble());
        }
        //排序
        Collections.sort(r,new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return o1 < o2 ? -1 : 1;
            }
        });
        //用这divide个随机数来打断一个数，来取得divide份分解之后的数
        List<Integer> out = new ArrayList<>();
        int last = 0;
        for (int i = 0; i < divide-1; i++) {
            int c = (int) (r.get(i) * total);
            result[i]=c-last;
            last = c;
        }
        result[divide-1]=total - last;
		return result;
	}
	
	public static LinkedHashMap<String, Integer> rateMapToNumMap(Map<String, Double> map, int count) {
		if(!(map instanceof LinkedHashMap)){
			return null;
		}
		LinkedHashMap<String, Integer> resultMap = new LinkedHashMap<String, Integer>();
		int i = 0, sum = 0;
		for (Entry<String, Double> entry : map.entrySet()) {
			i++;
			int num = 0;
			if (i == map.size()) {
				num = count - sum;
			} else {
				num = (int) (entry.getValue() * count);
			}
			sum += num;
			resultMap.put(entry.getKey(), num);
		}
		return resultMap;
	}
	
	public static void random(int n, int L){
        Random rand = new Random();
        int temp = L;
        for(int i = 0, j; i < n-1; i++){
            j = rand.nextInt(temp-1) + 1;
            temp -= j;
            System.out.println(j);
        }
        System.out.println(temp);
    }
}