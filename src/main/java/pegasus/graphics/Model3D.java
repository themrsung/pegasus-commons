package pegasus.graphics;

import pegasus.tensor.Vector3;
import pegasus.tuple.IntTriple;
import pegasus.tuple.Tuple;

import java.io.Serial;

/**
 * An immutable three-dimensional model.
 *
 * @see Model
 */
public class Model3D extends AbstractModel<Vector3, Model3D.Face3D> {
    @Serial
    private static final long serialVersionUID = 0;

    /**
     * Creates a new model.
     *
     * @param vertices The vertices of this model
     * @param normals  The normals of this model
     * @param faces    The faces of this model
     * @throws NullPointerException When a {@code null} parameter is provided
     */
    public Model3D(Tuple<Vector3> vertices, Tuple<Vector3> normals, Tuple<Face3D> faces) {
        super(vertices, normals, faces);
    }

    /**
     * An immutable three-dimensional face.
     */
    public static class Face3D implements Model.Face {
        @Serial
        private static final long serialVersionUID = 0;

        /**
         * Creates a new model face.
         *
         * @param vertexIndices The vertex indices
         * @param normalIndex   The normal index
         * @throws NullPointerException When the provided tuple of indices is {@code null}
         */
        public Face3D(IntTriple vertexIndices, int normalIndex) {
            this.vertexIndices = vertexIndices;
            this.normalIndex = normalIndex;
        }

        /**
         * Creates a new model face.
         *
         * @param f The face of which to copy values from
         */
        public Face3D(Model.Face f) {
            this.vertexIndices = f.getVertexIndices();
            this.normalIndex = f.getNormalIndex();
        }

        /**
         * The vertex indices.
         */
        protected final IntTriple vertexIndices;

        /**
         * The normal index.
         */
        protected final int normalIndex;

        /**
         * {@inheritDoc}
         *
         * @return {@inheritDoc}
         */
        @Override
        public IntTriple getVertexIndices() {
            return vertexIndices;
        }

        /**
         * {@inheritDoc}
         *
         * @param i The index of the vertex index to get
         * @return {@inheritDoc}
         * @throws IndexOutOfBoundsException {@inheritDoc}
         */
        @Override
        public int getVertexIndex(int i) throws IndexOutOfBoundsException {
            return vertexIndices.get(i);
        }

        /**
         * {@inheritDoc}
         *
         * @return {@inheritDoc}
         */
        @Override
        public int getNormalIndex() {
            return normalIndex;
        }
    }
}
