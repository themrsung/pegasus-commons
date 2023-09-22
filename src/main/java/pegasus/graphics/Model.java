package pegasus.graphics;

import pegasus.tensor.Vector;
import pegasus.tuple.*;

import java.io.Serializable;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * A model which represents an arbitrary {@code n}-dimensional object.
 * @param <V> The vector used to represent the vertices of this model
 */
public interface Model<V extends Vector<V>> extends Serializable {
    /**
     * Returns the number of vertices this model has.
     * @return The number of vertices this model has
     */
    int getVertexCount();

    /**
     * Returns the vertices of this model.
     * @return A tuple of the vertices of this model
     */
    Tuple<V> getVertices();

    /**
     * Returns the {@code i}th vertex of this model.
     * @param i The index of the vertex to get
     * @return The {@code i}th vertex of this model
     * @throws IndexOutOfBoundsException When the provided index {@code i} is out of bounds
     */
    V getVertex(int i) throws IndexOutOfBoundsException;

    /**
     * Returns the number of normal vertices this model has.
     * @return The number of normal vertices this model has
     */
    int getNormalCount();

    /**
     * Returns the normal vertices of this model.
     * @return A tuple of the normal vertices of this model
     */
    Tuple<V> getNormals();

    /**
     * Returns the {@code i}th normal vertex of this model.
     * @param i The index of the normal vertex to get
     * @return The {@code i}th normal vertex of this model
     * @throws IndexOutOfBoundsException When the provided index {@code i} is out of bounds
     */
    V getNormal(int i) throws IndexOutOfBoundsException;

    /**
     * Returns the number of faces this model has.
     * @return The number of faces this model has
     */
    int getFaceCount();

    /**
     * Returns the faces of this model.
     * @return A tuple of the faces of this model
     */
    Tuple<? extends Face> getFaces();

    /**
     * Returns the {@code i}th face of this model.
     * @param i The index of the face to get
     * @return The {@code i}th face of this model
     * @throws IndexOutOfBoundsException When the provided index {@code i} is out of bounds
     */
    Face getFace(int i) throws IndexOutOfBoundsException;

    /**
     * Returns a stream whose source is the vertices of this model.
     * @return A stream of this model's vertices
     */
    Stream<V> vertexStream();

    /**
     * Returns a stream whose source is the normal vertices of this model.
     * @return A stream of this model's normal vertices
     */
    Stream<V> normalStream();

    /**
     * Returns a stream whose source is the faces of this model.
     * @return A stream of this model's faces
     */
    Stream<? extends Face> faceStream();

    /**
     * Executes the provided action once for each vertex of this model.
     * @param action The action to be performed for each vertex
     * @throws NullPointerException When the provided action is {@code null}
     */
    void forEachVertex(Consumer<? super V> action);

    /**
     * Executes the provided action once for each normal vertex of this model.
     * @param action The action to be performed for each normal vertex
     * @throws NullPointerException When the provided action is {@code null}
     */
    void forEachNormal(Consumer<? super V> action);

    /**
     * Executes the provided action once for each face of this model.
     * @param action The action to be performed for each face
     * @throws NullPointerException When the provided action is {@code null}
     */
    void forEachFace(Consumer<? super Face> action);

    /**
     * Serializes this model into a string.
     * @return The string representation of this model
     */
    String toString();

    /**
     * An immutable model face.
     */
    interface Face extends Serializable {
        /**
         * Returns the vertex indices of this face.
         * @return A tuple of the vertex indices of this face
         */
        IntTriple getVertexIndices();

        /**
         * Returns the {@code i}th vertex index of this face.
         * @param i The index of the vertex index to get
         * @return The {@code i}th vertex index of this face
         * @throws IndexOutOfBoundsException When the provided index {@code i} is out of bounds
         */
        int getVertexIndex(int i) throws IndexOutOfBoundsException;

        /**
         * Returns the surface normal index of this face.
         * @return The surface normal index of this face
         */
        int getNormalIndex();
    }
}
