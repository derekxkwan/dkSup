DxkStut {
	* ar {
		|in,stut = 0, deltime = 10, stutlen = 0.1|
	var out, wet, delptr, clock, buf, delphs, trig, env, ramptime = 0.001;
	trig = HPZ1.kr(stut);
	clock = Phasor.ar(trig, stutlen.reciprocal/SampleRate.ir);
	clock = (clock - Delay1.ar(clock)) < 0;
	delptr = EnvGen.ar(Env([0,0,deltime], [0, deltime], \lin), trig);
	delptr = Latch.ar(delptr, clock);
	env = EnvGen.ar(Env.asr(ramptime, 1, ramptime), clock);
	wet = DelayL.ar(in, deltime, delptr);
	out = XFade2.ar(in, wet, Lag.kr(stut*2-1, ramptime));
	^out;
	}
}

