package nl.mcemperor.commons.lang.reflect;

/**
 * Utility class for operations on class types.
 *
 * @author Maurits de Jong
 * @since 2017-10-10
 */
public class ClassUtils {
	
	/**
	 * A private constructor, because this is a utility class.
	 */
	private ClassUtils() { }
	
	/**
	 * Whether the given class is primitive or not. The primitive types are {@code boolean}, {@code byte},
	 * {@code short}, {@code int}, {@code long}, {@code char}, {@code float} and {@code double}. Their classes are
	 * available through the class literal.
	 * 
	 * @param clazz The class to inspect.
	 * @return {@code true} if the given class represents a primitive type, {@code false} otherwise.
	 */
	public static boolean isPrimitive(Class<?> clazz) {
		return
			clazz == boolean.class ||
			clazz == byte.class ||
			clazz == short.class ||
			clazz == int.class ||
			clazz == long.class ||
			clazz == char.class ||
			clazz == float.class ||
			clazz == double.class;
	}
	
	/**
	 * Whether the given type is a wrapper class. A wrapper class is a class to wrap one of the eight primitive data
	 * types: {@code boolean}, {@code byte}, {@code short}, {@code int}, {@code long}, {@code character}, {@code float}
	 * and {@code double}.
	 * 
	 * @param clazz The class to inspect.
	 * @return Whether the given class is a primitive wrapper class.
	 */
	public static boolean isPrimitiveWrapper(Class<?> clazz) {
		return
			clazz == Boolean.class ||
			clazz == Byte.class ||
			clazz == Short.class ||
			clazz == Integer.class ||
			clazz == Long.class ||
			clazz == Character.class ||
			clazz == Float.class ||
			clazz == Double.class;
	}
	
	/**
	 * Returns the boxed type of the specified primitive type. If the specified class is not a primitive type, then that
	 * particular class is simply returned.
	 * 
	 * @param clazz The type of a primitive.
	 * @return The corresponding wrapper type, or the specified class if that's not a primitive type.
	 */
	public static Class<?> boxed(Class<?> clazz) {
		if (clazz == boolean.class) {
			return Boolean.class;
		}
		else if (clazz == byte.class) {
			return Byte.class;
		}
		else if (clazz == short.class) {
			return Short.class;
		}
		else if (clazz == int.class) {
			return Integer.class;
		}
		else if (clazz == long.class) {
			return Long.class;
		}
		else if (clazz == char.class) {
			return Character.class;
		}
		else if (clazz == float.class) {
			return Float.class;
		}
		else if (clazz == double.class) {
			return Double.class;
		}
		else {
			return clazz;
		}
	}
	
	/**
	 * Returns the unboxed type of the specified primitive wrapper type.
	 * 
	 * @param clazz The type of a primitive wrapper.
	 * @return The corresponding primitive type.
	 */
	public static Class<?> unboxed(Class<?> clazz) {
		if (clazz == Boolean.class) {
			return boolean.class;
		}
		else if (clazz == Byte.class) {
			return byte.class;
		}
		else if (clazz == Short.class) {
			return short.class;
		}
		else if (clazz == Integer.class) {
			return int.class;
		}
		else if (clazz == Long.class) {
			return long.class;
		}
		else if (clazz == Character.class) {
			return char.class;
		}
		else if (clazz == Float.class) {
			return float.class;
		}
		else if (clazz == Double.class) {
			return double.class;
		}
		else {
			return clazz;
		}
	}
}
