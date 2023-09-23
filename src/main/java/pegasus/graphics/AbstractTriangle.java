package pegasus.graphics;

import pegasus.tensor.Vector;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/**
 * The abstract base class for triangles.
 *
 * @param <V> The vector used to represent the vertices of this triangle
 * @see Triangle
 */
public abstract class AbstractTriangle<V extends Vector<V>> implements Triangle<V> {
    /**
     * Creates a new triangle.
     *
     * @param a The first vertex of this triangle
     * @param b The second vertex of this triangle
     * @param c The third vertex of this triangle
     * @throws NullPointerException When a {@code null} vector is provided
     */
    protected AbstractTriangle(V a, V b, V c) {
        this.a = Objects.requireNonNull(a);
        this.b = Objects.requireNonNull(b);
        this.c = Objects.requireNonNull(c);
    }

    /**
     * Creates a new triangle.
     *
     * @param t The triangle of which to copy properties from
     * @throws NullPointerException When the provided triangle {@code t} is {@code null}
     */
    protected AbstractTriangle(AbstractTriangle<V> t) {
        this.a = t.a;
        this.b = t.b;
        this.c = t.c;
    }

    /**
     * The first vertex of this triangle.
     */
    protected V a;

    /**
     * The second vertex of this triangle.
     */
    protected V b;

    /**
     * The third vertex of this triangle.
     */
    protected V c;

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public V getA() {
        return a;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public V getB() {
        return b;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public V getC() {
        return c;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public double getArea() {
        double ab = b.distance(a);
        double bc = c.distance(b);
        double ca = a.distance(c);

        double s = (ab + bc + ca) * 0.5;

        return Math.sqrt(s * (s - ab) * (s - bc) * (s - ca));
    }

    /**
     * {@inheritDoc}
     *
     * @param a The first vertex of this triangle
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void setA(V a) {
        this.a = Objects.requireNonNull(a);
    }

    /**
     * {@inheritDoc}
     *
     * @param b The second vertex of this triangle
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void setB(V b) {
        this.b = Objects.requireNonNull(b);
    }

    /**
     * {@inheritDoc}
     *
     * @param c The third vertex of this triangle
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void setC(V c) {
        this.c = Objects.requireNonNull(c);
    }

    /**
     * {@inheritDoc}
     *
     * @param operator The update function of which to apply to each vertex of this triangle
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void replace(UnaryOperator<V> operator) {
        a = operator.apply(a);
        b = operator.apply(b);
        c = operator.apply(c);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Stream<V> stream() {
        return Stream.of(a, b, c);
    }

    /**
     * {@inheritDoc}
     *
     * @param action The action to be performed for each vertex
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void forEachVertex(Consumer<? super V> action) {
        action.accept(a);
        action.accept(b);
        action.accept(c);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }

    /**
     * {@inheritDoc}
     *
     * @param obj The object to compare to
     * @return {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Triangle<?> t)) return false;
        return Objects.equals(a, t.getA()) &&
                Objects.equals(b, t.getB()) &&
                Objects.equals(c, t.getC());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }
}
