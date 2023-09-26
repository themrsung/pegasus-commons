package pegasus.graphics;

import pegasus.tensor.Vector3;
import pegasus.tuple.Tuple;

import java.io.Serial;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * An immutable three-dimensional model.
 *
 * @see Model
 */
public class Model3D implements Model<Vector3> {
    @Serial
    private static final long serialVersionUID = 0;

    /**
     * Creates a new model.
     *
     * @param vertices The vertices of this model
     * @param normals  The normal vertices of this model
     * @param faces    The faces of this model
     * @throws NullPointerException When a {@code null} parameter is provided, or at least one
     *                              vertex or face is {@code null}
     */
    public Model3D(Tuple<Vector3> vertices, Tuple<Vector3> normals, Tuple<Face> faces) {
        vertices.forEach(Objects::requireNonNull);
        normals.forEach(Objects::requireNonNull);
        faces.forEach(Objects::requireNonNull);

        this.vertices = vertices;
        this.normals = normals;
        this.faces = faces;
    }

    /**
     * Creates a new model.
     *
     * @param m The model of which to copy values from
     * @throws NullPointerException When the provided model {@code m} is {@code null}
     */
    public Model3D(Model<? extends Vector3> m) {
        this.vertices = m.getVertices().cast(Vector3.class);
        this.normals = m.getNormals().cast(Vector3.class);
        this.faces = m.getFaces();
    }

    /**
     * The vertices of this model.
     */
    protected final Tuple<Vector3> vertices;

    /**
     * The normal vertices of this model.
     */
    protected final Tuple<Vector3> normals;

    /**
     * The faces of this model.
     */
    protected final Tuple<Face> faces;

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
    public Tuple<Vector3> getVertices() {
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
    public Vector3 getVertex(int i) throws IndexOutOfBoundsException {
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
    public Tuple<Vector3> getNormals() {
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
    public Vector3 getNormal(int i) throws IndexOutOfBoundsException {
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
    public Tuple<Face> getFaces() {
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
    public Face getFace(int i) throws IndexOutOfBoundsException {
        return faces.get(i);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Stream<Vector3> vertexStream() {
        return vertices.stream();
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Stream<Vector3> normalStream() {
        return normals.stream();
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Stream<Face> faceStream() {
        return faces.stream();
    }

    /**
     * {@inheritDoc}
     *
     * @param action The action to be performed for each vertex
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void forEachVertex(Consumer<? super Vector3> action) {
        vertices.forEach(action);
    }

    /**
     * {@inheritDoc}
     *
     * @param action The action to be performed for each normal vertex
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void forEachNormal(Consumer<? super Vector3> action) {
        normals.forEach(action);
    }

    /**
     * {@inheritDoc}
     *
     * @param action The action to be performed for each face
     * @throws NullPointerException {@inheritDoc}
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
        return "Model3D{" +
                "vertices=" + vertices +
                ", normals=" + normals +
                ", faces=" + faces +
                '}';
    }
}
