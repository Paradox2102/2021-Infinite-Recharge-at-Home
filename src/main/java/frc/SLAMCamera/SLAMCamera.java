/*
 *	  Copyright (C) 2020  John H. Gaby
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, version 3 of the License.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *    
 *    Contact: robotics@gabysoft.com
 */

package frc.SLAMCamera;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 * @author John Gaby
 * 
 * @brief The PiCamera class handles communication with the Raspberry Pi Camera.
 * 
 *        This class allows you to connect to the Data server of the Raspberry
 *        Pi and receive information about the image frames processed by the
 *        camera.
 *
 *        It also implements a time sync protocol to synchronize the clocks and
 *        performs keep-alive functions that detect a disconnect.
 *
 */
public class SLAMCamera implements Network.NetworkReceiver {
	/**
	 * @brief The PiCameraStats class collects the current camera performance
	 *        statistics.
	 *
	 */
	public class SLAMCameraStats {
		// public int m_averageDelay = 0; // !<Specifies the average frame delay
		// public int m_maxDelay = 0; // !<Specifies the max frame delay
		// public int m_minDelay = Integer.MAX_VALUE; // !<Specifies the min frame delay
		// public int m_lostFrames; // !<Specifies the number of lost frames
		public long m_time; // !<Specifies the elapsed time

		public SLAMCameraStats(long time) {
			//int avgDelay, int maxDelay, int minDelay, int lostFrames,
			// m_averageDelay = avgDelay;
			// m_maxDelay = maxDelay;
			// m_minDelay = minDelay;
			// m_lostFrames = lostFrames;
			m_time = time;
		}
	}

	/**
	 * @brief The PiCamerPoint specifies an (x,y) position
	 *
	 */
	public class SLAMCameraPoint {
		public int m_SLAMx; // !<Specifies the horizontal position in pixels from the left
		public int m_SLAMy; // !<Specifies the vertical position in pixels from the top
		public int m_SLAMAngle;

		public SLAMCameraPoint(int SLAMx, int SLAMy, int SLAMAngle) {
			m_SLAMx = SLAMx;
			m_SLAMy = SLAMy;
			m_SLAMAngle = SLAMAngle;
		}
	}

	/**
	 * @brief The PiCameraRect specifies an rectangular region
	 *
	 */
	

	
		// ! @endcond

		/*
		 * public PiCameraPoint GetTopCenter() { return(new
		 * PiCameraPoint((m_bounds.m_left + m_bounds.m_right)/2, (m_topLeft +
		 * m_topRight)/2)); }
		 */
	

	/**
	 * @brief The PiCameraRegions specifies list of detected regions
	 *
	 *        The list will contain up to the max regions specified using the
	 *        ImageViewer and will be sorted from the largest to smallest area.
	 *
	 */
	

	private Network m_network = null;
	private boolean m_connected = false;
	

	public SLAMCamera() {
	
	}

	/**
	 * Returns true if connected to the camera
	 * 
	 */
	public boolean IsConnected() {
		return (m_connected);
	}

	// ! @cond PRIVATE
	public static int[] ParseIntegers(String str, int count) {
		int[] args = new int[count];
		int i = 0;

		String[] tokens = str.trim().split(" ");

		for (String token : tokens) {
			try {
				args[i] = Integer.parseInt(token);

			} catch (NumberFormatException nfe) {
				break;
			}

			if (++i >= count) {
				break;
			}
		}

		if (i == count) {
			return (args);
		}

		return (null);
	}

	public static long[] ParseLong(String str, int count) {
		long[] args = new long[count];
		int i = 0;

		String[] tokens = str.trim().split(" ");

		for (String token : tokens) {
			try {
				args[i] = Long.parseLong(token);

			} catch (NumberFormatException nfe) {
				break;
			}

			if (++i >= count) {
				break;
			}
		}

		if (i == count) {
			return (args);
		}

		return (null);
	}

	
	// ! @endcond

	/**
	 * Instructs the Raspberry Pi to dump the last N frame to disk. This can be
	 * useful in debugging to obtain a record of what the camera saw.
	 * 
	 * @param count - Count of frames to dump (max 120)
	 * 
	 */
	
	

	/**
	 * Ends logging and closes the log file
	 * 
	 */

	/**
	 * The Raspberry Pi controls pins 11 and 12 and these pins can be used to
	 * control relays which can control lights for the camera
	 * 
	 * @param value - Bit zero controls pin 11 and bit one controls pin 12
	 */
	
	
	
	/**
	 * Specifies which capture profile should be used.
	 * 
	 * @param profile - Specifies the profile to use (0-3)
	 */

	/**
	 * Instructs the Raspberry Pi to start logging the camera data on the Pi's disk
	 * This can be useful for debugging
	 *
	 */
	
	/**
	 * Ends the Raspberry Pi logging
	 *
	 */
	
	private long m_startTime = 0;
	/**
	 * Connects to the Raspberry Pi Data server
	 *
	 * @param host - Specifies IP for the host
	 * @param port - Specifies the port (default is 5800)
	 */
	public void Connect(String host, int port) {
		m_network = new Network();

		m_startTime = System.currentTimeMillis();

		m_network.Connect(this, host, port);

	}


	/**
	 * Returns the latest set of camera regions. Note that the data is received from
	 * the camera in a separate thread so calling this twice in a row can generate a
	 * different result. Once you retrieve and instance of PiCameraRegions however,
	 * you can be assured that it will NOT be modified when a new frame is received.
	 *
	 */

	private long GetTimeMs() {
		return System.currentTimeMillis();
	}

	

	SLAMCameraPoint m_point;
	Object m_lock;

	public SLAMCameraPoint getPoint(){
		synchronized(this){
			return m_point;	
		}
	}

	// ! @cond PRIVATE
	@Override
	public void ProcessData(String data) {
		Logger.Log("SLAMCamera", -1, String.format("Data: %s", data));

		if (data.charAt(0)=='P'){
			int[] values;
			values = ParseIntegers(data.substring(1), 6);
			SLAMCameraPoint point = new SLAMCameraPoint (values[0], values[1],values[2]);
			synchronized(m_lock){
				m_point = point;	
			}
		}

	}

	@Override
	public void Disconnected() {
		m_connected = false;
	}

	@Override
	public void Connected() {
		m_connected = true;

	}
	// ! @endcond
}
