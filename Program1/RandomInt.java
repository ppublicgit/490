/*
 * Random Integer generator
 */

import java.util.Random;

public class RandomInt {
    private int m_min; // min integer
    private int m_max; // max integer

    /*
     * RandomInt constructor
     */
    RandomInt(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        m_min = min;
        m_max = max;
    }

    /*
     * Get next integer
     */
    public int getNext() {
        Random r = new Random();
        return r.nextInt((m_max - m_min) + 1) + m_min;
    }
}