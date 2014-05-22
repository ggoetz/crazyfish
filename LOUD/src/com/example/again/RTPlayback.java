package com.example.again;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder.AudioSource;
import android.util.Log;
public class RTPlayback implements Runnable {
	
	private int SAMPLE_RATE;
	private static int[] mSampleRates = new int[] { 8000, 11025, 22050, 44100 };
	private short channelConfig;
	private short audioFormat;
	public final int BUFFER_SIZE = 8000; // in bytes
	private AudioRecord rec;
	private AudioTrack play;
	private static final String TAG = "RTPlayback";
	
	public RTPlayback() {
		
		rec = findAudioRecord();
		
	 if (channelConfig == AudioFormat.CHANNEL_IN_MONO) {
		play = new AudioTrack(AudioManager.STREAM_MUSIC,
                SAMPLE_RATE,
                AudioFormat.CHANNEL_OUT_MONO,
                audioFormat,
                BUFFER_SIZE,
                AudioTrack.MODE_STREAM);
		} else {
			play = new AudioTrack(AudioManager.STREAM_MUSIC,
	                SAMPLE_RATE,
	                AudioFormat.CHANNEL_OUT_STEREO,
	                audioFormat,
	                BUFFER_SIZE,
	                AudioTrack.MODE_STREAM);
		} 
	}
	
	@Override
	public void run() {
		while (true) {
			short[] buf = new short[BUFFER_SIZE/16];
			rec.startRecording();
			rec.read(buf,0,BUFFER_SIZE/16);
			play.write(buf, 0, BUFFER_SIZE/16);
			play.play();
		}
	}
	
	public AudioRecord findAudioRecord() {
	    for (int rate : mSampleRates) {
	        for (short audioFormat : new short[] { AudioFormat.ENCODING_PCM_8BIT, AudioFormat.ENCODING_PCM_16BIT }) {
	            for (short channelConfig : new short[] { AudioFormat.CHANNEL_IN_MONO, AudioFormat.CHANNEL_IN_STEREO }) {
	                try {
	                    Log.d(TAG, "Attempting rate " + rate + "Hz, bits: " + audioFormat + ", channel: "
	                            + channelConfig);
	                    int bufferSize = AudioRecord.getMinBufferSize(rate, channelConfig, audioFormat);

	                    if (bufferSize != AudioRecord.ERROR_BAD_VALUE) {
	                        // check if we can instantiate and have a success
	                        AudioRecord recorder = new AudioRecord(AudioSource.MIC, rate, channelConfig, audioFormat, bufferSize);

	                        if (recorder.getState() == AudioRecord.STATE_INITIALIZED) {
	                        	SAMPLE_RATE = rate;
	                        	this.audioFormat = audioFormat;
	                        	this.channelConfig = channelConfig;
	                            return recorder;
	                        }
	                    }
	                } catch (Exception e) {
	                    Log.e(TAG, rate + "Exception, keep trying.",e);
	                }
	            }
	        }
	    }
	    return null;
	}
	
	public void changeInput() {
		
	}

}
