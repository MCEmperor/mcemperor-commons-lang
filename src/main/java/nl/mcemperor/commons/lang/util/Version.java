package nl.mcemperor.commons.lang.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import nl.mcemperor.commons.lang.builder.EqualsBuilder;
import nl.mcemperor.commons.lang.builder.HashCodeBuilder;
import nl.mcemperor.commons.lang.stream.Streams;

/**
 * The Version class represents a version with multiple version components, for example {@code 2.3.5}.
 *
 * @author Maurits de Jong
 * @version 1.2.0
 * @since 2015-03-26
 */
public class Version implements Comparable<Version> {
	
	/**
	 * The version number components.
	 */
	private final List<Integer> components;
	
	/**
	 * Constructs a Version instance with the given version number components.
	 * @param version The version components.
	 */
	public Version(int... version) {
		OptionalInt index = Streams.reversedIndices(Integers.boxed(version))
			.filter(i -> version[i] != 0)
			.findFirst();
		List<Integer> comps = Arrays.stream(version)
			.limit(index.getAsInt() + 1)
			.boxed()
			.collect(Collectors.toList());
		this.components = Collections.unmodifiableList(comps);
	}

	public List<Integer> getComponents() {
		return new ArrayList<>(this.components);
	}
	
	/**
	 * Constructs a Version instance with the given version.
	 * @param version The version number as a string.
	 */
	public Version(String version) {
		this(Arrays.asList(version.split("\\.")).stream()
			.mapToInt(Integer::valueOf)
			.toArray());
	}
	
	/**
	 * Checks if this object is equal to the given object.
	 * @param o The object to compare.
	 * @return Whether this instance is equal to the given object.
	 */
	@Override
	public boolean equals(Object o) {
		return new EqualsBuilder<>(this, o)
			.test(t -> t.components)
			.isEqual();
	}
	
	/**
	 * Returns the hash code for this object.
	 * @return The hash code as an int.
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder<>(this)
			.hash(t -> t.components)
			.code();
	}
	
	/**
	 * Compares this version with the given version.
	 * @param o The version to compare this version with.
	 * @return {@code 0} if the versions are equal, {@code -1} if this version is smaller (or lower) than the given
	 * version, and {@code 1} otherwise.
	 */
	@Override
	public int compareTo(Version o) {
		if (this == o) {
			return 0;
		}
		int max = Math.max(this.components.size(), o.components.size());
		for (int i = 0; i < max; i++) {
			int valueOfThis = (i < this.components.size() ? this.components.get(i) : 0);
			int valueOfOther = (i < o.components.size() ? o.components.get(i) : 0);
			
			int compare = valueOfThis - valueOfOther;
			if (compare != 0) {
				return compare;
			}
		}
		return 0;
	}
	
	/**
	 * Increments the version number at the given position and resets the following version components.
	 * @param position The position of the component to increment. The position must be between {@code 0} and {@code
	 * versionComponents - 1} inclusive.
	 * @return A new Version with the upgrade
	 */
	public Version upgrade(int position) {
		return new Version(IntStream.rangeClosed(0, position)
			.map(i -> (i < this.components.size() ? this.components.get(i) : 0) + Booleans.toInt(position == i))
			.toArray());
	}
	
	/**
	 * Returns a textual representation of the version number.
	 * @return The version number as a string.
	 */
	@Override
	public String toString() {
		return Strings.join(this.components.toArray(), ".");
	}
}
