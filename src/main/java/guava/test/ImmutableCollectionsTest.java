package guava.test;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import javafeatures.util.PrintUtil;

import java.util.*;

/**
 * 不可修改的集合: ImmutableSet ImmutableMap
 * 注意：每个Guava immutable集合类的实现都拒绝null值
 *
 * @author panws
 * @since 2017-06-23
 */
public class ImmutableCollectionsTest {

	public static void main(String[] args) {

		Set<String> set = new HashSet<>(Arrays.asList(new String[] { "RED", "GREEN" }));

		/**
		 *不安全：在原来的 set 上 add 或者 remove 元素,unmodifiableSet 也会被 add 或者 remove 元素
		 */
		Set<String> unmodifiableSet = Collections.unmodifiableSet(set);
		set.remove("RED");
		PrintUtil.println(unmodifiableSet.size());

		/**
		 *ImmutableSet
		 */
		//直接生成
		ImmutableSet<String> immutableSet1 = ImmutableSet.of("RED", "GREEN");
		//从set中拷贝,这种做法也有Collections.unmodifiableSet的问题
		ImmutableSet<String> immutableSet2 = ImmutableSet.copyOf(set);
		set.remove("RED");
		PrintUtil.println(immutableSet1);
		PrintUtil.println(immutableSet2);
		//immutableSet1.add("RED");//throw: java.lang.UnsupportedOperationException
		ImmutableSet.Builder<String> builder1 = ImmutableSet.builder();
		ImmutableSet<String> immutableSet3 = builder1.add("YELLOW").addAll(set).build();
		PrintUtil.println(immutableSet3);

		/**
		 * ImmutableMap
		 */
		Map<String, String> map1 = new HashMap<>(5);
		Map<Integer, Integer> map2 = new HashMap<>(5);
		Map<String, Integer> map3 = new HashMap<>(5);
		map1.put("1", "a");
		map2.put(1, 1);
		map3.put("b", 1);
		ImmutableMap.Builder builder2 = ImmutableMap.builder();
		@SuppressWarnings("unchecked") ImmutableMap<?, ?> immutableMap = builder2.putAll(map1).putAll(map2).putAll(map3)
				.build();
		PrintUtil.println(immutableMap);
	}
}
