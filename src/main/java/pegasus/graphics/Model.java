package pegasus.graphics;

import pegasus.tensor.Vector;
import pegasus.tuple.IntTriple;
import pegasus.tuple.Tuple;

import java.awt.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * An immutable graphical model of an object of arbitrary dimensions.
 *
 * @param <V> The type of vector used to represent this models' vertices
 * @see Model3D
 */
public interface Model<V extends Vector<V>> extends Serializable {
    /**
     * Returns the number of vertices this model has.
     *
     * @return The number of vertices this model has
     */
    int getVertexCount();

    /**
     * Returns a tuple containing the vertices of this model.
     *
     * @return A tuple of this model's vertices
     */
    Tuple<V> getVertices();

    /**
     * Returns the {@code i}th vertex of this model.
     *
     * @param i The index of the vertex to get
     * @return The {@code i}th vertex of this model
     * @throws IndexOutOfBoundsException When the provided index {@code i} is out of bounds
     */
    V getVertex(int i) throws IndexOutOfBoundsException;

    /**
     * Returns the number of normal vertices this model has.
     *
     * @return The number of normal vertices this model has
     */
    int getNormalCount();

    /**
     * Returns a tuple containing the normal vertices of this model.
     *
     * @return A tuple of this model's normal vertices
     */
    Tuple<V> getNormals();

    /**
     * Returns the {@code i}th normal vertex of this model.
     *
     * @param i The index of the normal vertex to get
     * @return The {@code i}th normal vertex of this model
     * @throws IndexOutOfBoundsException When the provided index {@code i} is out of bounds
     */
    V getNormal(int i) throws IndexOutOfBoundsException;

    /**
     * Returns the number of faces this model has.
     *
     * @return The number of faces this model has
     */
    int getFaceCount();

    /**
     * Returns a tuple containing the faces of this model.
     *
     * @return A tuple of this model's faces
     */
    Tuple<Face> getFaces();

    /**
     * Returns the {@code i}th face of this model.
     *
     * @param i The index of the face to get
     * @return The {@code i}th face of this model
     * @throws IndexOutOfBoundsException When the provided index {@code i} is out of bounds
     */
    Face getFace(int i) throws IndexOutOfBoundsException;

    /**
     * Returns a stream whose source is the vertices of this model.
     *
     * @return A stream of this mode's vertices
     */
    Stream<V> vertexStream();

    /**
     * Returns a stream whose source is the normal vertices of this model.
     *
     * @return A stream of this model's normal vertices
     */
    Stream<V> normalStream();

    /**
     * Returns a stream whose source is the faces of this model.
     *
     * @return A stream of this model's faces
     */
    Stream<Face> faceStream();

    /**
     * Executes the provided action once for each vertex of this model.
     *
     * @param action The action to be performed for each vertex
     * @throws NullPointerException When the provided action is {@code null}
     */
    void forEachVertex(Consumer<? super V> action);

    /**
     * Executes the provided action once for each normal vertex of this model.
     *
     * @param action The action to be performed for each normal vertex
     * @throws NullPointerException When the provided action is {@code null}
     */
    void forEachNormal(Consumer<? super V> action);

    /**
     * Executes the provided action once for each face of this model.
     *
     * @param action The action to be performed for each face
     * @throws NullPointerException When the provided action is {@code null}
     */
    void forEachFace(Consumer<? super Face> action);

    /**
     * Serializes this model into a string.
     *
     * @return The string representation of this model
     */
    String toString();

    /**
     * The face of a {@link Model model}.
     */
    class Face implements Serializable {
        @Serial
        private static final long serialVersionUID = 0;

        /**
         * Creates a new face.
         *
         * @param vertexIndices The indices of this face's vertices
         * @param normalIndex   The index of this face's normal vertex
         * @param color         The color of this face
         * @throws NullPointerException When the provided tuple of vertices is {@code null}, or
         *                              the provided color is {@code null}
         */
        public Face(IntTriple vertexIndices, int normalIndex, Color color) {
            this.vertexIndices = Objects.requireNonNull(vertexIndices);
            this.normalIndex = normalIndex;
            this.color = Objects.requireNonNull(color);
        }

        /**
         * The tuple of vertex indices.
         */
        protected final IntTriple vertexIndices;

        /**
         * The index of the normal vertex.
         */
        protected final int normalIndex;

        /**
         * The color of this vertex.
         */
        protected final Color color;

        /**
         * Returns the {@code i}th vertex's index.
         *
         * @param i The index of the vertex to get
         * @return The {@code i}th vertex's index
         * @throws IndexOutOfBoundsException When the provided index {@code i} is out of bounds
         */
        public int getVertexIndex(int i) throws IndexOutOfBoundsException {
            return vertexIndices.get(i);
        }

        /**
         * Returns the index of this face's normal vertex.
         *
         * @return The index of this face's normal vertex
         */
        public int getNormalIndex() {
            return normalIndex;
        }

        /**
         * Returns the color of this face.
         *
         * @return The color of this face
         */
        public Color getColor() {
            return color;
        }

        /**
         * Serializes this face into a string.
         *
         * @return The string representation of this face
         */
        @Override
        public String toString() {
            return "Face{" +
                    "vertexIndices=" + vertexIndices +
                    ", normalIndex=" + normalIndex +
                    ", color=" + Colors.toString(color) +
                    '}';
        }
    }
}
