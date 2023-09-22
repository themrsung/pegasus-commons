package pegasus.graphics;

import pegasus.tensor.*;

import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/**
 * Represents a triangle of arbitrary dimensions.
 *
 * @param <V> The type of vector used to represent the vertices of this triangle
 * @see Triangle2D
 * @see Triangle3D
 */
public interface Triangle<V extends Vector<V>> {
    /**
     * Creates and returns a new triangle.
     *
     * @param a The first vertex
     * @param b The second vertex
     * @param c The third vertex
     * @return The constructed triangle
     * @throws NullPointerException When a {@code null} vertex is provided
     */
    static Triangle2D of(Vector2 a, Vector2 b, Vector2 c) {
        return new Triangle2D(a, b, c);
    }

    /**
     * Creates and returns a new triangle.
     *
     * @param a The first vertex
     * @param b The second vertex
     * @param c The third vertex
     * @return The constructed triangle
     * @throws NullPointerException When a {@code null} vertex is provided
     */
    static Triangle3D of(Vector3 a, Vector3 b, Vector3 c) {
        return new Triangle3D(a, b, c);
    }

    /**
     * Returns the first vertex of this triangle.
     *
     * @return The first vertex of this triangle
     */
    V getA();

    /**
     * Returns the second vertex of this triangle.
     *
     * @return The second vertex of this triangle
     */
    V getB();

    /**
     * Returns the third vertex of this triangle.
     *
     * @return The third vertex of this triangle
     */
    V getC();

    /**
     * Returns the geometric centroid of this triangle.
     *
     * @return The geometric centroid of this triangle
     */
    V getCenter();

    /**
     * Returns the surface area of this triangle.
     *
     * @return The surface area of this triangle
     */
    double getArea();

    /**
     * Sets the first vertex of this triangle.
     *
     * @param a The first vertex of this triangle
     * @throws NullPointerException When the provided vertex is {@code null}
     */
    void setA(V a);

    /**
     * Sets the second vertex of this triangle.
     *
     * @param b The second vertex of this triangle
     * @throws NullPointerException When the provided vertex is {@code null}
     */
    void setB(V b);

    /**
     * Sets the third vertex of this triangle.
     *
     * @param c The third vertex of this triangle
     * @throws NullPointerException When the provided vertex is {@code null}
     */
    void setC(V c);

    /**
     * Applies the provided update function to each vertex of this triangle, then assigns each
     * vertex of this triangle to the return values of the provided update function.
     *
     * @param operator The update function of which to apply to each vertex of this triangle
     * @throws NullPointerException When the provided update function is {@code null}
     */
    void replace(UnaryOperator<V> operator);

    /**
     * Applies the provided mapper function to each vertex of this triangle, then returns a
     * new triangle of the return values of the provided mapper function.
     *
     * @param mapper The mapper function of which to apply to each vertex of this triangle
     * @return The resulting triangle
     * @throws NullPointerException When the provided mapper function is {@code null}
     */
    Triangle<V> map(UnaryOperator<V> mapper);

    /**
     * Returns a stream whose source is the vertices of this triangle.
     *
     * @return A stream of this triangle's vertices
     */
    Stream<V> stream();

    /**
     * Executes the provided action once for each vertex of this triangle.
     *
     * @param action The action to be performed for each vertex
     * @throws NullPointerException When the provided action is {@code null}
     */
    void forEachVertex(Consumer<? super V> action);

    /**
     * Returns the hash code of this triangle.
     *
     * @return The hash code of this triangle
     */
    int hashCode();

    /**
     * Checks for equality between this triangle and the provided object {@code obj}.
     *
     * @param obj The object to compare to
     * @return {@code true} if the provided object is also a triangle, and the vertices are equal
     */
    boolean equals(Object obj);

    /**
     * Serializes this triangle into a string.
     *
     * @return The string representation of this triangle
     */
    String toString();
}
