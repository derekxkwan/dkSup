//resettable phase impulse
//adapted from code from Fredrik Olofsson on the sc-users mailing list

DxkIpls {
	* ar {
		|freq = 0,t_trig|
	var out, trig;
	out = Phasor.ar(t_trig, freq/SampleRate.ir);
	out = (out - Delay1.ar(out)) < 0;
	^out
	}
}