package de.craftingdani.craftingplugin.main;

public class TPS_API implements Runnable
{
	public static int TICK_COUNT= 0;
	public static long[] TICKS= new long[600];
	public static long LAST_TICK= 0L;

	public static double getTPS()
	{
		if (TICK_COUNT < 100)
		{
			return 20.0D;
		}
		
		int target = (TICK_COUNT- 1 - 100) % TICKS.length;
		long elapsed = System.currentTimeMillis() - TICKS[target];

		return 100 / (elapsed / 1000.0D);
	}
 
	public static long getElapsed(int tickID)
	{
		long time = TICKS[(tickID % TICKS.length)];
		return System.currentTimeMillis() - time;
	}
 
	public void run()
	{
		TICKS[(TICK_COUNT% TICKS.length)] = System.currentTimeMillis();
 
		TICK_COUNT+= 1;
	}
}