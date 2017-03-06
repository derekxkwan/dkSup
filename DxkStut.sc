DxkStutL {
	* ar {
		|in, stut = 0, maxdelay = 10, stutlen = 0.1|
	var out, wet, delptr, clock, trig, env, ramptime = 0.001;
	trig = HPZ1.kr(stut);
	clock = Phasor.ar(trig, stutlen.reciprocal/SampleRate.ir);
	clock = (clock - Delay1.ar(clock)) < 0;
	delptr = Clip.ar(Sweep.ar(trig), 0, maxdelay);
	delptr = Latch.ar(delptr, clock);
	env = EnvGen.ar(Env.asr(ramptime, 1, ramptime), clock);
	wet = DelayL.ar(in, maxdelay, delptr);
	out = XFade2.ar(in, wet, Lag.kr(stut*2-1, ramptime));
	^out
	}
}

DxkStutC {
	* ar {
		|in, stut = 0, maxdelay = 10, stutlen = 0.1|
	var out, wet, delptr, clock, trig, env, ramptime = 0.001;
	trig = HPZ1.kr(stut);
	clock = Phasor.ar(trig, stutlen.reciprocal/SampleRate.ir);
	clock = (clock - Delay1.ar(clock)) < 0;
	delptr = Clip.ar(Sweep.ar(trig), 0, maxdelay);
	delptr = Latch.ar(delptr, clock);
	env = EnvGen.ar(Env.asr(ramptime, 1, ramptime), clock);
	wet = DelayC.ar(in, maxdelay, delptr);
	out = XFade2.ar(in, wet, Lag.kr(stut*2-1, ramptime));
	^out
	}
}


