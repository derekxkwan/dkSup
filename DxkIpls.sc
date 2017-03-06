//resettable phase impulse
//from code from Fredrik Olofsson on the sc-users mailing list

DxkIpls {
	* ar {
		|freq = 0|
	var out, trig;
	trig = HPZ1.kr(freq);
	out = Phasor.ar(trig, freq.reciprocal/SampleRate.ir);
	out = (out - Delay1.ar(out)) < 0;
	^out
	}
}