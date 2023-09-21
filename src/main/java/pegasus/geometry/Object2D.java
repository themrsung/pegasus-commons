package pegasus.geometry;

import pegasus.tensor.Vector2;

import java.util.function.DoubleUnaryOperator;
import java.util.function.UnaryOperator;

/**
 * A two-dimensional object.
 */
public interface Object2D {
    /**
     * Returns the location of this object.
     *
     * @return The location of this object
     */
    Vector2 getLocation();

    /**
     * Returns the acceleration of this object.
     *
     * @return The acceleration of this object
     */
    Vector2 getAcceleration();

    /**
     * Returns the rotation of this object.
     *
     * @return The rotation of this object
     */
    double getRotation();

    /**
     * Returns the rate of rotation of this object.
     *
     * @return The rate of rotation of this object
     */
    double getRotationRate();

    /**
     * Sets the location of this object.
     *
     * @param location The location of this object
     * @throws NullPointerException When the provided location is {@code null}
     */
    void setLocation(Vector2 location);

    /**
     * Sets the acceleration of this object.
     *
     * @param acceleration The acceleration of this object
     * @throws NullPointerException When the provided acceleration is {@code null}
     */
    void setAcceleration(Vector2 acceleration);

    /**
     * Sets the rotation of this object.
     *
     * @param rotation The rotation of this object
     */
    void setRotation(double rotation);

    /**
     * Sets the rate of rotation of this object.
     *
     * @param rotationRate The rate of rotation of this object
     */
    void setRotationRate(double rotationRate);

    /**
     * Updates the location of this object.
     *
     * @param operator The update function of which to apply
     * @throws NullPointerException When the provided update function is {@code null}
     */
    void updateLocation(UnaryOperator<Vector2> operator);

    /**
     * Updates the acceleration of this object.
     *
     * @param operator The update function of which to apply
     * @throws NullPointerException When the provided update function is {@code null}
     */
    void updateAcceleration(UnaryOperator<Vector2> operator);

    /**
     * Updates the rotation of this object.
     *
     * @param operator The update function of which to apply
     * @throws NullPointerException When the provided update function is {@code null}
     */
    void updateRotation(DoubleUnaryOperator operator);

    /**
     * Updates the rate of rotation of this object.
     *
     * @param operator The update function of which to apply
     * @throws NullPointerException When the provided update function is {@code null}
     */
    void updateRotationRate(DoubleUnaryOperator operator);

    /**
     * Moves this object.
     *
     * @param amount The amount of movement to apply
     * @throws NullPointerException When the provided vector is {@code null}
     */
    void move(Vector2 amount);

    /**
     * Accelerates this object.
     *
     * @param amount The amount of acceleration to apply
     * @throws NullPointerException When the provided vector is {@code null}
     */
    void accelerate(Vector2 amount);

    /**
     * Rotates this object.
     *
     * @param amount The amount of rotation to apply
     */
    void rotate(double amount);
}
