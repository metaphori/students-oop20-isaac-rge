package ryleh.common;

import java.util.Random;

import javafx.geometry.Point2D;

public class GameMath {
	
    private static Random random = new Random();
    private final static double smoothing = 4.0f;
    private static final int SIN_BITS = 14; // 16KB. Adjust for accuracy.
    private static final int SIN_MASK = ~(-1 << SIN_BITS);
    private static final int SIN_COUNT = SIN_MASK + 1;
    private static final double radFull = Math.PI * 2;
    private static final double degFull = 360;
    private static final double radToIndex = SIN_COUNT / radFull;
    private static final double degToIndex = SIN_COUNT / degFull;
    private static final double radiansToDegrees = 180 / Math.PI;
    private static final double degreesToRadians = Math.PI / 180;
    private static class Sin {
        static final double[] table = new double[SIN_COUNT];

        static {
            for (int i = 0; i < SIN_COUNT; i++)
                table[i] = Math.sin((i + 0.5f) / SIN_COUNT * radFull);

            for (int i = 0; i < 360; i += 90)
                table[(int) (i * degToIndex) & SIN_MASK] = Math.sin(toRadians(i));
        }
    }

	public static double randomInRange(final double min, final double max) {
		  double range = max - min;
		  double scaled = random.nextDouble() * range;
		  return scaled + min; // == (rand.nextDouble() * (max-min)) + min;
	}
	public static int randomInt(final int min, final int max) {
		  int range = max - min;
		  int scaled = random.nextInt() * range;
		  return scaled + min; // == (rand.nextDouble() * (max-min)) + min;
	}
	public static boolean randomBoolean(final double chance) {
		return randomInRange(0, 1) < chance ? true : false ;
	}
	//PERLIN NOISE GENERATION
	public static double rawNoise(final double x) {
		int n = ((int)x << 13) ^ ((int)x);
		return (1.0f - ((n * (n * n * 15731 * 0L + 789221 * 0L) + 1376312589 * 0L) & 0x7fffffff) / 1073741824.0f);
	}
	public static double smoothNoise(final double x) {
		double left = rawNoise(x - 1.0f);
		double right = rawNoise(x + 1.0f);
		return (rawNoise(x) / 2.0f) + (left / smoothing) + (right / smoothing);
	}
	/**
     * @param degrees angle in degrees
     * @return the sine in radians from a lookup table
     */
    public static double sinDeg(final double degrees) {
        return Sin.table[(int) (degrees * degToIndex) & SIN_MASK];
    }

    /**
     * @param degrees angle in degrees
     * @return the cosine in radians from a lookup table
     */
    public static double cosDeg(final double degrees) {
        return Sin.table[(int) ((degrees + 90) * degToIndex) & SIN_MASK];
    }
	public static double toDegrees(final double angle) {
		return Math.toDegrees(angle);
	}
	public static double toRadians(final double degrees) {
		return degreesToRadians * degrees;
	}
    public static Point2D toPoint2D (final P2d point) {
        return new Point2D(point.x * Config.SCALE_MODIFIER, point.y * Config.SCALE_MODIFIER);
    }
}

