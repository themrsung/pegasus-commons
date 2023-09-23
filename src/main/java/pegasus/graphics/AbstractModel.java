package pegasus.graphics;

import pegasus.tensor.Vector;
import pegasus.tuple.Tuple;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * The abstract base class for model implementations.
 *
 * @param <V> The vector used to represent the vertices of this model
 * @param <F> The face used to represent the faces of this model
 * @see Model
 */
public abstract class AbstractModel<V extends Vector<V>, F extends Model.Face> implements Model<V> {
    /**
     * Creates a new model.
     *
     * @param vertices The vertices of this model
     * @param normals  The normals of this model
     * @param faces    The faces of this model
     * @throws NullPointerException When a {@code null} parameter is provided
     */
    protected AbstractModel(Tuple<V> vertices, Tuple<V> normals, Tuple<F> faces) {
        this.vertices = Objects.requireNonNull(vertices);
        this.normals = Objects.requireNonNull(normals);
        this.faces = Objects.requireNonNull(faces);
    }

    /**
     * The vertices of this model.
     */
    protected final Tuple<V> vertices;

    /**
     * The normals of this model.
     */
    protected final Tuple<V> normals;

    /**
     * The faces of this model.
     */
    protected final Tuple<F> faces;

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int getVertexCount() {
        return vertices.size();
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Tuple<V> getVertices() {
        return vertices;
    }

    /**
     * {@inheritDoc}
     *
     * @param i The index of the vertex to get
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public V getVertex(int i) throws IndexOutOfBoundsException {
        return vertices.get(i);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int getNormalCount() {
        return normals.size();
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Tuple<V> getNormals() {
        return normals;
    }

    /**
     * {@inheritDoc}
     *
     * @param i The index of the normal vertex to get
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public V getNormal(int i) throws IndexOutOfBoundsException {
        return normals.get(i);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int getFaceCount() {
        return faces.size();
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Tuple<F> getFaces() {
        return faces;
    }

    /**
     * {@inheritDoc}
     *
     * @param i The index of the face to get
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public F getFace(int i) throws IndexOutOfBoundsException {
        return faces.get(i);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Stream<V> vertexStream() {
        return vertices.stream();
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Stream<V> normalStream() {
        return normals.stream();
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Stream<F> faceStream() {
        return faces.stream();
    }

    /**
     * {@inheritDoc}
     *
     * @param action The action to be performed for each vertex
     */
    @Override
    public void forEachVertex(Consumer<? super V> action) {
        vertices.forEach(action);
    }

    /**
     * {@inheritDoc}
     *
     * @param action The action to be performed for each normal vertex
     */
    @Override
    public void forEachNormal(Consumer<? super V> action) {
        normals.forEach(action);
    }

    /**
     * {@inheritDoc}
     *
     * @param action The action to be performed for each face
     */
    @Override
    public void forEachFace(Consumer<? super Face> action) {
        faces.forEach(action);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "vertices=" + vertices +
                ", normals=" + normals +
                ", faces=" + faces +
                '}';
    }
}
