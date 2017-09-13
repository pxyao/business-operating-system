//import org.apache.commons.lang3.StringUtils;
//import org.junit.Test;
//
//import per.bos.utils.PinYin4jUtils;
//
//public class test {
//	@Test
//	public void test1(){
//		String provice = "河北省";
//		
//		String city = "石家庄市";
//		
//		String district = "桥西区";
//		
//		provice = provice.substring(0,provice.length()-1);
//		
//		city = city.substring(0,city.length()-1);
//		
//		district = district.substring(0,district.length()-1);
//		
//		String info = provice+city+district;
//		
//		String[] headByString = PinYin4jUtils.getHeadByString(info);
//
//		System.out.println(headByString);
//		
//		String shortcode = StringUtils.join(headByString);
//		
//		String citycode = PinYin4jUtils.hanziToPinyin(city,"");
//		
//		System.out.println(citycode);
//	}
//}
