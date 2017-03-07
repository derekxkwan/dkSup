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

//can pass arrays for modharm and modidx!


DxkFm {
	* ar {|freq, carharm = 1, modharm, modidx, mul = 1|
	var mods, carrier, modfreq;
	modfreq = freq * modharm;
	mods = freq.deepCollect(1, {|item|
		var curmodfreq = item*modharm;
		Mix.new(SinOsc.ar(curmodfreq, 0, curmodfreq*modidx, 0));
	});


	carrier = SinOsc.ar((freq*carharm)+mods, 0, mul);
	^carrier;
	}


}


