package pegasus.geometry;

import pegasus.tensor.Quaternion;
import pegasus.tensor.Vector3;

import java.util.function.UnaryOperator;

/**
 * A three-dimensional object.
 */
public interface Object3D {
    /**
     * Returns the location of this object.
     *
     * @return The location of this object
     */
    Vector3 getLocation();

    /**
     * Returns the acceleration of this object.
     *
     * @return The acceleration of this object
     */
    Vector3 getAcceleration();

    /**
     * Returns the rotation of this object.
     *
     * @return The rotation of this object
     */
    Quaternion getRotation();

    /**
     * Returns the rate of rotation of this object.
     *
     * @return The rate of rotation of this object
     */
    Quaternion getRotationRate();

    /**
     * Sets the location of this object.
     *
     * @param location The location of this object
     * @throws NullPointerException When the provided location is {@code null}
     */
    void setLocation(Vector3 location);

    /**
     * Sets the acceleration of this object.
     *
     * @param acceleration The acceleration of this object
     * @throws NullPointerException When the provided acceleration is {@code null}
     */
    void setAcceleration(Vector3 acceleration);

    /**
     * Sets the rotation of this object.
     *
     * @param rotation The rotation of this object
     * @throws NullPointerException When the provided rotation is {@code null}
     */
    void setRotation(Quaternion rotation);

    /**
     * Sets the rate of rotation of this object.
     *
     * @param rotationRate The rate of rotation of this object
     * @throws NullPointerException When the provided rate of rotation is {@code null}
     */
    void setRotationRate(Quaternion rotationRate);

    /**
     * Updates the location of this object.
     *
     * @param operator The update function of which to apply
     * @throws NullPointerException When the provided update function is {@code null}
     */
    void updateLocation(UnaryOperator<Vector3> operator);

    /**
     * Updates the acceleration of this object.
     *
     * @param operator The update function of which to apply
     * @throws NullPointerException When the provided update function is {@code null}
     */
    void updateAcceleration(UnaryOperator<Vector3> operator);

    /**
     * Updates the rotation of this object.
     *
     * @param operator The update function of which to apply
     * @throws NullPointerException When the provided update function is {@code null}
     */
    void updateRotation(UnaryOperator<Quaternion> operator);

    /**
     * Updates the rate of rotation of this object.
     *
     * @param operator The update function of which to apply
     * @throws NullPointerException When the provided update function is {@code null}
     */
    void updateRotationRate(UnaryOperator<Quaternion> operator);

    /**
     * Moves this object.
     *
     * @param amount The amount of movement to apply
     * @throws NullPointerException When the provided vector is {@code null}
     */
    void move(Vector3 amount);

    /**
     * Accelerates this object.
     *
     * @param amount The amount of acceleration to apply
     * @throws NullPointerException When the provided vector is {@code null}
     */
    void accelerate(Vector3 amount);

    /**
     * Rotates this object.
     *
     * @param amount The amount of rotation to apply
     * @throws NullPointerException When the provided quaternion is {@code null}
     */
    void rotate(Quaternion amount);
}
