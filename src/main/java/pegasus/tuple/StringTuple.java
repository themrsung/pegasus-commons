package pegasus.tuple;

import pegasus.container.ArrayElementReference;
import pegasus.container.ObjectContainer;

import java.io.File;
import java.io.Serial;
import java.util.*;
import java.util.function.*;
import java.util.stream.Stream;

/**
 * A specialized tuple of {@link String strings}. String tuples do not allow the usage
 * of {@code null} as its values, thus guaranteeing null safety during its lifecycle.
 *
 * @see Tuple
 * @see String
 */
public class StringTuple implements Tuple<String> {
    @Serial
    private static final long serialVersionUID = 0;

    /**
     * Creates a new string tuple.
     *
     * @param values The values of which to contain
     * @throws NullPointerException When the provided array is {@code null},
     *                              or when it contains a {@code null} value
     */
    public StringTuple(String... values) {
        this.values = Arrays.stream(values)
                .peek(Objects::requireNonNull)
                .toArray(String[]::new);
    }

    /**
     * Creates a new string tuple.
     *
     * @param s The stream of which to retrieve values from
     * @throws NullPointerException When the provided stream {@code s} is {@code null},
     *                              or when it contains a {@code null} value
     */
    public StringTuple(Stream<String> s) {
        this.values = s.peek(Objects::requireNonNull).toArray(String[]::new);
    }

    /**
     * Creates a new string tuple.
     *
     * @param t The tuple of which to copy values from
     * @throws NullPointerException When the provided tuple {@code t} is {@code null},
     *                              or it contains a {@code null} value
     */
    public StringTuple(Tuple<String> t) {
        this(t.stream());
    }

    /**
     * The internal array of strings.
     */
    private final String[] values;

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int size() {
        return values.length;
    }

    /**
     * Returns the total length of all values of this tuple. This is equivalent to the sum of
     * each {@link String#length() value's length}.
     *
     * @return The sum of this tuple's value's lengths
     */
    public int length() {
        return stream().mapToInt(String::length).sum();
    }

    /**
     * {@inheritDoc}
     *
     * @param value The value of which to check for containment
     * @return {@inheritDoc}
     */
    @Override
    public boolean contains(String value) {
        return stream().anyMatch(v -> Objects.equals(v, value));
    }

    /**
     * Returns whether this tuple contains the provided value without regard for
     * the case of its characters.
     *
     * @param value The value of which to check for containment
     * @return {@code true} if this tuple contains the provided value without regard to case
     */
    public boolean containsIgnoreCase(String value) {
        return stream().anyMatch(v -> v.equalsIgnoreCase(value));
    }

    /**
     * {@inheritDoc}
     *
     * @param t The tuple of which to check for containment
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public boolean containsAll(Tuple<? extends String> t) {
        return t.stream().allMatch(this::contains);
    }

    /**
     * {@inheritDoc}
     *
     * @param i The index of the element to get
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public String get(int i) throws IndexOutOfBoundsException {
        return values[i];
    }

    /**
     * Returns the {@code i}th element of this tuple, converted to upper case.
     *
     * @param i The index of the element to get
     * @return The {@code i}th element of this tuple in upper case
     * @throws IndexOutOfBoundsException When the provided index {@code i} is out of bounds
     */
    public String getAsUpperCase(int i) throws IndexOutOfBoundsException {
        return values[i].toUpperCase();
    }

    /**
     * Returns the {@code i}th element of this tuple, converted to lower case.
     *
     * @param i The index of the element to get
     * @return The {@code i}th element of this tuple in lower case
     * @throws IndexOutOfBoundsException When the provided index {@code i} is out of bounds
     */
    public String getAsLowerCase(int i) throws IndexOutOfBoundsException {
        return values[i].toLowerCase();
    }

