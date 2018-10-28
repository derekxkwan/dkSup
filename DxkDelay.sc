DxkFbdellopN {
	* ar {|in, maxdelay = 1, delaytime =1, fb = 0, lop = 100, dry = 1, wet = 1|
		var snd, numChan = in.numChannels;
		var cur_serv = Server.local, delay_comp = cur_serv.options.blockSize/cur_serv.sampleRate;
		var real_delay = (delaytime - delay_comp).max(0);
		var local_in = LocalIn.ar(numChan);
		var mixed_in = local_in + in;
		var delayed = LPF.ar( DelayN.ar(mixed_in, maxdelay, real_delay), lop);
		LocalOut.ar(delayed * fb);
		snd = (dry * mixed_in) + (wet * delayed);
		^snd
	}
}

DxkFbdellopL {
	* ar {|in, maxdelay = 1, delaytime =1, fb = 0, lop = 100, dry = 1, wet = 1|
		var snd, numChan = in.numChannels;
		var cur_serv = Server.local, delay_comp = cur_serv.options.blockSize/cur_serv.sampleRate;
		var real_delay = (delaytime - delay_comp).max(0);
		var local_in = LocalIn.ar(numChan);
		var mixed_in = local_in + in;
		var delayed = LPF.ar( DelayL.ar(mixed_in, maxdelay, real_delay), lop);
		LocalOut.ar(delayed * fb);
		snd = (dry * mixed_in) + (wet * delayed);
		^snd
	}
}

DxkFbdellopC {
	* ar {|in, maxdelay = 1, delaytime =1, fb = 0, lop = 100, dry = 1, wet = 1|
		var snd, numChan = in.numChannels;
		var cur_serv = Server.local, delay_comp = cur_serv.options.blockSize/cur_serv.sampleRate;
		var real_delay = (delaytime - delay_comp).max(0);
		var local_in = LocalIn.ar(numChan);
		var mixed_in = local_in + in;
		var delayed = LPF.ar( DelayC.ar(mixed_in, maxdelay, real_delay), lop);
		LocalOut.ar(delayed * fb);
		snd = (dry * mixed_in) + (wet * delayed);
		^snd
	}
}