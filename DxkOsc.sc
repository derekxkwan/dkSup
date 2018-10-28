//resettable phase ixbmpulse
//adapted from code from Fredrik Olofsson on the sc-users mailing list

DxkIpls {
	* ar {
		|freq = 0, mul = 1, t_reset = 0|
		var output;
		output = Phasor.ar(t_reset, freq/SampleRate.ir);
		output = (output - Delay1.ar(output)) < 0;
		output = output * mul;	
		^output
	}
}

//version with random amplitudes

DxkIplsR {
	* ar {
		|freq = 0, mul = 1, rand = 1, randlo = 0.001, t_reset = 0|
		var output,  amp;
		output = Phasor.ar(t_reset, freq/SampleRate.ir);
		output = (output - Delay1.ar(output)) < 0;
		output = output * mul;
		output = Select.ar(rand, [output, output*Demand.ar(output, 0, Dwhite(randlo/mul, 1))]);
		^output
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


