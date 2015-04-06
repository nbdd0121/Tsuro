package phisighackers.util;

import java.util.Objects;

public class Tuple {

	public static class _2<T1, T2> extends Tuple{
		public final T1 _1;
		public final T2 _2;
		
		public _2(T1 _1, T2 _2){
			this._1=_1;
			this._2=_2;
		}
		
		public String toString(){
			return "("+_1 +", "+_2+")";
		}
		
		public boolean equals(Object obj){
			if(obj instanceof _2){
				_2<?, ?> tp=(_2<?, ?>)obj;
				return Objects.equals(tp._1, _1)&&Objects.equals(tp._2, _2);
			}
			return false;
		}
		
	}
	
	public static <T1, T2> _2<T1, T2> as(T1 _1, T2 _2){
		return new _2<>(_1, _2);
	}
	
	
	
}