    /**
     * {@inheritDoc}
     *
     * @param i The index of the element to get
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public ObjectContainer<String> getReference(int i) throws IndexOutOfBoundsException {
        return new ArrayElementReference<>(values, i, true);
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to each element of this tuple
     * @param <U>    {@inheritDoc}
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public <U> Tuple<U> map(Function<? super String, ? extends U> mapper) {
        return Tuple.from(stream().map(mapper));
    }

    /**
     * Applies the provided mapper function to each element of this tuple, then returns a new
     * tuple containing the return values of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to each element of this tuple
     * @return The resulting tuple
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    public StringTuple map(UnaryOperator<String> mapper) {
        return new StringTuple(stream().map(mapper));
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to each element of this tuple
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public DoubleTuple mapToDouble(ToDoubleFunction<? super String> mapper) {
        return DoubleTuple.from(stream().mapToDouble(mapper));
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to each element of this tuple
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public LongTuple mapToLong(ToLongFunction<? super String> mapper) {
        return LongTuple.from(stream().mapToLong(mapper));
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function of which to apply to each element of this tuple
     * @return {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public IntTuple mapToInt(ToIntFunction<? super String> mapper) {
        return IntTuple.from(stream().mapToInt(mapper));
    }

    /**
     * Converts this tuple's values into a {@link File file} using the value as it's path, then
     * returns the resulting tuple.
     *
     * @return The resulting tuple
     */
    public Tuple<File> mapToFile() {
        return Tuple.from(stream().map(File::new));
    }

    /**
     * Applies the modifier function to each value of this tuple, then converts it into a
     * {@link File file} using the resulting value as the file's path, then returns the resulting tuple.
     *
     * @param modifier The modifier function to apply to each string before passing it as a filepath
     * @return The resulting tuple
     * @throws NullPointerException When the provided modifier function is {@code null}
     */
    public Tuple<File> mapToFile(UnaryOperator<String> modifier) {
        return Tuple.from(stream().map(modifier).map(File::new));
    }

    /**
     * Parses the values of this tuple into {@code double} values, then returns the resulting tuple.
     *
     * @return The resulting tuple
     * @throws NumberFormatException When at least one element of this tuple is not
     *                               convertible to a {@code double} value
     */
    public DoubleTuple parseToDouble() throws NumberFormatException {
        return DoubleTuple.from(stream().mapToDouble(Double::parseDouble));
    }

    /**
     * Parses the values of this tuple into {@code long} values, then returns the resulting tuple.
     *
     * @return The resulting tuple
     * @throws NumberFormatException When at least one element of this tuple is not
     *                               convertible to a {@code long} value
     */
    public LongTuple parseToLong() throws NumberFormatException {
        return LongTuple.from(stream().mapToLong(Long::parseLong));
    }

    /**
     * Parses the values of this tuple into {@link Float} values, then returns the resulting tuple.
     *
     * @return The resulting tuple
     * @throws NumberFormatException When at least one element of this tuple is not
     *                               convertible to a {@link Float} value
     */
    public Tuple<Float> parseToFloat() throws NumberFormatException {
        return Tuple.from(stream().map(Float::parseFloat));
    }

    /**
     * Parses the values of this tuple into {@code int} values, then returns the resulting tuple.
     *
     * @return The resulting tuple
     * @throws NumberFormatException When at least one element of this tuple is not
     *                               convertible to a {@code int} value
     */
    public IntTuple parseToInt() throws NumberFormatException {
        return IntTuple.from(stream().mapToInt(Integer::parseInt));
    }

    /**
     * {@inheritDoc}
     *
     * @param target The target class of which to cast to
     * @param <U>    {@inheritDoc}
     * @return {@inheritDoc}
     * @throws ClassCastException   {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public <U> Tuple<U> cast(Class<? extends U> target) throws ClassCastException {
        return Tuple.of((U[]) values);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Stream<String> stream() {
        return Arrays.stream(values);
    }

    /**
     * Joins the values of this tuple into a string, separated by "{@code , }".
     *
     * @return The joint string
     */
    public String joinToString() {
        return String.join(", ", values);
    }

    /**
     * Joins the values of this tuple into a string.
     *
     * @param delimiter The delimiter string to separate the strings
     * @return The joint string
     * @throws NullPointerException When the provided delimiter is {@code null}
     */
    public String joinToString(String delimiter) {
        return String.join(delimiter, values);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public String[] toArray() {
        return Arrays.copyOf(values, values.length);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Iterator<String> iterator() {
        return stream().iterator();
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash((Object) values);
    }

    /**
     * {@inheritDoc}
     *
     * @param obj The object to compare to
     * @return {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Tuple<?> t)) return false;
        if (values.length != t.size()) return false;

        for (int i = 0; i < values.length; i++) {
            if (!Objects.equals(values[i], t.get(i))) return false;
        }

        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public String toString() {
        return Arrays.toString(values);
    }
}
