package phisighackers.util;

import java.lang.reflect.Array;

public class MoreArrays {
	/**
	 * Duplicate a two-dimension array
	 * @param arr arrays to duplicate
	 * @return duplicated array
	 */
	public static <T> T[][] dup(T[][] arr){
		@SuppressWarnings("unchecked")
		T[][] ret=(T[][]) Array.newInstance(arr.getClass().getComponentType().getComponentType(), arr.length, arr[0].length);
		for(int i=0;i<ret.length;i++){
			for(int j=0;j<ret[0].length;j++){
				ret[i][j]=arr[i][j];
			}
		}
		return ret;
	}

	/**
	 * Check whether an array is totally empty
	 * @param arr
	 * @return
	 */
	public static <T> boolean isEmpty(T[] arr) {
		for(T t:arr){
			if(t!=null){
				return false;
			}
		}
		return true;
	}
}
